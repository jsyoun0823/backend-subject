package com.example.backend.service;

import com.example.backend.controller.TeamRequest;
import com.example.backend.controller.TeamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    TeamResponse searchTeamById(Long id);
    Page<TeamResponse> searchTeamAll(Pageable pageable);
    Page<TeamResponse> searchTeamAllByFiltering(Pageable pageable, String name, String location, String foundedDate);
    TeamResponse createTeam(TeamRequest request);
    TeamResponse updateTeam(TeamRequest request, Long id);
}
