package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

  @Query(value = "SELECT * FROM roles WHERE active=true", nativeQuery = true)
  List<Role> roles();

  @Query(value = "select * from roles where slug = :slug", nativeQuery = true)
  List<Role> roleDetail(@Param("slug") String slug);

  @Query(value = "select * from roles where active=true  and slug = :slug", nativeQuery = true)
  Set<Role> getRoleBySlug(@Param("slug") String slug);

  @Query(value = "select * from roles where active=true and id = :id", nativeQuery = true)
  Set<Role> getRoleByRoleId(@Param("id") Long id);

  @Query(value = "select * from roles where active=true and id = :id", nativeQuery = true)
  Optional<Role> getRoleById(@Param("id") Long id);

}
