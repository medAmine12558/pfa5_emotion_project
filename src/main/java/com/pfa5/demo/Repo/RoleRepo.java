package com.pfa5.demo.Repo;

import com.pfa5.demo.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Roles, Integer> {
}
