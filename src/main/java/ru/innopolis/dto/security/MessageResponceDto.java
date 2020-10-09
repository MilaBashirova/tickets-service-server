package ru.innopolis.dto.security;

/**
 * @author KhafizovR by 22.02.2020
 * tickets-service-server
 */
public class MessageResponceDto {

    private String message;

    public MessageResponceDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}