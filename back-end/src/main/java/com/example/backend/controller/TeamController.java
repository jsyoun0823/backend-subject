package com.example.backend.controller;

import com.example.backend.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /* ID 기반 개별 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> searchTeamById(@RequestParam Long id) {
        return ResponseEntity.ok(teamService.searchTeamById(id));
    }

    /* 페이징 기반 Team 조회 해당 - Team의 Member List으로 응답*/
    @GetMapping
    public ResponseEntity<PageResponse<TeamResponse>> searchTeamAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<TeamResponse> teamResponsePage = teamService.searchTeamAll(PageRequest.of(page, size));

        return ResponseEntity.ok(PageResponse.build(teamResponsePage, teamResponsePage.getNumber()
                , teamResponsePage.getSize(), teamResponsePage.getTotalPages()));
    }

    /* 검색 필터가 적용된 페이징 기반 Team 조회 */
    @GetMapping("/filter")
    public ResponseEntity<PageResponse<TeamResponse>> searchTeamAllByFiltering(@RequestParam("page") int page, @RequestParam("size") int size
            , @RequestParam("name") String name, @RequestParam("location") String location, @RequestParam("foundedDate") String foundedDate){
        Page<TeamResponse> teamResponsePage = teamService.searchTeamAllByFiltering(PageRequest.of(page, size), name, location, foundedDate);
        return ResponseEntity.ok(PageResponse.build(teamResponsePage, teamResponsePage.getNumber()
                , teamResponsePage.getSize(), teamResponsePage.getTotalPages()));
    }

    /* Team 등록 */
    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest request) {
        return ResponseEntity.ok(teamService.createTeam(request));
    }

    /* Team 개별 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> updateTeam(@RequestBody TeamRequest request, @RequestParam Long id) {
        return ResponseEntity.ok(teamService.updateTeam(request, id));
    }
}