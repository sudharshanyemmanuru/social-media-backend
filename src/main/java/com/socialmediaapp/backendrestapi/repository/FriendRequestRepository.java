package com.socialmediaapp.backendrestapi.repository;

import com.socialmediaapp.backendrestapi.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Integer> {
}
