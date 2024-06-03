package com.example.demo.dto;

public class ElectionVoter {
    private Voter voter;
    private Election election;

    public Voter getVoter() {

        return voter;
    }

    public void setVoter(Voter voter) {

        this.voter = voter;
    }

    public Election getElection() {

        return election;
    }

    public void setElection(Election election) {

        this.election = election;
    }
}
