package com.socialmediaapp.backendrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends NewUserProfileDto{
    private List<MyPostResponse> posts;
}
