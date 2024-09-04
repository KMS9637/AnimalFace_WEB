package com.project.animalface_web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long memberNo;

    @NotEmpty(message = "아이디를 입력 해 주세요.")
    private String memberId;

    @NotEmpty(message = "비밀번호를 입력 해 주세요.")
    @Length(min=6, max=16, message = "비밀번호는 6자 이상, 16자 이하로 입력해주세요")
    private String memberPw;

//    private String memberImg;

    @NotEmpty(message = "닉네임을 입력 해 주세요.")
    private String memberName;

    private String memberGameResult;

//    //프로필 사진 위해 필요
//    private String uuid;
//    private String fileName;
//
//    //첨부 파일 이름들
//    // fileNames, uuid_fileName 구조 되어 있고,
//    // 업로드 후, 해당 dto에 위의 이름 구조 파일형식으로 업로드 하기.
//    private List<String> fileNames;
//    //    private MultipartFile profileImage;
//    public void addProfileImage(String fileName) {
//        this.fileNames.add(fileName);
//    }

}
