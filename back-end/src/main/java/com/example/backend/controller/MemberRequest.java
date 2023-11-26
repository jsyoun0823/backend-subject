package com.example.backend.controller;

import com.example.backend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String joinedDate;

    private TeamRequest team;

    public static Member toEntity(MemberRequest request) {
        return Member.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .joinedDate(request.getJoinedDate())
                .team(TeamRequest.toEntity(request.getTeam()))
                .build();
    }
}