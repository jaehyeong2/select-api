package jjfactory.jpabasic.web.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRes<T> {
    private T data;
    private HttpStatus status;

    public ApiRes() {
    }

    public ApiRes(T data) {
        this.data = data;
    }

    public ApiRes(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }
}
