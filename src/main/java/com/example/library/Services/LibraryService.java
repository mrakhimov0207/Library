package com.example.library.Services;

import com.example.library.DTO.AddBookDTO;
import com.example.library.DTO.BookDTO;

import java.util.List;
import java.util.Map;

public interface LibraryService {


    BookDTO add(AddBookDTO addBookDTO);

    List<?> getAllBooks();

    Map<?,?> getBooksByAuthor();

    List<?> findAuthorsWithMatchCount(String value);
}
