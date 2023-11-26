package com.example.backend.service;

import com.example.backend.controller.MemberRequest;
import com.example.backend.controller.MemberResponse;
import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public MemberResponse searchMemberById(Long id) {
        return MemberResponse.build(memberRepository.findById(id).get());
    }


    @Override
    public Page<MemberResponse> searchMemberAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return new PageImpl<>(members.getContent().stream().map(MemberResponse::build).collect(Collectors.toList())
                , members.getPageable(), members.getTotalElements());
    }

    @Override
    public Page<MemberResponse> searchMemberAllByFiltering(Pageable pageable, String firstName, String lastName, String address, String joinedDate) {
        Page<Member> members = memberRepository.findAllByFiltering(pageable, firstName, lastName, address, joinedDate);
        return new PageImpl<>(members.getContent().stream().map(MemberResponse::build).collect(Collectors.toList())
                , members.getPageable(), members.getTotalElements());
    }

    @Override
    @Transactional
    public MemberResponse createMember(MemberRequest request) {
        return MemberResponse.build(memberRepository.save(MemberRequest.toEntity(request)));
    }

    @Override
    @Transactional
    public MemberResponse updateMember(MemberRequest request, Long id) {
        Member member = memberRepository.findById(id).get();
        member.updateMember(request);

        return MemberResponse.build(member);
    }
}