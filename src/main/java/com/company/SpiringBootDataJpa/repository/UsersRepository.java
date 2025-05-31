package com.company.SpiringBootDataJpa.repository;

import com.company.SpiringBootDataJpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}