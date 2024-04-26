package hillsboro.philoarte.scalar.types;

import shop.philoarte.api.artist.domain.role.Role;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Log4j2
public class ArtistDto {
    private Long artistId;
    @NotBlank(message = "유효하지 않은 아이디입니다.")
    private String username;
    @NotBlank(message = "유효하지 않은 비밀번호입니다.")
    @Size(min = 8, message = "Minimum password length: 8")
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String school;
    private String department;

    private String uuid;
    private String imgName;
    private List<ArtistFileDto> fileDto;
    private List<Role> roles;
    private String token;

    @Builder.Default
    private ArrayList<MultipartFile> files = new ArrayList<>();

    @Builder.Default
    private List<ArtistFileDto> artistFileDtoList = new ArrayList<>();

    public void addArtistFileDto(ArtistFileDto artistFileDto) {
        artistFileDtoList.add(artistFileDto);
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

}
