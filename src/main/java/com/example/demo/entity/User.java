package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    @NotEmpty(message = "username is mandatory")
    String userName;
    @Size(min=6,message = "password must be atleast 6 characters long")
    String passWord;
    List<String> roles;
}
