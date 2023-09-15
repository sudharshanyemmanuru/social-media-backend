package com.socialmediaapp.backendrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="new_user_profile_picture")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profile_pic_id")
    private int profilePicId;
    @Column(name = "img_data")
    private byte[] imgData;
}
