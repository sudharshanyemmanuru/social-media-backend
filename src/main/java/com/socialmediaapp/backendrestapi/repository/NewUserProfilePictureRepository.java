package com.socialmediaapp.backendrestapi.repository;

import com.socialmediaapp.backendrestapi.model.NewUserProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewUserProfilePictureRepository extends JpaRepository<NewUserProfilePicture,Integer> {
}
