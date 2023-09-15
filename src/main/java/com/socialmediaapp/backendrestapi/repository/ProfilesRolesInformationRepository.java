package com.socialmediaapp.backendrestapi.repository;

import com.socialmediaapp.backendrestapi.model.ProfilesRolesInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRolesInformationRepository extends JpaRepository<ProfilesRolesInformation,Integer> {
    public ProfilesRolesInformation getByprofileRoleId(int id);
}
