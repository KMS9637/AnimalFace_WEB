package com.project.animalface_web.controller.ksyAPI;


import com.project.animalface_web.dto.CreateGameDTO;
import com.project.animalface_web.service.kdkserviece.CreateGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/createGame")
public class RestCreateGameController {
    private final CreateGameService createGameService;

    @GetMapping("/created")
    public String create(Model model) {
        return "createGame/create2";
    }//@GetMapping("/create")

    @PostMapping("/create")
    public ResponseEntity<?> createRegister(@Valid @RequestBody CreateGameDTO createGameDTO,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("register 중 오류 발생" + bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        log.info("화면에서 입력 받은 내용 확인 : " + createGameDTO);

        Long createGameNo = createGameService.registerCreateGame(createGameDTO);

        return ResponseEntity.ok().body("Created game with ID: " + createGameNo);
    }



    @GetMapping("/read/{createGameNo}")
    public String read(@PathVariable("createGameNo") Long createGameNo, Model model) {
        log.info("CreateGameController : /read 확인 중");

        CreateGameDTO createGameDTO1 = createGameService.readCreateGame(createGameNo);

        log.info("CreateGameController 확인 중, createGameDTO1 : " + createGameDTO1);
        model.addAttribute("createGameDTO", createGameDTO1);

        // 적절한 뷰 이름을 반환합니다.
        return "createGame/read"; // 뷰의 이름을 반환합니다.
    }






















}//Class
