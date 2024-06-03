package com.example.demo.domain.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ElectionVoterId implements Serializable {
    private String voterId;
    private String electionName;

    public ElectionVoterId(String voterId, String electionName) {
        this.voterId = voterId;
        this.electionName = electionName;
    }

    public String getVoterId() {

        return voterId;
    }

    public void setVoterId(String voterId) {

        this.voterId = voterId;
    }

    public String getElectionName() {

        return electionName;
    }

    public void setElectionName(String electionName) {

        this.electionName = electionName;
    }
}
