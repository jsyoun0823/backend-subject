package com.example.backend.repository;

import com.example.backend.entity.Member;
import com.example.backend.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findById(Long id);

    Page<Team> findAll(Pageable pageable);

    @Query(value = "select t from Team t where t.name = :name and t.location = :location and t.foundedDate = :foundedDate")
    Page<Team> findAllByFiltering(Pageable pageable, @Param("name") String name, @Param("location") String location, @Param("foundedDate") String foundedDate);

    Team save(Team Team);
}
