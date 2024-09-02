package com.project.animalface_web.controller.kmscontroller;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.domain.mongoEntity.ProfileImage;
import com.project.animalface_web.service.kmsserviece.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Log4j2
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllUsers() {
        return memberService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable Long id) {
        Optional<Member> user = memberService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 파일업로드 없을 경우
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createdUser = userService.createUser(user);
//        return ResponseEntity.ok(createdUser);
//    }

    // 파일 업로드 할 경우
    @PostMapping
    public ResponseEntity<Member> createUser( @RequestPart("user") Member user,
                                            @RequestParam(value = "profileImage", required = false) MultipartFile file) {
        try {
//            @RequestPart를 사용하여 멀티파트 요청의 user 부분을 User 객체로 자동 변환

            // 사용자 정보 저장
            Member createdUser = memberService.createUser(user);

            // 파일이 존재할 경우 프로필 이미지 저장
            if (file !=null && !file.isEmpty()) {
                memberService.saveProfileImage(createdUser.getMemberNo(), file);
            }

            return ResponseEntity.ok(createdUser);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save user or profile image", e);
        }
    }

    // 이미지 같이 수정.
    @PutMapping("/{id}/update")
    public ResponseEntity<Member> updateUser(
            @PathVariable Long id,
            @RequestPart("user") Member user,
            @RequestParam(value = "profileImage", required = false) MultipartFile file) {

        try {
            Member updatedUser = memberService.updateUser(id, user);

            // 파일이 존재할 경우 프로필 이미지 저장
            if (file !=null && !file.isEmpty()) {
                // 기존 프로필 삭제
                Optional<Member> loadUser = memberService.getUserById(id);
                Member loadedUser = loadUser.get();
                memberService.deleteProfileImage(loadedUser);

                memberService.saveProfileImage(id, file);
            }

            return ResponseEntity.ok(updatedUser);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save user or profile image", e);
        }
    }

//    이미지 없이 수정
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//        User updatedUser = userService.updateUser(id, userDetails);
//        return ResponseEntity.ok(updatedUser);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id: " + id);
        memberService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 프로필 이미지, 몽고 디비에 연결
    @PostMapping("/{id}/uploadProfileImage")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            memberService.saveProfileImage(id, file);
            return ResponseEntity.ok("Profile image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload profile image");
        }
    }


    @GetMapping("/{id}/profileImage")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long id) {
        log.info("lsy users image 확인 ");
        Optional<Member> user = memberService.getUserById(id);
        if (user.isPresent() && user.get().getProfileImageId() != null) {
            ProfileImage profileImage = memberService.getProfileImage(user.get().getProfileImageId());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(profileImage.getContentType()))
                    .body(profileImage.getData());
        }
        return ResponseEntity.notFound().build();
    }

    // 프로필 이미지 삭제
    @PostMapping("/{id}/deleteProfileImage")
    public String deleteProfileImage(@RequestParam Long id) {
        Optional<Member> user = memberService.getUserById(id);
        Member user1 = user.get();
        if (user1 != null) {
            memberService.deleteProfileImage(user1);
            return "Profile image deleted successfully";
        } else {
            return "User not found";
        }
    }
}
