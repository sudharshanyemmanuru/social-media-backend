package com.socialmediaapp.backendrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="duplicate_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DuplicateProfile {
    @Id
    @Column(name="duplicate_profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int duplicateProfileId;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id",referencedColumnName = "profile_id")
    private NewUserProfile profile;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "duplicateProfile")
    private List<FriendRequest> friendRequests;
}
