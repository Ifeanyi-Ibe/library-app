package com.library.service;

import com.library.Dtos.LibraryRecordDto;
import com.library.model.Book;

import java.util.List;

public interface LibraryRecordService {
    public LibraryRecordDto addRecord(Long id, Book book);
    //List<LibraryRecordDto> getRecords();

}
