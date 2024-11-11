package com.hivemind.app

import cats.effect.*
import org.http4s.circe.CirceEntityEncoder.*
import cats.syntax.all.*
import com.comcast.ip4s.{host, port, Host, Port}
import org.http4s.circe.*
import org.http4s.dsl.io.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.{HttpRoutes, Response}
import com.hivemind.app.model.Book
import com.hivemind.app.repository.BookRepository
import org.typelevel.log4cats.slf4j.Slf4jFactory // Import for log4cats

object Main extends IOApp.Simple {
  // Implicit logger factory for Ember server
  implicit val loggerFactory: Slf4jFactory[IO] = Slf4jFactory.create[IO]

  // A simple route that responds with a plain text greeting
  private val helloWorldRoute = HttpRoutes.of[IO] { case GET -> Root / "hello" =>
    Ok("Hello, world!")
  }

  // A route that returns a list of books as JSON
  private val booksRoute = HttpRoutes.of[IO] { case GET -> Root / "books" =>
    Ok(BookRepository.getBooks)
  }

  // Combine routes
  private val httpApp = (helloWorldRoute <+> booksRoute).orNotFound

  // Define the port and host
  private val myPort: Port = port"8080"
  private val myHost: Host = host"localhost"

  val run: IO[Nothing] =
    EmberServerBuilder
      .default[IO]
      .withHost(myHost)
      .withPort(myPort)
      .withHttpApp(httpApp)
      .build
      .useForever
}
