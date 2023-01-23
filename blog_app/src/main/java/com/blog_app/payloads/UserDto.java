package com.blog_app.payloads;

import com.blog_app.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String about;

    private Set<Role> roles = new HashSet<>();
}
