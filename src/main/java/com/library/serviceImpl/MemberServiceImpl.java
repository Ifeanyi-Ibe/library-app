package com.library.serviceImpl;

import com.library.Dtos.MemberDto;
import com.library.mappers.MapStructMapper;
import com.library.model.Member;
import com.library.repositories.MemberRepository;
import com.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final MapStructMapper mapper;

    @Override
    public ResponseEntity<?> getMembers() {
        List<MemberDto> members = mapper.membersToMemberDtos(memberRepository.findAll());
        if(members.isEmpty()) {
            return ResponseEntity.status(404).body("No members were found.");
        }
        return ResponseEntity.ok(members);
    }

    @Override
    public ResponseEntity<?> getMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) {
            return ResponseEntity.status(404).body("Member with id: " + id + " does not exist.");
        }
        return ResponseEntity.ok(member.get());
    }

    @Override
    public ResponseEntity<?> addMember(MemberDto memberDto) {
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());
        if(member.isPresent()) {
            return ResponseEntity.status(422).body("Member already exists");
        }

        memberRepository.save(mapper.memberDtoToMember(memberDto));
        return ResponseEntity.ok("Member added successfully.");
    }

    @Override
    public ResponseEntity<?> update(Long id, MemberDto memberDto) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            return ResponseEntity.status(404).body("Member with id: " + id + " does not exist.");
        }
        mapper.updateMemberFromDto(memberDto, member.get());
        memberRepository.save(member.get());
        return ResponseEntity.ok(member.get());
    }

    @Override
    public ResponseEntity<?> remove(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(memberRepository::delete);
        return ResponseEntity.ok("Member successfully removed.");
    }


}
