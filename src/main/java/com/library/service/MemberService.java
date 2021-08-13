package com.library.service;

import com.library.Dtos.MemberDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    public ResponseEntity<?> getMembers();
    public ResponseEntity<?> getMember(Long id);
    public ResponseEntity<?> addMember(MemberDto memberDto);
    public ResponseEntity<?> update(Long id, MemberDto memberDto);
    public ResponseEntity<?> remove(Long id);
}
