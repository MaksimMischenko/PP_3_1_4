package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.*;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import java.util.List;



@Data

@Entity

@Table(name = "roles")

public class Role implements GrantedAuthority {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(unique = true)

    private String role;



    @Transient

    @ManyToMany(mappedBy = "roles")

    private List<User> users;



    public Role() {

    }



    public Role(String role) {

        this.role = role;

    }



    @Override

    public String getAuthority() {

        return role;

    }



    @Override

    public String toString() {

        return role.replace("ROLE_", "");

    }

}