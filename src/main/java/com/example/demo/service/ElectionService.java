package com.example.demo.service;

import com.example.demo.MapperUtil;
import com.example.demo.domain.entity.ElectionEntity;
import com.example.demo.domain.entity.VoterEntity;
import com.example.demo.domain.repository.ElectionRepository;
import com.example.demo.domain.repository.ElectionVoteRepository;
import com.example.demo.domain.repository.VoterRepository;
import com.example.demo.dto.Election;
import com.example.demo.dto.VoteModel;
import com.example.demo.dto.Voter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Optional;
import java.util.Base64;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;
    private final VoterRepository voterRepository;
    private final ElectionVoteRepository electionVoteRepository;

    @Autowired
    public ElectionService(ElectionRepository electionRepository,
            VoterRepository voterRepository,
            ElectionVoteRepository electionVoteRepository) {
        this.electionRepository = electionRepository;
        this.voterRepository = voterRepository;
        this.electionVoteRepository = electionVoteRepository;
    }

    public Long addElection(Election election) {

        ElectionEntity entity = new ElectionEntity();
        entity.setName(election.getName());
        entity.setCandidate1(election.getCandidate1().getName());
        entity.setCandidate2(election.getCandidate2().getName());
        entity.setCandidate3(election.getCandidate3().getName());
        entity.setCandidate4(election.getCandidate4().getName());
        entity.setCandidate5(election.getCandidate5().getName());
        entity.setStatus(true);

        ElectionEntity savedEntity = electionRepository.save(entity);
        return savedEntity.getId();
    }

    public Election getActiveElection() {

        List<ElectionEntity> activeElections = electionRepository.findByStatusTrue();
        return activeElections.stream().findFirst()
                .map(MapperUtil::toElection)
                .orElse(null);
    }

    public Election getElectionById(Long id) {
        Optional<ElectionEntity> electionOpt = electionRepository.findById(id);
        return electionOpt
                .map(MapperUtil::toElection)
                .orElse(null);
    }

    public String addNewVoter(Voter voter) {

        Optional<VoterEntity> existed = voterRepository.findById(voter.getPersonalId());
        if(existed.isPresent()) {
            VoterEntity existedEntity = existed.get();
            if(existedEntity.getName().equals(voter.getName())) {
                try {
                    KeyPair rsaKeys = generateRSAKeys();
                    String privateKeyBase64 = Base64.getEncoder().encodeToString(rsaKeys.getPrivate().getEncoded());
                    String publicKeyBase64 = Base64.getEncoder().encodeToString(rsaKeys.getPublic().getEncoded());
                    existedEntity.setPrivateKey(privateKeyBase64);
                    existedEntity.setPublicKey(publicKeyBase64);
                    VoterEntity savedEntity = voterRepository.save(existedEntity);
                    return savedEntity.getPersonalId();
                } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
                    return "ERROR_GenKeys";
                }
            }
            return "ERROR_Conflict";
        }

        VoterEntity entity = new VoterEntity();
        entity.setName(voter.getName());
        entity.setPersonalId(voter.getPersonalId());
        entity.setEmail(voter.getEmail());
        try {
            KeyPair rsaKeys = generateRSAKeys();
            String privateKeyBase64 = Base64.getEncoder().encodeToString(rsaKeys.getPrivate().getEncoded());
            String publicKeyBase64 = Base64.getEncoder().encodeToString(rsaKeys.getPublic().getEncoded());
            entity.setPrivateKey(privateKeyBase64);
            entity.setPublicKey(publicKeyBase64);
            VoterEntity savedEntity = voterRepository.save(entity);
            return savedEntity.getPersonalId();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            return "ERROR_GenKeys";
        }
    }

    private KeyPair generateRSAKeys() throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        SecureRandom random = new SecureRandom();
        generator.initialize(2048, random);

        return generator.generateKeyPair();
    }

    private byte[] sign(PrivateKey privateKey, String data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(data.getBytes(StandardCharsets.UTF_8));
        return privateSignature.sign();

        // To get the signature
        // System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }

    private boolean verifySignature(PublicKey publicKey, byte[] signature, String data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(data.getBytes(StandardCharsets.UTF_8));
        return publicSignature.verify(signature);
    }

    public Voter getVoterById(String voterId) {
        return voterRepository.findById(voterId)
                .map(MapperUtil::toVoterDTO)
                .orElse(null);
    }

    public boolean updateVote(VoteModel voteModel) {
        Optional<ElectionEntity> electionEntityOpt = electionRepository.findById(voteModel.getElectionId());
        if(electionEntityOpt.isEmpty()) {
            return false;
        }

        Optional<VoterEntity> voterOpt = voterRepository.findById(voteModel.getVoterId());
        if(voterOpt.isEmpty()) {
            return false;
        }

        ElectionEntity election = electionEntityOpt.get();
        VoterEntity voter = voterOpt.get();

        String selectedCandidate = "WHITE-TICKET";
        //Sign vote
        if(voteModel.getCandidate1Selected()) {
            selectedCandidate = election.getCandidate1();
        }
        if(voteModel.getCandidate2Selected()) {
            selectedCandidate = election.getCandidate2();
        }
        if(voteModel.getCandidate3Selected()) {
            selectedCandidate = election.getCandidate3();
        }
        if(voteModel.getCandidate4Selected()) {
            selectedCandidate = election.getCandidate4();
        }
        if(voteModel.getCandidate5Selected()) {
            selectedCandidate = election.getCandidate5();
        }
        byte[] privateKeyBytes = Base64.getDecoder().decode(voter.getPrivateKey());
        byte[] publicKeyBytes = Base64.getDecoder().decode(voter.getPublicKey());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA", "BC");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            byte[] signature = sign(privateKey, selectedCandidate);
            //Verify RSA
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            if(verifySignature(publicKey, signature, selectedCandidate)) {
                //Update election vote count
                if(voteModel.getCandidate1Selected()) {
                    election.setNumOfVote1(election.getNumOfVote1() + 1);
                }
                if(voteModel.getCandidate2Selected()) {
                    election.setNumOfVote2(election.getNumOfVote2() + 1);
                }
                if(voteModel.getCandidate3Selected()) {
                    election.setNumOfVote3(election.getNumOfVote3() + 1);
                }
                if(voteModel.getCandidate4Selected()) {
                    election.setNumOfVote4(election.getNumOfVote4() + 1);
                }
                if(voteModel.getCandidate5Selected()) {
                    election.setNumOfVote5(election.getNumOfVote5() + 1);
                }
                electionRepository.save(election);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException | SignatureException | InvalidKeyException e) {
            return false;
        }

        return true;
    }
}
