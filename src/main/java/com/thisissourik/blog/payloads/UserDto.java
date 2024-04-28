package com.thisissourik.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {


    private int id;
    @NotEmpty
    @Size(min = 3, message = "Username must be minimum of 3 characters")
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be minimum of 3 and maximum of 4 characters")
    private String password;
    @NotEmpty
    private String about;
}
