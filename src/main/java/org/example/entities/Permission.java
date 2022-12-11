package org.example.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.common.PermissionType;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "permissions")
public class Permission extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private PermissionType name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}