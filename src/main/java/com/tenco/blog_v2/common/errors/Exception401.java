package com.tenco.blog_v2.common.errors;

public class Exception401 extends RuntimeException {
    // throw new Exception400("잘못된 요청"); <- 사용 시점 호출 모습
    public Exception401(String msg) {
        super(msg);
    }
}
