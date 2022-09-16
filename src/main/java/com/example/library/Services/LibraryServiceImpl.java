package com.example.library.Services;

import com.example.library.DTO.AddBookDTO;
import com.example.library.DTO.BookDTO;
import com.example.library.Entities.Book;
import com.example.library.Repositories.LibraryRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Override
    public BookDTO add(AddBookDTO addBookDTO) {

        Book book = mapAddBookDTOToBook(addBookDTO);

        Book savedBook = libraryRepository.save(book);

        return mapBookToBookDTO(savedBook);
    }

    @Override
    public List<?> getAllBooks() {
        return libraryRepository.findAllByOrderByTitleDesc();
    }

    @Override
    public Map<?,?> getBooksByAuthor() {
        List<Book> all = libraryRepository.findAll();
        Map<String, List<Book>> booksWithAuthor = new HashMap<>();
        if (all.isEmpty())
            return booksWithAuthor;
        putBooksWithAuthor(all, booksWithAuthor);
        return booksWithAuthor;
    }

    @Override
    public List<?> findAuthorsWithMatchCount(String value) {
        List<Book> all = libraryRepository.findAll();

        Map<String, String> authorsWithBookTitles = new HashMap<>();

        putAuthorsWithBookTitles(all, authorsWithBookTitles);

        List<Map<String, Integer>> maps = new ArrayList<>();

        authorsWithBookTitles.forEach((author1, concatOfTitles) -> makeListOFResult(value, maps, author1, concatOfTitles));

        List<Map.Entry<String, Integer>> authorsWithMatchCount =
             maps.
                stream().
                    flatMap(stringIntegerMap -> stringIntegerMap.entrySet()
                        .stream()
                            .filter(stringIntegerEntry -> stringIntegerEntry
                                .getValue() > 0))
                                    .limit(10)
                                        .sorted((o1, o2) -> o2.getValue()- o1.getValue())
                                            .toList();

        return authorsWithMatchCount;
    }

    private static BookDTO mapBookToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setTitle(book.getTitle());
        return bookDTO;
    }

    private static Book mapAddBookDTOToBook(AddBookDTO addBookDTO) {
        Book book = new Book();
        book.setAuthor(addBookDTO.getAuthor());
        book.setDescription(addBookDTO.getDescription());
        book.setTitle(addBookDTO.getTitle());
        return book;
    }

    private static void makeListOFResult(String value, List<Map<String, Integer>> maps, String author1, String concatOfTitles) {
        int countOfOccurrences = 0;
        for (int i = 0; i < concatOfTitles.length(); i++) {
            if (Character.toLowerCase(value.charAt(0)) == Character.toLowerCase(concatOfTitles.charAt(i)))
                countOfOccurrences++;
        }

        Map<String, Integer> result = new HashMap<>();
        result.put(author1, countOfOccurrences);
        maps.add(result);
    }

    private static void putAuthorsWithBookTitles(List<Book> all, Map<String, String> authorsWithBookTitles) {
        String author;
        for (Book book : all) {
            author = book.getAuthor();

            if (Objects.isNull(authorsWithBookTitles.get(author)))
                authorsWithBookTitles.put(author, book.getTitle());
            else {
                authorsWithBookTitles.put(author, authorsWithBookTitles.get(author) + book.getTitle());
            }

        }
    }

    private static void putBooksWithAuthor(List<Book> all, Map<String, List<Book>> bookMap) {
        for (Book book : all) {
            if(Objects.isNull(bookMap.get(book.getAuthor()))){
                ArrayList<Book> books = new ArrayList<>();
                books.add(book);
                bookMap.put(book.getAuthor(),books);
            }
            else {
                List<Book> books = bookMap.get(book.getAuthor());
                books.add(book);
                bookMap.put(book.getAuthor(), books);

            }

        }
    }
}
