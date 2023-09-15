package com.socialmediaapp.backendrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserProfileDto {
    private int id;
    private String firstName;
    private String dob;
    private String lastName;
    private String profileUrl;
}
