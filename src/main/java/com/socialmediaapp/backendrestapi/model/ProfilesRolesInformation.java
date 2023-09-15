package com.socialmediaapp.backendrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="profiles_roles_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfilesRolesInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profile_role_id")
    private int profileRoleId;
    @Column(name="role_name")
    private String roleName;
}

