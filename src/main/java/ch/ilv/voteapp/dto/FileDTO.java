package ch.ilv.voteapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FileDTO {
    @NotBlank
    @NotEmpty
    private String base64;
    private String name;
    private String type;
}
