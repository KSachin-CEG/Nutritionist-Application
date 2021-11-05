package com.globallogic.adminservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.adminservice.model.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {
}
