package com.example.backend.controller;

import com.example.backend.entity.Member;
import com.example.backend.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String joinedDate;

    private TeamResponse team;

    public static MemberResponse build(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .address(member.getAddress())
                .joinedDate(member.getJoinedDate())
                .team(TeamResponse.build(member.getTeam()))
                .build();
    }
}