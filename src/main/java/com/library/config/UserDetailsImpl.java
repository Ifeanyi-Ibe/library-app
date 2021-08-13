package com.library.config;

import com.library.model.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Member user;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public UserDetailsImpl(Member user) {
        this.user = user;
    }

    public UserDetailsImpl(Long id, String username, String password,
                           Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.email = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static UserDetails buildUserDetail(Member user){
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" +user.getRole()));
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                grantedAuthorityList
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Member getUser() {
        return user;
    }
}

