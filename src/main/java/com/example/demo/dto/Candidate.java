package com.example.demo.dto;

public class Candidate {
    private String name;
    private int numOfVote;

    public Candidate() { }

    public Candidate(String name, int voteCount) {
        this.name = name;
        this.numOfVote = voteCount;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getNumOfVote() {

        return numOfVote;
    }

    public void setNumOfVote(int numOfVote) {

        this.numOfVote = numOfVote;
    }
}
