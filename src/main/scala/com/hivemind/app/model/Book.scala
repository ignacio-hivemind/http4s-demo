package com.hivemind.app.model

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class Book(title: String, author: String)

object Book {
  // generate circe codecs for Book
  given Codec[Book] = deriveCodec[Book]
}
