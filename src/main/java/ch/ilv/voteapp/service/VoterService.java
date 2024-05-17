package ch.ilv.voteapp.service;

import ch.ilv.voteapp.base.MessageResponse;
import ch.ilv.voteapp.dto.*;
import ch.ilv.voteapp.entity.*;
import ch.ilv.voteapp.mapper.UserVoterMapper;
import ch.ilv.voteapp.mapper.VoterMapper;
import ch.ilv.voteapp.repository.*;
import ch.ilv.voteapp.security.Roles;
import ch.ilv.voteapp.service.interf.VoterServiceInt;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VoterService implements VoterServiceInt {

    public static final List<String> HEADER_ROW = Arrays.asList("fname", "lname", "blk", "lot", "migs", "email", "age", "comments");

    private final UserVoteRepo userVoteRepo;
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final RoleRepo roleRepo;
    private final CandidateRepo candidateRepo;

    @Autowired
    public VoterService(UserVoteRepo userVoteRepo, UserRepo userRepo, UserRoleRepo userRoleRepo, RoleRepo roleRepo, CandidateRepo candidateRepo) {
        this.userVoteRepo = userVoteRepo;
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.roleRepo = roleRepo;
        this.candidateRepo = candidateRepo;
    }

    @Transactional
    @Override
    public void addVoter(VoterDto voterDto) {
        voterDto.setStatus(null);
        voterDto.setRoles(null);
        voterDto.setId(null);
        User user = VoterMapper.INSTANCE.dtoToEnity(voterDto);
        user.setId(null);
        createCredentials(user);
        user.setStatus(Status.ACTIVE);
        user.setRoles(null);
        validate(user);
        User saved = userRepo.save(user);
        UserRole userRole = new UserRole();
        userRole.setRole(new Role());
        userRole.setUser(new User());
        userRole.getRole().setId(roleRepo.findByName(Roles.VOTER).getId());
        userRole.getUser().setId(saved.getId());
        userRoleRepo.save(userRole);
    }

    private void validate(User user) {
        if(userRepo.existsByUsername(user.getUsername())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "username: " + user.getUsername() + " already exist.");
        }
        if(userRepo.existsByBlkAndLot(user.getBlk(), user.getLot())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Block: " + user.getBlk() +
                    " Lot: " + user.getLot() + " already exist.");
        }
    }

    @Override
    public void addVoter(List<VoterDto> voterDto) {
        for(VoterDto user : voterDto){
            addVoter(user);
        }
    }

    @Transactional
    private void createCredentials(User user){
        String userName = String.join("",user.getFname(),
                user.getLname(), String.valueOf(user.getAge()),
                String.valueOf(user.getBlk()), String.valueOf(user.getLot()));

        if(userName.length() > 49){
            userName = userName.substring(0, 49);
        }

        setTempPassword(user);
        user.setUsername(userName);
    }

    private void setTempPassword(User user) {
        String password = RandomStringUtils.randomAlphabetic(10);

        user.setTempPassword(password);
    }


    @Transactional
    @Override
    public MessageResponse vote(VoteDto voteDto) {
        User user = userRepo.findById(voteDto.getId()).orElse(null);
        if(user != null) {
            if(user.getVotedDate() != null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Voter already voted. id: " + user.getId());
            }
            List<UserVote> list = new ArrayList<>();
            Long voterId = voteDto.getId();
            validateVoter(voterId);

            Date date = new Date(Calendar.getInstance().getTime().getTime());
            for (Long id : voteDto.getCandidates()) {
                UserVote userVote = new UserVote();
                Candidate candidate = candidateRepo.findById(id).orElse(null);

                if(candidate != null){
                    userVote.setCandidate(candidate);
                    userVote.setUser(user);
                    userVote.setCreatedBy(voterId);
                    userVote.setCreatedDate(date);
                    userVote.setUpdatedBy(voterId);
                    userVote.setUpdatedDate(date);

                    list.add(userVote);
                }else{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate does not exist. id: " + id);
                }
            }

            if(!list.isEmpty()){
                userVoteRepo.saveAll(list);
            }
            user.setVotedDate(date);
            userRepo.save(user);

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist. id: " + voteDto.getId());
        }
        return new MessageResponse("Successfully Voted.");
    }

    private void validateVoter(Long voterId) {
        if(userVoteRepo.countByUserId(voterId) > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Voters already voted.");
        }
    }

    @Override
    public Long getCountCandidateByType(CandidateType candidateType) {
       if(candidateType == null){
           return userVoteRepo.count();
       }else{
            return userVoteRepo.countByCandidateType(candidateType.toString());
       }
    }

    @Override
    public List<UserVoteDto> getCandidateByType(CandidateType candidateType) {
        if(candidateType == null){
            return UserVoterMapper.INSTANCE.
                    entityToDto(userVoteRepo.findAll());
        }else{
            return UserVoterMapper.INSTANCE.
                    entityToDto(userVoteRepo.findByCandidateAndType(candidateType.toString()));
        }
    }

    @Override
    public VoterDto getVoter(Long id) {
        User user = userRepo.findById(id)
                .orElse(null);
        if(user == null)
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voter not found.");

        return parseUser(user);
    }

    @Override
    public VoterDto updateVoter(VoterDto voterDto, Long id) {
        User user = userRepo.findById(id).orElse(null);
        if(user != null){
            VoterMapper.INSTANCE.updateFromDto(user, voterDto);

            user = userRepo.save(user);
            voterDto = parseUser(user);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return voterDto;
    }

    private VoterDto parseUser(User user){
        VoterDto voterDto = VoterMapper.INSTANCE.entityToDto(user);

        if(user.getVotedDate() != null)
            voterDto.setVotedDate(new Date(user.getVotedDate().getTime()));

        if(user.getActivatedDate() != null)
            voterDto.setActivatedDate(new Date(user.getActivatedDate().getTime()));

        if(user.getLastLogin() != null)
            voterDto.setLastLogin(new Date(user.getLastLogin().getTime()));

        List<UserRole> roles = userRoleRepo.findByUserId(user.getId());
        voterDto.setRoles(new ArrayList<>());

        for(UserRole userRole : roles){
            RoleDto roleDto = new RoleDto();
            roleDto.setId(userRole.getUser().getId());
            roleDto.setName(userRole.getRole().getName());
            voterDto.getRoles().add(roleDto);
        }
        return voterDto;
    }

    @Transactional
    @Override
    public MessageResponse deleteVoter(Long id) {
        MessageResponse messageResponse = new MessageResponse();

        if(userRepo.existsById(id)){
            messageResponse.setMessage(String.join("Voter id {d} is deleted.", id.toString()));
            userRepo.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return messageResponse;
    }

    @Transactional
    @Override
    public MessageResponse updatePass(UpdatePassDto updatePassDto, PasswordEncoder passwordEncoder) {
        User user = userRepo.findByUsername(updatePassDto.getUsername()).orElse(null);
        if(user != null){
            if(user.getPassword() != null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already activated. Use your password and login.");
            }

            if(updatePassDto.getTemppass().equals(user.getTempPassword())){
                user.setPassword(passwordEncoder.encode(updatePassDto.getPassword()));
                if(StringUtils.isNotBlank(updatePassDto.getEmail())){
                    user.setEmail(updatePassDto.getEmail());
                }
                user.setActivatedDate(new Date(Calendar.getInstance().getTime().getTime()));

                userRepo.save(user);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Temporary password is invalid.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist.");
        }
        return new MessageResponse("User password successfully updated.");
    }

    @Override
    public MessageResponse checkUser(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found.")
        );
        MessageResponse messageResponse = new MessageResponse();
        if(user.getPassword() == null){
            messageResponse.setMessage("NEW_USER");
        }else{
            messageResponse.setMessage("LOGIN");
        }

        return messageResponse;
    }

    @Override
    public MessageResponse resetUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found.")
        );
        if(user.getVotedDate() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can reset a user that already voted.");
        }

        MessageResponse messageResponse = new MessageResponse();

        setTempPassword(user);
        user.setPassword(null);
//        deleteVotes(user.getId());
        userRepo.save(user);

        messageResponse.setMessage("User successfully reset. ");

        return messageResponse;
    }

    @Override
    public MessageResponse bulkAddVoter(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(FilenameUtils.getExtension(file.getOriginalFilename()));
        Map<Integer, String> writerMap = new HashMap<>();
        Workbook wb;

        try {
            boolean ctr = true;
            wb = WorkbookFactory.create(file.getInputStream());

            Sheet sheet = wb.getSheetAt(0);

            if(sheet != null && sheet.getLastRowNum() > 0){
                Iterator<Row> rowIterator = sheet.iterator();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    System.out.println(row.getRowNum());
                    if(ctr){
                        validateHeaderRow(row);
                        ctr = false;
                        continue;// row.getCell(HEADER_ROW.)
                    }

                    parseVoter(row, writerMap);

                    System.out.println();

                }

            }
            wb.close();
//            file.getInputStream().close();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Excel file error. " + e.getMessage());
        }
        MessageResponse messageResponse = new MessageResponse();

        if(writerMap.isEmpty()){
            messageResponse.setMessage("Success.");
        }else{
            StringBuilder sb = new StringBuilder();
            for(Map.Entry e : writerMap.entrySet()){
                if(sb.length() > 0){
                    sb.append("\n");
                }
                sb.append("Row: ").append(e.getKey()).append(" Errors: [ ").append(e.getValue()).append(" ]");
            }
            messageResponse.setMessage("Failures: " + sb);
        }

        return messageResponse;
    }

    @Override
    public List<VoterDto> getAll() {
        List<User> users = userRepo.findAll();
        List<VoterDto> list = VoterMapper.INSTANCE.entityToDto(users);

        return users.stream().map( user -> {
            VoterDto voterDto = VoterMapper.INSTANCE.entityToDto(user);

            if(user.getLastLogin() != null)
                voterDto.setLastLogin(new java.util.Date(user.getLastLogin().getTime()));
            if(user.getActivatedDate() != null)
                voterDto.setActivatedDate(new java.util.Date(user.getActivatedDate().getTime()));
            if(user.getVotedDate() != null)
                voterDto.setVotedDate(new java.util.Date(user.getVotedDate().getTime()));

            return voterDto;
        }).collect(Collectors.toList());
    }

    private void parseVoter(Row row, Map<Integer, String> stringMap) {
        VoterDto user = new VoterDto();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < HEADER_ROW.size(); i++){
            Cell cell = row.getCell(i);
            switch (i){
                case 0:
                    if(cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                        user.setFname(cell.getStringCellValue());
                    }else{
                        if(sb.length() > 0){
                            sb.append(" | ");
                        }
                        sb.append("Rejected: First Name is null. Col number: ").append(i);
                    }
                    break;
                case 1:
                    if(cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                        user.setLname(cell.getStringCellValue());
                    }else{
                        if(sb.length() > 0){
                            sb.append(" | ");
                        }
                        sb.append("Rejected: Last Name is null. Col number: ").append(i);
                    }
                    break;
                case 2:
                    if(cell != null && CellType.NUMERIC.equals(cell.getCellType()) &&
                            cell.getNumericCellValue() > 0) {
                        user.setBlk(new Double(cell.getNumericCellValue()).intValue());
                    }else{
                        if(sb.length() > 0){
                            sb.append(" | ");
                        }
                        sb.append("Rejected: Blk is invalid. Col number: ").append(i);
                    }
                    break;
                case 3:
                    if(cell != null && CellType.NUMERIC.equals(cell.getCellType()) &&
                            cell.getNumericCellValue() > 0) {
                        user.setLot(new Double(cell.getNumericCellValue()).intValue());
                    }else{
                        if(sb.length() > 0){
                            sb.append(" | ");
                        }
                        sb.append("Rejected: Lot is invalid. Col number: ").append(i);
                    }
                    break;
                case 4:
                    if(cell != null && CellType.BOOLEAN.equals(cell.getCellType()) ) {
                        user.setMigs(cell.getBooleanCellValue());
                    }else{
                        user.setMigs(false);
                    }
                    break;
                case 5:
                    if(cell != null && StringUtils.isNotBlank(cell.getStringCellValue()) ) {
                        user.setEmail(cell.getStringCellValue());
                    }else{
                        user.setEmail(StringUtils.EMPTY);
                    }
                    break;
                case 6:
                    if(cell != null && CellType.NUMERIC.equals(cell.getCellType()) &&
                            cell.getNumericCellValue() > 0) {
                        user.setAge(new Double(cell.getNumericCellValue()).intValue());
                    }else{
                        user.setAge(0);
                    }
            }
        }

        if(StringUtils.isBlank(sb.toString())){
            try{
                addVoter(user);
            }catch (Exception e){
                stringMap.put(row.getRowNum(), e.getMessage());
            }
        }else{
            stringMap.put(row.getRowNum(), sb.toString());
        }

    }

    private void validateHeaderRow(Row row) {
        for(int i = 0; i < HEADER_ROW.size() ; i++){
            Cell cell = row.getCell(i);
            String header = HEADER_ROW.get(i);
            if(cell == null || !header.equalsIgnoreCase(cell.getStringCellValue())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Excel file " +
                        "invalid headers: missing header [" + header + "].");
            }
        }

    }

    private void deleteVotes(Long id) {
        userVoteRepo.deleteAllByUserId(id);
        log.info("All votes for user id: " + String.valueOf(id) + " is deleted.");
    }

    @Override
    public Long getCountCandidateById(Long id) {
        return userVoteRepo.countByCandidateId(id);
    }

    @Override
    public List<UserVoteDto> getCandidateByVoter(Long id) {
        return UserVoterMapper.INSTANCE.
                entityToDto(userVoteRepo.findByUserId(id));
    }
}
