package com.socialmediaapp.backendrestapi.dto;

import com.socialmediaapp.backendrestapi.model.NewUserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPostResponse {
    private int postId;
    private String tagLine;
    private String url;
    private List<CommentDto> comments;
    private NewUserProfileDto postedBy;
}
