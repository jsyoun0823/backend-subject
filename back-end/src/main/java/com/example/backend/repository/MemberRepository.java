package com.example.backend.repository;

import com.example.backend.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    Page<Member> findAll(Pageable pageable);

    @Query(value = "select m from Member m where m.firstName = :firstName and m.lastName = :lastName and m.address = :address and m.joinedDate = :joinedDate")
    Page<Member> findAllByFiltering(Pageable pageable, @Param("firstName") String firstName, @Param("lastName") String lastName,
                                    @Param("address") String address, @Param("joinedDate") String joinedDate);

    Member save(Member member);
}
