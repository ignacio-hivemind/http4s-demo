package com.hivemind.app.repository

import cats.effect.IO
import com.hivemind.app.model.Book

object BookRepository {

  // generate a list of 4 books
  private val book1: Book = Book("Moby Dick", "Herman Melville")
  private val book2: Book = Book("Wuthering Heights", "Emily Bronte")

  def getBooks: IO[List[Book]] = IO {
    List(book1, book2)
  }
}
