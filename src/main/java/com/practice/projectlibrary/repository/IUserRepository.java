package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {


	Optional<User> findUserById(Long id);

	Boolean existsUserByEmail(String email);

	Boolean existsUserByUsername(String userName);

	@Query(value = "select * from users where active =true", nativeQuery = true)
	List<User> users();

	@Query(value = "select * from users where active=true and username=:username or email=:username", nativeQuery = true)
	Optional<User> getUserByUsernameAndEmail(@Param("username") String username);

	@Query(value = "select * from users where username=:username or email=:username", nativeQuery = true)
	Optional<User> checkUserByUsernameAndEmail(@Param("username") String username);


	@Query(value = "Select * from users where active=true AND email = :email", nativeQuery = true)
	Optional<User> findUserByEmail(@Param("email") String email);

}
