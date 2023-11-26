package com.example.backend.controller;

import com.example.backend.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* ID 기반 개별 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> searchMemberById(@RequestParam Long id) {
        return ResponseEntity.ok(memberService.searchMemberById(id));
    }

    /* 페이징 기반 Member 조회 */
    @GetMapping
    public ResponseEntity<PageResponse<MemberResponse>> searchMemberAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<MemberResponse> memberResponsePage = memberService.searchMemberAll(PageRequest.of(page, size));
        return ResponseEntity.ok(PageResponse.build(memberResponsePage, memberResponsePage.getNumber()
                                    , memberResponsePage.getSize(), memberResponsePage.getTotalPages()));
    }

    /* 검색 필터가 적용된 페이징 기반 Member 조회 */
    @GetMapping("/filter")
    public ResponseEntity<PageResponse<MemberResponse>> searchMemberAllByFiltering(@RequestParam("page") int page, @RequestParam("size") int size
            , @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
            , @RequestParam("address") String address, @RequestParam("joinedDate") String joinedDate){
        Page<MemberResponse> memberResponsePage = memberService.searchMemberAllByFiltering(PageRequest.of(page, size), firstName, lastName, address, joinedDate);
        return ResponseEntity.ok(PageResponse.build(memberResponsePage, memberResponsePage.getNumber()
                , memberResponsePage.getSize(), memberResponsePage.getTotalPages()));
    }

    /* Member 등록 */
    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.createMember(request));
    }

    /* Member 개별 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@RequestBody MemberRequest request, @RequestParam Long id) {
        return ResponseEntity.ok(memberService.updateMember(request, id));
    }

}