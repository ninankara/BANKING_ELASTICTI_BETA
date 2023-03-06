package com.transfer.util;

import com.transfer.module.ExtKakaoApiNotifInput;
import com.transfer.module.ExtKakaoApiNotifOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestTemplate {
    private final String apiUrl = "https://kapi.kakao.com/v1/api/talk/friends/message/default/send";
    private final RestTemplateBuilder restTemplateBuilder;

    public static HttpHeaders makeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        headers.setBearerAuth("Bearer <Code>"); //here we need to code how to get the authentication code, which started from login API (not include in this project)
        return headers;
    }
    public ExtKakaoApiNotifOutput postMessage(List<String> receiverUuids, ExtKakaoApiNotifInput.templateObject templateObject) throws URISyntaxException {
        return restTemplateBuilder.build().postForObject(
                new URI(apiUrl),
                new HttpEntity<>(ExtKakaoApiNotifInput.builder().receiver_uuids(receiverUuids).template_object(templateObject).build(), makeHeader()),
                ExtKakaoApiNotifOutput.class);
    }
}
