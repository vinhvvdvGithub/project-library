package com.practice.projectlibrary.dto;

import com.practice.projectlibrary.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private String roleName;
    private Timestamp createdAt;
    private String createdBy;

}
