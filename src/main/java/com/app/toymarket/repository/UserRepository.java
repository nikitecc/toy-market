package com.app.toymarket.repository;

import com.app.toymarket.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findAll();

    @Query(value = "SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
    Optional<User> getUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.name = :name")
    User getUserByEmailAndName(@Param("email") String email, @Param("name") String name);
}
