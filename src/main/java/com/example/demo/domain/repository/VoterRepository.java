package com.example.demo.domain.repository;

import com.example.demo.domain.entity.VoterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoterRepository extends JpaRepository<VoterEntity, String> {

}
