package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "elections")
public class ElectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "election_id_generator")
    @SequenceGenerator(name = "election_id_generator", sequenceName = "election_id_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String candidate1;
    private int numOfVote1;
    private String candidate2;
    private int numOfVote2;
    private String candidate3;
    private int numOfVote3;
    private String candidate4;
    private int numOfVote4;
    private String candidate5;
    private int numOfVote5;
    private Boolean status;

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

    public String getCandidate1() {
        return candidate1;
    }

    public void setCandidate1(String candidate1) {
        this.candidate1 = candidate1;
    }

    public int getNumOfVote1() {
        return numOfVote1;
    }

    public void setNumOfVote1(int numOfVote1) {

        this.numOfVote1 = numOfVote1;
    }

    public String getCandidate2() {
        return candidate2;
    }

    public void setCandidate2(String candidate2) {
        this.candidate2 = candidate2;
    }

    public int getNumOfVote2() {
        return numOfVote2;
    }

    public void setNumOfVote2(int numOfVote2) {
        this.numOfVote2 = numOfVote2;
    }

    public String getCandidate3() {
        return candidate3;
    }

    public void setCandidate3(String candidate3) {
        this.candidate3 = candidate3;
    }

    public int getNumOfVote3() {
        return numOfVote3;
    }

    public void setNumOfVote3(int numOfVote3) {
        this.numOfVote3 = numOfVote3;
    }

    public String getCandidate4() {
        return candidate4;
    }

    public void setCandidate4(String candidate4) {
        this.candidate4 = candidate4;
    }

    public int getNumOfVote4() {
        return numOfVote4;
    }

    public void setNumOfVote4(int numOfVote4) {
        this.numOfVote4 = numOfVote4;
    }

    public String getCandidate5() {
        return candidate5;
    }

    public void setCandidate5(String candidate5) {
        this.candidate5 = candidate5;
    }

    public int getNumOfVote5() {
        return numOfVote5;
    }

    public void setNumOfVote5(int numOfVote5) {
        this.numOfVote5 = numOfVote5;
    }

    public Boolean getStatus() {

        return status;
    }

    public void setStatus(Boolean status) {

        this.status = status;
    }
}
