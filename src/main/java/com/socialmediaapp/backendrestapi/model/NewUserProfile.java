package com.socialmediaapp.backendrestapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="new_user_profile")
@Getter
@Setter
public class NewUserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profile_id")
    private int profileId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email_address")
    private String emailAddress;
    @Column(name="contact_number")
    private String contactNumber;
    @Column(name="date_of_birth")
    private String dateOfBirth;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="authorization_info_id",referencedColumnName = "authorization_info_id")
    private NewProfileAuthorizationInformation newProfileAuthorizationInformation;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="profile_pic_id",referencedColumnName = "profile_pic_id")
    private NewUserProfilePicture newUserProfilePicture;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "newUserProfile")
    private List<Post> posts;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "newUserProfile")
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "profile")
    private List<FriendRequest> friendRequests;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "profile")
    private DuplicateProfile duplicateProfile;
}
