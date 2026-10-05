package com.howtodoinjava.mockito;

import java.util.Optional;

public interface BookRepository {

  Optional<Book> findByTitle(String title);

  void save(Book book);
}
