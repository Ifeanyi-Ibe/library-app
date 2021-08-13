package com.library.repositories;

import com.library.model.LibraryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRecordRepository extends JpaRepository<LibraryRecord, Long> {
    Optional<LibraryRecord> findByEmail(String email);
}
