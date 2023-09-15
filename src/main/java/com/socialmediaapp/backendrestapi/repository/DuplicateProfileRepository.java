package com.socialmediaapp.backendrestapi.repository;

import com.socialmediaapp.backendrestapi.model.DuplicateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuplicateProfileRepository extends JpaRepository<DuplicateProfile,Integer> {
}
