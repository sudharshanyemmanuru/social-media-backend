package com.socialmediaapp.backendrestapi.configuration;

import com.socialmediaapp.backendrestapi.model.NewProfileAuthorizationInformation;
import com.socialmediaapp.backendrestapi.repository.NewProfileAuthorizationInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BackendRestAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private NewProfileAuthorizationInformationRepository newProfileAuthorizationInformationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<GrantedAuthority> authorities;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String password=authentication.getCredentials().toString();
        NewProfileAuthorizationInformation authorizationInformation=newProfileAuthorizationInformationRepository.getBynewProfileUserName(userName);
        if(authorizationInformation!=null){
            if(passwordEncoder.matches(authorizationInformation.getNewProfilePassword(),password)){
                authorities=new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(authorizationInformation.getProfilesRolesInformation().getRoleName()));
                return new UsernamePasswordAuthenticationToken(authorizationInformation.getNewUserProfile().getFirstName(),password,authorities);
            }else{
                throw new BadCredentialsException("Invalid Credintails!!");
            }
        }
        throw new BadCredentialsException("Your Account is not found!! please register first");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}