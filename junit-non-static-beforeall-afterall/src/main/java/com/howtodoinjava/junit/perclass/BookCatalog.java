package com.howtodoinjava.junit.perclass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A read-only book catalog loaded from a CSV file with lines such as "Emma,Jane Austen".
 */
public class BookCatalog {

  private final List<Book> books;

  private BookCatalog(List<Book> books) {
    this.books = List.copyOf(books);
  }

  public static BookCatalog load(Path csvFile) throws IOException {
    List<Book> books = Files.readAllLines(csvFile).stream()
        .map(line -> line.split(",", 2))
        .map(parts -> new Book(parts[0].strip(), parts[1].strip()))
        .toList();
    return new BookCatalog(books);
  }

  public List<String> titlesBy(String author) {
    return books.stream().filter(b -> b.author().equals(author)).map(Book::title).toList();
  }

  public int size() {
    return books.size();
  }
}
