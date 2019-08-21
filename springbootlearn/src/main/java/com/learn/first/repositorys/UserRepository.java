package com.learn.first.repositorys;

import com.learn.first.entitys.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
  }