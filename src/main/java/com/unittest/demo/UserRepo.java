package com.unittest.demo;

import com.unittest.demo.models.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserResource,Long> {
    UserResource getByEmail(String email);
}
