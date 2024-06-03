package com.example.demo.domain.repository;

import com.example.demo.domain.entity.ElectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<ElectionEntity, Long> {

    List<ElectionEntity> findByStatusTrue();
    List<ElectionEntity> findByName(String name);
}
