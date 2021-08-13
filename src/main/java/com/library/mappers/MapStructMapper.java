package com.library.mappers;


import com.library.Dtos.AuthorDto;
import com.library.Dtos.BookDto;
import com.library.Dtos.LibraryRecordDto;
import com.library.Dtos.MemberDto;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.LibraryRecord;
import com.library.model.Member;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MapStructMapper {
    Member memberDtoToMember(MemberDto memberDto);
    MemberDto memberToMemberDto(Member member);
    Author authorDtoToAuthor(AuthorDto authorDto);
    AuthorDto authorToAuthorDto(Author author);
    Book bookDtoToBook(BookDto bookDto);
    BookDto bookToBookDto(Book book);
    List<BookDto> booksToBookDtos(List<Book> books);
    List<AuthorDto> authorsToAuthorDtos(List<Author> authors);
    LibraryRecord recordToRecordDto(LibraryRecordDto libraryRecordDto);
    List<MemberDto> membersToMemberDtos(List<Member> members);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(BookDto dto, @MappingTarget Book entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAuthorFromDto(AuthorDto dto, @MappingTarget Author entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMemberFromDto(MemberDto dto, @MappingTarget Member entity);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    List<LibraryRecordDto> libraryRecordsToDto(LibraryRecord libraryRecord, @MappingTarget LibraryRecordDto recordDto);

}
