package com.example.ontapbuoi3.Model;

import java.util.Objects;

public class AccountLogin {
    private static int cnt = 0;

    private int id;
    private String username;
    private String password;
    private String email;

    public AccountLogin() {
        this.id = ++cnt;
    }

    public AccountLogin(String username, String password, String email) {
        this.id = ++cnt;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountLogin)) return false;
        AccountLogin that = (AccountLogin) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword());
    }

}
