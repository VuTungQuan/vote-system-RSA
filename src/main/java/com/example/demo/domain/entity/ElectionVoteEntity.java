package com.example.demo.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "election_votes")
public class ElectionVoteEntity {

    @EmbeddedId
    private ElectionVoterId id;
    private String candidate;
    private String signature;

    public ElectionVoterId getId() {

        return id;
    }

    public void setId(ElectionVoterId id) {

        this.id = id;
    }

    public String getCandidate() {

        return candidate;
    }

    public void setCandidate(String candidate) {

        this.candidate = candidate;
    }

    public String getSignature() {

        return signature;
    }

    public void setSignature(String signature) {

        this.signature = signature;
    }
}
