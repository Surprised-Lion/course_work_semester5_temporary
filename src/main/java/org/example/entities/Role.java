package org.example.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.common.RoleType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Role extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column
    private RoleType name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<Permission> permissions;
}