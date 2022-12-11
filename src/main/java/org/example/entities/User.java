package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.common.GenderType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {
    @Column
    @JsonProperty("username")
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    @JsonProperty("email")
    private String email;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    @JsonProperty("genderType")
    private GenderType genderType;

    @OneToOne
    @JoinColumn(name = "role_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("roleId")
    private Role role;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : this.getRole().getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getName().name()));
        }
        return authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}