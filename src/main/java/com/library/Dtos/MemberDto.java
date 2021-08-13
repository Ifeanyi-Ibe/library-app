package com.library.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roles;
}
