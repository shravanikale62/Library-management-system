package com.library.librarymanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByMobile(String mobile);

    User findByFullName(String fullName);

    User findByEmailAndPasswordAndRole(String email, String password, String role);

    List<User> findByRole(String role);
}