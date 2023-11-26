package com.example.backend.service;

import com.example.backend.controller.MemberRequest;
import com.example.backend.controller.MemberResponse;
import com.example.backend.controller.TeamRequest;
import com.example.backend.entity.Team;
import com.example.backend.repository.MemberRepository;
import com.example.backend.repository.TeamRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @AfterEach
    public void clear() {
        memberRepository.deleteAll();
        teamRepository.deleteAll();
    }

    @Test
    public void searchMemberByIdTest() {
        Team team = Team.builder().name("개발팀").location("서울").foundedDate("2023-11-01").members(new LinkedList<>()).build();
        teamRepository.saveAndFlush(team);
        MemberRequest request = MemberRequest.builder()
//                .id(1L)
                .firstName("윤")
                .lastName("지선")
                .address("경기도 고양시")
                .joinedDate("2023-11-20")
                .team(TeamRequest.builder().id(1L).name("개발팀").location("서울").foundedDate("2023-11-01").members(new LinkedList<>()).build())
                .build();
        memberService.createMember(request);

        MemberResponse memberResponse = memberService.searchMemberById(1L);
        assertThat(memberResponse.getFirstName()).isEqualTo(request.getFirstName());

    }

}