package com.socialmediaapp.backendrestapi.controller;

import com.socialmediaapp.backendrestapi.dto.MyPostResponse;
import com.socialmediaapp.backendrestapi.dto.NewUserProfileDto;
import com.socialmediaapp.backendrestapi.dto.Profile;
import com.socialmediaapp.backendrestapi.model.NewUserProfile;
import com.socialmediaapp.backendrestapi.model.Post;
import com.socialmediaapp.backendrestapi.service.SocailMediaBackendServiceLayer;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class SocialMediaBackendController {
    @Autowired
    private SocailMediaBackendServiceLayer socailMediaBackendServiceLayer;
    @PostMapping("/register")
   public String regsiterNewUser(@RequestBody NewUserProfile newUserProfile){
        if(socailMediaBackendServiceLayer.register(newUserProfile)!=null)
            return "success!!!";
       return "Failed";
   }
   @PostMapping ("/profile")
   public String profile(@RequestParam MultipartFile img,Authentication authentication) throws IOException {
       System.out.println("profile.....");
        return socailMediaBackendServiceLayer.updateProfilePicture(img, authentication.getName());
   }
   @GetMapping("/showprofile/{id}")
   public ResponseEntity<byte[]> getProfilePicture(@PathVariable int id) throws IOException {
        System.out.println("profile.....");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(socailMediaBackendServiceLayer.showProfilePic(id));
   }
   @PostMapping("/createpost")
   public String createPost(@RequestParam MultipartFile file,Authentication authentication) throws IOException {
        return socailMediaBackendServiceLayer.createPost(new Post(),file,authentication.getName());
   }
   @GetMapping("/showpost/{postId}")
   public ResponseEntity<byte[]> showPost(@PathVariable int postId){
        byte[] postContent= socailMediaBackendServiceLayer.showPost(postId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(postContent);
   }
   @GetMapping("/myposts")
   public List<MyPostResponse> getMyPosts(Authentication authentication){
        return socailMediaBackendServiceLayer.getMyPosts(authentication.getName());
   }
   @PostMapping("/postcomment/{postId}")
   public String postComment(@PathVariable int postId,Authentication authentication,@RequestParam String commentText){
        return socailMediaBackendServiceLayer.postComment(postId,commentText,authentication.getName());
   }
   @GetMapping("/all-posts")
   public List<MyPostResponse> getAllPosts(){
        return socailMediaBackendServiceLayer.getAllPosts();
   }
   @GetMapping("/all-people")
   public List<Profile> getAllPeople(Authentication authentication){
        return socailMediaBackendServiceLayer.getAllPeople(authentication.getName());
   }
   @PostMapping("/send-friend-request/{profileId}")
   public String sendFriendRequest(@PathVariable int profileId,Authentication authentication){
        return socailMediaBackendServiceLayer.sendFriendRequest(profileId,authentication.getName());
   }
   @GetMapping("/get-friend-requests")
   public List<Profile> getAllRequests(Authentication authentication){
        return socailMediaBackendServiceLayer.getAllRequests(authentication.getName());
   }
}