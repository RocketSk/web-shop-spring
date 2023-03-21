package com.training.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String login;
    private String welcomeMessage = "Welcome, ";
    private String messageToOrder = "Dear, ";

    public String getWelcomeMessage() {
        return welcomeMessage + this.login;
    }

    public String getMessageToOrder() {
        return messageToOrder + this.login + " is your order:";
    }
}