package com.example.backend.controller;

import com.example.backend.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamResponse {

    private Long id;

    private String name;

    private String location;

    private String foundedDate;

    private List<MemberResponse> members = new ArrayList<>();

    public static TeamResponse build(Team team) {
        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .location(team.getLocation())
                .foundedDate(team.getFoundedDate())
                .members(team.getMembers().stream().map(t -> MemberResponse.build(t)).collect(Collectors.toList()))
                .build();
    }
}