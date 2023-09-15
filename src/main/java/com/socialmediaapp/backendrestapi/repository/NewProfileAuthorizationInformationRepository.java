package com.socialmediaapp.backendrestapi.repository;

import com.socialmediaapp.backendrestapi.model.NewProfileAuthorizationInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewProfileAuthorizationInformationRepository extends JpaRepository<NewProfileAuthorizationInformation,Integer> {
    public NewProfileAuthorizationInformation getBynewProfileUserName(String userName);
}
