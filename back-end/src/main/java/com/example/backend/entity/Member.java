package com.example.backend.entity;

import com.example.backend.controller.MemberRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String address;

    @NotNull
    private String joinedDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public void updateMember(MemberRequest memberRequest) {
        this.firstName = memberRequest.getFirstName();
        this.lastName = memberRequest.getLastName();
        this.address = memberRequest.getAddress();
        this.joinedDate = memberRequest.getJoinedDate();
    }
}