package com.example.tickets.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Client{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Login may not be empty")
    @NotBlank(message = "Login may not be blank")
    private String login;
    @NotEmpty(message = "Password may not be empty")
    @NotBlank(message = "Password may not be blank")
    private String password;
    @NotEmpty(message = "Name may not be empty")
    @NotBlank(message = "Name may not be blank")
    private String full_name;
    private  String sex;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "client_role", joinColumns = @JoinColumn(name = "client_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Client() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Client(String login, String password, String full_name, String sex) {
        this.login = login;
        this.password = password;
        this.full_name = full_name;
        this.sex = sex;
    }
}
