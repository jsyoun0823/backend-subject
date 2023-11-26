package com.example.backend.service;

import com.example.backend.controller.MemberRequest;
import com.example.backend.controller.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    MemberResponse searchMemberById(Long id);
    Page<MemberResponse> searchMemberAll(Pageable pageable);
    Page<MemberResponse> searchMemberAllByFiltering(Pageable pageable, String firstName, String lastName, String address, String joinedDate);
    MemberResponse createMember(MemberRequest request);
    MemberResponse updateMember(MemberRequest request, Long id);
}