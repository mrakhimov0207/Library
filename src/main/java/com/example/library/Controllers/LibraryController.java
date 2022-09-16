package com.example.library.Controllers;

import com.example.library.DTO.AddBookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public interface LibraryController {

    @PostMapping
    ResponseEntity<?> add(@RequestBody @Valid AddBookDTO addBookDTO);

    @GetMapping
    ResponseEntity<?> getAllBooks();

    @GetMapping("/getByAuthor")
    ResponseEntity<?> getBooksByAuthor();

    @GetMapping("/find-matched/{value}")
    ResponseEntity<?> findBooks(@PathVariable String value);


}
