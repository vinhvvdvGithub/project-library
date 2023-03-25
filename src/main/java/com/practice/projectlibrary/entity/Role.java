package com.practice.projectlibrary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


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
