package com.socialmediaapp.backendrestapi.service;

import com.socialmediaapp.backendrestapi.dto.CommentDto;
import com.socialmediaapp.backendrestapi.dto.MyPostResponse;
import com.socialmediaapp.backendrestapi.dto.NewUserProfileDto;
import com.socialmediaapp.backendrestapi.dto.Profile;
import com.socialmediaapp.backendrestapi.model.*;
import com.socialmediaapp.backendrestapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocailMediaBackendServiceLayer {
    @Autowired
    private NewUserProfileRepository newUserProfileRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfilesRolesInformationRepository profilesRolesInformationRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private DuplicateProfileRepository duplicateProfileRepository;
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    public NewUserProfile register(NewUserProfile newUserProfile){
        ProfilesRolesInformation profilesRolesInformation=profilesRolesInformationRepository.getByprofileRoleId(1);
        newUserProfile.getNewProfileAuthorizationInformation().setProfilesRolesInformation(profilesRolesInformation);
        NewUserProfile savedProfile=newUserProfileRepository.save(newUserProfile);
        DuplicateProfile duplicateProfile=new DuplicateProfile();
        duplicateProfile.setProfile(savedProfile);
        duplicateProfileRepository.save(duplicateProfile);
        return savedProfile;
    }
    public String updateProfilePicture(MultipartFile img,String userName) throws IOException {
        NewUserProfile newUserProfile=newUserProfileRepository.findByfirstName(userName);
        if(newUserProfile!=null){
            if(newUserProfile.getNewUserProfilePicture()!=null){
                NewUserProfilePicture profilePicture=newUserProfile.getNewUserProfilePicture();
                profilePicture.setImgData(img.getBytes());
            }else{
                NewUserProfilePicture profilePicture=new NewUserProfilePicture();
                profilePicture.setImgData(img.getBytes());
                newUserProfile.setNewUserProfilePicture(profilePicture);
            }
            newUserProfileRepository.save(newUserProfile);
            return "Success!!";
        }
        return "failed";
    }
    public byte[] showProfilePic(int id) throws IOException {
        NewUserProfile newUserProfile=newUserProfileRepository.getReferenceById(id);
        if(newUserProfile!=null){
            byte[] content=newUserProfile.getNewUserProfilePicture().getImgData();
            return content;
        }
        return new byte[5];
    }
    public String createPost(Post post,MultipartFile file,String userName) throws IOException {
        NewUserProfile profile=newUserProfileRepository.findByfirstName(userName);
        if(profile!=null){
           post.setPostContent(file.getBytes());
           post.setNewUserProfile(profile);
           postRepository.save(post);
           return "Posted Successfully!!";
        }
        return "failed";
    }
    public byte[] showPost(int postId){
        Post post=postRepository.findBypostId(postId);
        if(post!=null){
            byte[] postContent=post.getPostContent();
            return postContent;
        }
        return null;
    }
    public List<MyPostResponse> getMyPosts(String userName){
        NewUserProfile profile=newUserProfileRepository.findByfirstName(userName);
        List<MyPostResponse> myPosts=new ArrayList<>();
        if(profile!=null){
            List<Post> posts=profile.getPosts();
            myPosts=convertAll(posts);
        }
        return myPosts;
    }
    public String postComment(int postId,String commentText,String userName){
        NewUserProfile profile=newUserProfileRepository.findByfirstName(userName);
        Post post=postRepository.findBypostId(postId);
        if(profile!=null){
            //create a new comment object
            Comment comment=new Comment();
            comment.setCommentText(commentText);
            comment.setNewUserProfile(profile);
            comment.setPost(post);
            commentRepository.save(comment);
            return "Comment posted successfully!!";
        }
        return "Comment is failed to post";
    }
    public List<MyPostResponse> getAllPosts(){
        List<Post>allPosts=postRepository.findAll();
        return convertAll(allPosts);
    }
    public List<Profile> getAllPeople(String userName){
        List<NewUserProfile> fetchedPeople=newUserProfileRepository.findAll();
        List<Profile> allPeople=convertProfiles(fetchedPeople);
        return allPeople.stream()
                .filter(people->people.getFirstName()!=userName).toList();
    }


    //Helper methods..
    private List<MyPostResponse> convertAll(List<Post> posts){
        List<MyPostResponse> myPosts=new ArrayList<>();
        List<CommentDto> commentDtoList;
        for(Post post:posts){
            MyPostResponse myPost=new MyPostResponse();
            myPost.setPostId(post.getPostId());
            myPost.setTagLine(post.getTageLine());
            String uri=buildUri("http://localhost:8080/api/showpost",post.getPostId()+"");
            myPost.setUrl(uri);
            NewUserProfile profile1=post.getNewUserProfile();
            NewUserProfileDto dto=new NewUserProfileDto(profile1.getProfileId(),
                    profile1.getFirstName(),
                    profile1.getDateOfBirth(),
                    profile1.getLastName(),
                    buildUri("http://localhost:8080/api/showprofile",profile1.getProfileId()+""));
            myPost.setPostedBy(dto);
            commentDtoList=new ArrayList<>();
            for(Comment comment:post.getComments()){
                NewUserProfile commentedBy=comment.getNewUserProfile();
                System.out.println("First Name : "+commentedBy.getFirstName());
                CommentDto commentDto=new CommentDto();
                commentDto.setCommentText(comment.getCommentText());
                commentDto.setCommentId(comment.getCommentId());
                NewUserProfileDto newUserProfileDto=new NewUserProfileDto();
                newUserProfileDto.setId(commentedBy.getProfileId());
                newUserProfileDto.setDob(commentedBy.getDateOfBirth());
                newUserProfileDto.setFirstName(commentedBy.getFirstName());
                newUserProfileDto.setLastName(commentedBy.getLastName());
                String url= buildUri("http://localhost:8080/api/showprofile",commentedBy.getProfileId()+"");
                newUserProfileDto.setProfileUrl(url);
                commentDto.setCommentedBy(newUserProfileDto);
                commentDtoList.add(commentDto);
            }
            myPost.setComments(commentDtoList);
            myPosts.add(myPost);
        }
        return myPosts;
    }
    private String buildUri(String baseUri,String pathSegments){
        UriComponentsBuilder url=UriComponentsBuilder.fromUriString(baseUri)
                .pathSegment(pathSegments);
        return url.toUriString();
    }
    private List<Profile> convertProfiles(List<NewUserProfile> profiles){
        List<Profile> profileList=new ArrayList<>();
        for(NewUserProfile profile:profiles){
            Profile profile1=new Profile();
            profile1.setId(profile.getProfileId());
            profile1.setFirstName(profile.getFirstName());
            profile1.setLastName(profile.getLastName());
            profile1.setDob(profile.getDateOfBirth());
            profile1.setPosts(convertAll(profile.getPosts()));
            profile1.setProfileUrl(buildUri("http://localhost:8080/api/showprofile",profile.getProfileId()+""));
            profileList.add(profile1);
        }
        return profileList;
    }
    public String sendFriendRequest(int profileId,String userName){
        NewUserProfile targetProfile=newUserProfileRepository.findByprofileId(profileId);
        NewUserProfile loggedInProfile=newUserProfileRepository.findByfirstName(userName);
        if(targetProfile!=null && loggedInProfile!=null){
            DuplicateProfile duplicateProfile=loggedInProfile.getDuplicateProfile();
            System.out.println("duplicate Profile : "+duplicateProfile.getDuplicateProfileId());
            FriendRequest friendRequest=new FriendRequest();
            friendRequest.setProfile(targetProfile);
            friendRequest.setDuplicateProfile(duplicateProfile);
            friendRequestRepository.save(friendRequest);
            return "Friend Request Sent!!!";
        }
        return "Failed to send!!";
    }
    public List<Profile> getAllRequests(String userName){
        NewUserProfile loggedInProfile=newUserProfileRepository.findByfirstName(userName);
        List<Profile> result=null;
        if(loggedInProfile!=null){
            List<FriendRequest> requests=loggedInProfile.getFriendRequests();
            if(requests!=null){
                List<NewUserProfile> profiles=new ArrayList<>();
                for(FriendRequest request:requests){
                    profiles.add(request.getDuplicateProfile().getProfile());
                    result=convertProfiles(profiles);
                }
            }
        }
        return result;
    }

}
