package com.example.library.Controllers;

import java.util.List;
import java.util.Map;

import com.example.library.DTO.AddBookDTO;
import com.example.library.DTO.BookDTO;
import com.example.library.Services.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class LibraryControllerImpl implements LibraryController {

    private final LibraryService libraryService;

    @Override
    public ResponseEntity<?> add(AddBookDTO addBookDTO) {
        BookDTO add = libraryService.add(addBookDTO);
        return ResponseEntity.ok(add);
    }

    @Override
    public ResponseEntity<?> getAllBooks() {
        List<?> allBooks = libraryService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @Override
    public ResponseEntity<?> getBooksByAuthor() {
        Map<?,?> booksByAuthor = libraryService.getBooksByAuthor();
        return ResponseEntity.ok(booksByAuthor);
    }

    @Override
    public ResponseEntity<?> findBooks(String value) {
        List<?> books = libraryService.findAuthorsWithMatchCount(value);
        return ResponseEntity.ok(books);
    }
}
