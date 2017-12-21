package com.codeproj.recipesimplifierbase.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeproj.recipesimplifierbase.model.User;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}


