package com.example.library.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.Entities.Book;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderByTitleDesc();
}
