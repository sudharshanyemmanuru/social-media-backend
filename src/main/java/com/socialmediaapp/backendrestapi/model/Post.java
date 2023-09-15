package com.socialmediaapp.backendrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Entity
@Table(name="user_profile_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private int postId;
    @Column(name="tag_line")
    private String tageLine;
    @Column(name="post_content")
    private byte[] postContent;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="profile_id",referencedColumnName = "profile_id")
    private NewUserProfile newUserProfile;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "post")
    private List<Comment> comments;
}
