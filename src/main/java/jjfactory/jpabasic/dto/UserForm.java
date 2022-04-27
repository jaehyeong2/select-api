package jjfactory.jpabasic.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UserForm {
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
