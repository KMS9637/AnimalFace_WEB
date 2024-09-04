//package com.project.animalface_web.domain;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import lombok.*;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = "game")
//public class Images implements Comparable<Images>{
//    @Id
//    private String uuid;
//
//    private String fileName;
//
//    //
//    private int ord;
//
//    @ManyToOne
//    private Game game;
//
//    @ManyToOne
//    private FaceType faceType;
//
//    @Override
//    public int compareTo(Images other) {
//        // 현재 이미지와, 매개변수로 넘어온 이미지의 ord를 비교를 해서 정렬
//        // 양수는 , 오름차순, 음수 내림차순.
//        return this.ord - other.ord;
//    }
//
//    // 영속성을 이용해서, 부모 객체를 참고 있다가 , 만약, 부모 객체가 없어진다면,
//    // 고아 객체가 되어서, 자동 삭제 이용할 예정.
//    public void changeBoard(Game game,FaceType faceType) {
//        this.game = game;
//        this.faceType = faceType;
//    }
//}
////테스트 이미지 링크
////https://ibb.co/6grghdk