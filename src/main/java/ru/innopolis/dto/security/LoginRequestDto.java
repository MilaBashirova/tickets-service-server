package ru.innopolis.dto.security;

import javax.validation.constraints.NotBlank;

/**
 * @author KhafizovR by 22.02.2020
 * tickets-service-server
 */
public class LoginRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
