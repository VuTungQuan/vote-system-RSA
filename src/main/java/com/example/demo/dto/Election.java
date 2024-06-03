package com.example.demo.dto;

public class Election {

    private Long id;
    private String name;
    private Candidate candidate1;
    private Candidate candidate2;
    private Candidate candidate3;
    private Candidate candidate4;
    private Candidate candidate5;
    private Boolean status;

    public Election() { }

    public Election(Long id) {
        this.id = id;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Candidate getCandidate1() {

        return candidate1;
    }

    public void setCandidate1(Candidate candidate1) {

        this.candidate1 = candidate1;
    }

    public Candidate getCandidate2() {

        return candidate2;
    }

    public void setCandidate2(Candidate candidate2) {

        this.candidate2 = candidate2;
    }

    public Candidate getCandidate3() {

        return candidate3;
    }

    public void setCandidate3(Candidate candidate3) {

        this.candidate3 = candidate3;
    }

    public Candidate getCandidate4() {

        return candidate4;
    }

    public void setCandidate4(Candidate candidate4) {

        this.candidate4 = candidate4;
    }

    public Candidate getCandidate5() {

        return candidate5;
    }

    public void setCandidate5(Candidate candidate5) {

        this.candidate5 = candidate5;
    }

    public Boolean getStatus() {

        return status;
    }

    public void setStatus(Boolean status) {

        this.status = status;
    }
}
