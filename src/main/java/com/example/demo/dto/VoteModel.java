package com.example.demo.dto;

public class VoteModel {

    private String voterId;
    private Long electionId;
    private Boolean candidate1Selected;
    private Boolean candidate2Selected;
    private Boolean candidate3Selected;
    private Boolean candidate4Selected;
    private Boolean candidate5Selected;

    public String getVoterId() {

        return voterId;
    }

    public void setVoterId(String voterId) {

        this.voterId = voterId;
    }

    public Long getElectionId() {

        return electionId;
    }

    public void setElectionId(Long electionId) {

        this.electionId = electionId;
    }

    public Boolean getCandidate1Selected() {

        return candidate1Selected;
    }

    public void setCandidate1Selected(Boolean candidate1Selected) {

        this.candidate1Selected = candidate1Selected;
    }

    public Boolean getCandidate2Selected() {

        return candidate2Selected;
    }

    public void setCandidate2Selected(Boolean candidate2Selected) {

        this.candidate2Selected = candidate2Selected;
    }

    public Boolean getCandidate3Selected() {

        return candidate3Selected;
    }

    public void setCandidate3Selected(Boolean candidate3Selected) {

        this.candidate3Selected = candidate3Selected;
    }

    public Boolean getCandidate4Selected() {

        return candidate4Selected;
    }

    public void setCandidate4Selected(Boolean candidate4Selected) {

        this.candidate4Selected = candidate4Selected;
    }

    public Boolean getCandidate5Selected() {

        return candidate5Selected;
    }

    public void setCandidate5Selected(Boolean candidate5Selected) {

        this.candidate5Selected = candidate5Selected;
    }
}
