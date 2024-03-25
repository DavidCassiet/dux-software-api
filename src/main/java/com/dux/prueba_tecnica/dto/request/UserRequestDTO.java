package com.dux.prueba_tecnica.dto.request;

public class UserRequestDTO {

    private String username;
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
