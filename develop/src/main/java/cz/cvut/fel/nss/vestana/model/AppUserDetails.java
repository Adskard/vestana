package cz.cvut.fel.nss.vestana.model;

import java.util.Collection;
import java.util.Collections;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {

    @Getter
    private final Employee user;
    final private Collection<? extends GrantedAuthority> authorities;

    public AppUserDetails(Employee user) {
        this.user = user;
        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getClass().getSimpleName().toUpperCase()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
}
