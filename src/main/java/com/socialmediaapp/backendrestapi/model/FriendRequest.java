package com.socialmediaapp.backendrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="friend_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {
    @Id
    @Column(name="friend_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int frendRequestId;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="profile_id",referencedColumnName = "profile_id")
    private NewUserProfile profile;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="duplicate_profile_id",referencedColumnName = "duplicate_profile_id")
    private DuplicateProfile duplicateProfile;
}
