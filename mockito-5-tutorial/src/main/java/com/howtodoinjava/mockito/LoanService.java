package com.howtodoinjava.mockito;

public class LoanService {

  private final BookRepository repository;
  private final Notifier notifier;

  public LoanService(BookRepository repository, Notifier notifier) {
    this.repository = repository;
    this.notifier = notifier;
  }

  // Returns true when the book is lent, false when it is already out
  public boolean borrow(String member, String title) {
    Book book = repository.findByTitle(title)
        .orElseThrow(() -> new IllegalArgumentException("No such book: " + title));
    if (!book.available()) {
      return false;
    }
    repository.save(new Book(title, false));
    notifier.send(member, "You borrowed " + title);
    return true;
  }

  public String receipt(String title) {
    return "Loan " + LoanIds.next() + " for " + title;
  }
}
