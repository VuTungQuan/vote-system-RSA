package com.example.demo.controller;

import com.example.demo.dto.Election;
import com.example.demo.dto.ElectionVoter;
import com.example.demo.dto.VoteModel;
import com.example.demo.dto.Voter;
import com.example.demo.service.ElectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {

    private final ElectionService electionService;
    @Autowired
    public DemoController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Voter voter) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid Voter voter, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "redirect:/signup";
        }
        String voterId = electionService.addNewVoter(voter);
        if(voterId.startsWith("ERROR")) {
            return "redirect:/vote_error?p=signup";
        }
        return "redirect:/vote?vid=" + voter.getPersonalId();
    }

    @GetMapping("/vote")
    public String showVotingForm(@RequestParam("vid") String voterId, Model model) {
        Election election = electionService.getActiveElection();
        if(election != null) {
            Voter voter = electionService.getVoterById(voterId);
            model.addAttribute("election", election);
            model.addAttribute("voter", voter);
            model.addAttribute("voteModel", new VoteModel());
            return "vote";
        }
        return "no_election";
    }

    @PostMapping("/vote")
    public String doVote(@Valid VoteModel voteModel) {
        if(electionService.updateVote(voteModel)) {
            return "redirect:/vote_ok";
        }
        return "redirect:/vote_error?p=vote";
    }

    @GetMapping("/vote_ok")
    public String showThankfulPage() {
        return "vote_ok";
    }

    @GetMapping("/vote_error")
    public String showError(@RequestParam(name = "p") String page, Model model) {
        String message = "Sorry you are not authored to vote";
        if(page.equals("signup")) {
            message = "There is a problem which sign in data. You signature is not VALID. Please check.";
        }
        model.addAttribute("message", message);
        return "vote_error";
    }

    @GetMapping("/admin")
    public String showAdmin(Election election) {
        return "admin";
    }

    @GetMapping("/election_info")
    public String showElection(@RequestParam(value = "v", required = false) Long id, Model model) {

        Election election;
        election = id == null ? electionService.getActiveElection() : electionService.getElectionById(id);
        if(election != null) {
            model.addAttribute("election", election);
            return "election_info";
        }

        return "no_election";
    }

    @PostMapping("/election/save")
    public String saveElection(Election election) {
        electionService.addElection(election);
        return "redirect:/election_info";
    }
}
