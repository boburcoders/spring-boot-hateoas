package com.company.SpiringBootDataJpa.repo;

import com.company.SpiringBootDataJpa.models.ToDos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDosRepo extends JpaRepository<ToDos, Integer> {
}