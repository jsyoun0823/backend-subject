package com.example.backend.entity;

import com.example.backend.controller.MemberRequest;
import com.example.backend.controller.MemberResponse;
import com.example.backend.controller.TeamRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String foundedDate;

    @NotNull
    @OneToMany
    private List<Member> members = new ArrayList<>();

    public void updateTeam(TeamRequest teamRequest) {
        this.id = teamRequest.getId();
        this.name = teamRequest.getName();
        this.location = teamRequest.getLocation();
        this.foundedDate = teamRequest.getFoundedDate();
        this.members = teamRequest.getMembers().stream().map(MemberRequest::toEntity).collect(Collectors.toList());
    }
}