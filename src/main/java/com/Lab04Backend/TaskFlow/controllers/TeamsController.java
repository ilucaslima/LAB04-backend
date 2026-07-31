package com.Lab04Backend.TaskFlow.controllers;

import com.Lab04Backend.TaskFlow.team.dto.TeamsRequest;
import com.Lab04Backend.TaskFlow.team.dto.TeamsResponse;
import com.Lab04Backend.TaskFlow.team.services.TeamsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/teams")
public class TeamsController {

    private final TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @PostMapping
    ResponseEntity<TeamsResponse> createTeam(@RequestBody TeamsRequest request) {
        return ResponseEntity.ok().body(teamsService.createTeam(request));
    }

    @PostMapping("/{teamId}/add/{memberId}")
    ResponseEntity<TeamsResponse> addMemberToTeam(@PathVariable UUID teamId, @PathVariable UUID memberId) {
        return ResponseEntity.ok().body(teamsService.addToTeam(teamId, memberId));
    }

    @DeleteMapping("/{teamId}/remove/{memberId}")
    ResponseEntity<Void> removeMemberFromTeam(@PathVariable UUID teamId, @PathVariable UUID memberId) {
        teamsService.removeFromTeam(teamId, memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    ResponseEntity<List<TeamsResponse>> findAll() {
        return ResponseEntity.ok().body(teamsService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<TeamsResponse> findOne(@PathVariable UUID id) {
        return ResponseEntity.ok().body(teamsService.findOne(id));
    }

    @PutMapping
    ResponseEntity<TeamsResponse> update(@RequestBody TeamsRequest request) {
        return ResponseEntity.ok().body(teamsService.update(request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        teamsService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
