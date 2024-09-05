package com.project.animalface_web.service;

import com.project.animalface_web.domain.Member;
import com.project.animalface_web.domain.MemberRole;
import com.project.animalface_web.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Log4j2
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getUserById(Long memberNo) {
        return memberRepository.findById(memberNo);
    }

    public Optional<Member> getUserById(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public Member createUser(Member user) {
        // 비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(user.getMemberPw());
        user.setMemberPw(encodedPassword);
        user.addRole(MemberRole.USER);
        return memberRepository.save(user);
    }

    public Member updateUser(Long id, Member userDetails) {
        Member user = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setMemberName(userDetails.getMemberName());
        user.setMemberId(userDetails.getMemberId());

        return memberRepository.save(user);
    }

    public void deleteUser(String memberNo) {
        log.info("lsy 2 MemberService memberNo : " + memberNo);
//        Optional<Member> existingMember = memberRepository.findById(memberNo);
//        Member member = existingMember.get();
//        log.info("lsy 3 MemberService member : " + member.getMemberNo());
            memberRepository.deleteByMemberId(memberNo);  // 회원 삭제
//        memberRepository.deleteByMemberNo(memberNo);  // 회원 삭제
//        if (existingMember.isPresent()) {
//            //memberNo
////            Member member = existingMember.get();
//            log.info("lsy 4 MemberService member : " + member.getMemberNo());
////            memberRepository.deleteById(memberNo);  // 회원 삭제
//            memberRepository.deleteByMemberNo(memberNo);  // 회원 삭제
//        } else {
//            throw new RuntimeException("해당 ID의 사용자가 존재하지 않습니다.");
//        }
    }


    //프로필 이미지 업로드, 레스트 형식
//    public void saveProfileImage(Long userId, MultipartFile file) throws IOException {
//        // Get the user
//        Member user = memberRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Create and save the profile image
//        ProfileImage profileImage = new ProfileImage(
//                file.getOriginalFilename(),
//                file.getContentType(),
//                file.getBytes()
//        );
//        ProfileImage savedImage = profileImageRepository.save(profileImage);
//
//        // Link the profile image to the user
//        user.setProfileImageId(savedImage.getId());
//        memberRepository.save(user);
//    }

//    public ProfileImage getProfileImage(String imageId) {
//        return profileImageRepository.findById(imageId)
//                .orElseThrow(() -> new RuntimeException("Image not found"));
//    }

    // 프로필 이미지만 삭제
//    public void deleteProfileImage(Member user) {
//        // 현재 사용자가 가진 프로필 이미지 ID 가져오기
//        log.info("lsy user : " + user);
//        String profileImageId = user.getProfileImageId();
//        log.info("lsy profileImageId : " + profileImageId);
//
//        // 프로필 이미지 ID가 null이 아닌 경우에만 삭제 작업 수행
//        if (profileImageId != null) {
//            // MongoDB에서 프로필 이미지 삭제
//            profileImageRepository.deleteById(profileImageId);
//
//            // 사용자의 profileImageId 필드를 null로 설정
//            user.setProfileImageId(null);
//
//            // 업데이트된 사용자 정보 저장
//            memberRepository.save(user);
//        }
//    }

}

