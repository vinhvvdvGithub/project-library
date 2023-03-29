package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "SELECT * FROM roles WHERE status=true",nativeQuery = true)
    List<Role> roles();

    @Query(value = "select * from roles where slug = :slug",nativeQuery = true)
    List<Role> roleDetail(@Param("slug") String slug);
    @Query(value = "select * from roles where slug = :slug and status=true",nativeQuery = true)
    Set<Role> getRoleBySlug(@Param("slug")String slug);

    @Query(value = "select * from roles where id = :id",nativeQuery = true)
    Set<Role> getRoleByRoleId(@Param("id")Integer id);



}
