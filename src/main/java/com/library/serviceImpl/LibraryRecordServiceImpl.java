package com.library.serviceImpl;

import com.library.Dtos.LibraryRecordDto;
import com.library.enums.BookStatus;
import com.library.model.Book;
import com.library.model.Member;
import com.library.repositories.LibraryRecordRepository;
import com.library.repositories.MemberRepository;
import com.library.service.LibraryRecordService;
import com.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class LibraryRecordServiceImpl implements LibraryRecordService {

    @Autowired
    private LibraryRecordRepository recordRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;



    @Override
    public LibraryRecordDto addRecord(Long id, Book book) {
        LibraryRecordDto libraryRecordDto = new LibraryRecordDto();
        Member member = memberRepository.findById(id).get();
        libraryRecordDto.setId(id);
        libraryRecordDto.setBorrower(member.getFirstName());
        libraryRecordDto.setBook(book.getTitle());
        libraryRecordDto.setStatus(BookStatus.PENDING.name());
        libraryRecordDto.setDate(Instant.now());
        return libraryRecordDto;
    }

}
