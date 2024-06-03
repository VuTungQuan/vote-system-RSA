package com.example.demo;

import com.example.demo.domain.entity.ElectionEntity;
import com.example.demo.domain.entity.VoterEntity;
import com.example.demo.dto.Candidate;
import com.example.demo.dto.Election;
import com.example.demo.dto.Voter;

public final class MapperUtil {

    public static Election toElection(ElectionEntity entity) {
        if(entity == null) {
            return null;
        }

        Election election = new Election(entity.getId());
        election.setName(entity.getName());
        election.setStatus(entity.getStatus());
        election.setCandidate1(new Candidate(entity.getCandidate1(), entity.getNumOfVote1()));
        election.setCandidate2(new Candidate(entity.getCandidate2(), entity.getNumOfVote2()));
        election.setCandidate3(new Candidate(entity.getCandidate3(), entity.getNumOfVote3()));
        election.setCandidate4(new Candidate(entity.getCandidate4(), entity.getNumOfVote4()));
        election.setCandidate5(new Candidate(entity.getCandidate5(), entity.getNumOfVote5()));

        return election;
    }

    public static Voter toVoterDTO(VoterEntity entity) {
        if(entity == null) {
            return null;
        }

        Voter voter = new Voter();
        voter.setName(entity.getName());
        voter.setEmail(entity.getEmail());
        voter.setPersonalId(entity.getPersonalId());
        return voter;
    }
}
