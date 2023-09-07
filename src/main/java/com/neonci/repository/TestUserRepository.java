package com.neonci.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neonci.entity.TestUser;

public interface TestUserRepository extends JpaRepository<TestUser, Long> {

}
