package com.socialmediaapp.backendrestapi.repository;

import com.socialmediaapp.backendrestapi.model.NewUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewUserProfileRepository extends JpaRepository<NewUserProfile,Integer> {
    public NewUserProfile findByfirstName(String userName);
    public NewUserProfile findByprofileId(int profileId);
}
