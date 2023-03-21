package com.practice.projectlibrary.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity{

    @NonNull
    @Column(name = "role_name")
    private String roleName;
    @NotNull
    private String slug;
    @NotNull
    private Boolean active;

}
