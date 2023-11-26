package com.example.backend.controller;

import com.example.backend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private int pageNo;
    private int pageSize;
    private int totalSize;

    public static <T> PageResponse<T> build(Page<T> contents, int pageNo, int pageSize, int totalSize) {
        return (PageResponse<T>) PageResponse.builder()
                .content(contents.get().collect(Collectors.toList()))
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalSize(totalSize)
                .build();
    }

}