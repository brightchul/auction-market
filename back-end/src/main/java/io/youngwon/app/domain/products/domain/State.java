package io.youngwon.app.domain.products.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {

    WAITING("STATE_WAITING", "대기중"),
    SELLING("STATE_SELLING", "판매중"),
    FINISH("STATE_FINISH", "종료");

    private final String key;
    private final String value;

}
