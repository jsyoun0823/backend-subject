package com.example.backend.controller;

import com.example.backend.entity.Team;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class TeamRequest {

    private Long id;

    private String name;

    private String location;

    private String foundedDate;

    private List<MemberRequest> members = new ArrayList<>();

    public static Team toEntity(TeamRequest request) {
        return Team.builder()
                .id(request.getId())
                .name(request.getName())
                .location(request.getLocation())
                .foundedDate(request.getFoundedDate())
                .members(request.getMembers().stream().map(MemberRequest::toEntity).collect(Collectors.toList()))
                .build();
    }
}