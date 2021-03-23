package io.youngwon.app.utils.rabbitmq;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Consumer {

    private final ObjectMapper objectMapper;


    public void handler(String message) {
        // 메시지 파싱

        // 실제 실행부분
    }


}
