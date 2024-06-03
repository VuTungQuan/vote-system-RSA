package com.example.demo.domain.repository;

import com.example.demo.domain.entity.ElectionVoteEntity;
import com.example.demo.domain.entity.ElectionVoterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionVoteRepository extends JpaRepository<ElectionVoteEntity, ElectionVoterId> {

}
