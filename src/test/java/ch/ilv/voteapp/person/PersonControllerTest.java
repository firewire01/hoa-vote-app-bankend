package ch.ilv.voteapp.person;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerTest {
//    @Autowired
//    private MockMvc api;
//
//    @Autowired
//    private PersonRepository personRepository;
//
//    @BeforeAll
//    void setup() {
//        this.personRepository.save(new Person("John", "Doe", true, Sex.MALE, 24, "Lombard Street 150", "San Francisco", "94123"));
//        this.personRepository.save(new Person("Jane", "Doe", true, Sex.FEMALE, 23, "Lombard Street 150", "San Francisco", "94123"));
//    }
//
//    @Test
//    @Order(1)
//    void testGetPerson() throws Exception {
//        String accessToken = obtainAccessToken();
//
//        api.perform(get("api/v1/person").header("Authorization", "Bearer " + accessToken)
//                        .with(csrf()))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Lombard Street 150")));
//    }
//
//    private String obtainAccessToken() {
//
//        RestTemplate rest = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        String body = "client_id=vote-app&" +
//                "grant_type=password&" +
//                "scope=openid profile roles offline_access&" +
//                "username=admin&" +
//                "password=admin";
//
//        HttpEntity<String> entity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);
//
//        JacksonJsonParser jsonParser = new JacksonJsonParser();
//        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
//    }
}