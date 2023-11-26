package com.example.backend.service;

import com.example.backend.controller.TeamRequest;
import com.example.backend.controller.TeamResponse;
import com.example.backend.entity.Team;
import com.example.backend.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service

public class TeamServiceImpl implements TeamService{

    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamResponse searchTeamById(Long id) {
        return TeamResponse.build(teamRepository.findById(id).get());
    }

    @Override
    public Page<TeamResponse> searchTeamAll(Pageable pageable) {
        Page<Team> teams = teamRepository.findAll(pageable);
        return new PageImpl<>(teams.getContent().stream().map(TeamResponse::build).collect(Collectors.toList())
                , teams.getPageable(), teams.getTotalElements());
    }

    @Override
    public Page<TeamResponse> searchTeamAllByFiltering(Pageable pageable, String name, String location, String foundedDate) {
        Page<Team> teams = teamRepository.findAllByFiltering(pageable, name, location, foundedDate);
        return new PageImpl<>(teams.getContent().stream().map(TeamResponse::build).collect(Collectors.toList())
                , teams.getPageable(), teams.getTotalElements());
    }

    @Override
    public TeamResponse createTeam(TeamRequest request) {
        return TeamResponse.build(teamRepository.save(TeamRequest.toEntity(request)));
    }

    @Override
    @Transactional
    public TeamResponse updateTeam(TeamRequest request, Long id) {
        Team team = teamRepository.findById(id).get();
        team.updateTeam(request);

        return TeamResponse.build(team);
    }
}