package com.socialmediaapp.backendrestapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="new_profile_authorization_information")
@Getter
@Setter
public class NewProfileAuthorizationInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorization_info_id")
    private int authorizationInfoId;
    @Column(name="new_profile_user_name")
    private String newProfileUserName;
    @Column(name="new_profile_password")
    private String newProfilePassword;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="role_id",referencedColumnName = "profile_role_id")
    private ProfilesRolesInformation profilesRolesInformation;
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "newProfileAuthorizationInformation")
    private NewUserProfile newUserProfile;
}
