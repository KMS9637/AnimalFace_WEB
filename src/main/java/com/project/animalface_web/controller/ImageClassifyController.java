package com.project.animalface_web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log4j2
@RestController
public class ImageClassifyController {

    @PostMapping("/classify")
    public ResponseEntity<String> classifyImage(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("No file was submitted.");
        }

        // PyCharm에서 실행 중인 Python 서버의 URL
        String apiUrl = "http://localhost:8000/classify/";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // MultipartFile을 임시 파일로 변환
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + image.getOriginalFilename());
            image.transferTo(convFile);

            // POST 요청 준비
            HttpPost uploadFile = new HttpPost(apiUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("image", convFile);
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            // 요청 실행
            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String apiResult = EntityUtils.toString(responseEntity, "UTF-8");

            // JSON 파싱 및 가공 (필요시)
            // 예: 응답에서 predicted_label 값만 추출하여 새로운 형식으로 반환
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(apiResult);

            String predictedLabel = rootNode.path("face_type : ").asText();
            log.info("predictedLabel : " + predictedLabel);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));

            String customResponse = "당신의 얼굴상은 " + predictedLabel+"상 입니다.";
            log.info("customResponse : "+customResponse);

            // 임시 파일 정리
            if (!convFile.delete()) {
                System.err.println("Failed to delete the temporary file.");
            }

            // API 응답 반환
            return new ResponseEntity<>(customResponse, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File processing error: " + e.getMessage());
        }
    }
}