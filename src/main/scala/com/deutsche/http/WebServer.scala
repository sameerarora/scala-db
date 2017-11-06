package com.deutsche.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.deutsche.http.routes.TrainHttpRoutes
import com.deutsche.repository.TrainRepository

import scala.io.StdIn

object WebServer extends TrainHttpRoutes {

  def loadTestData() = {
    TrainRepository.save(rajdhani112)
    TrainRepository.save(rajdhani415)
  }

  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
    loadTestData()
    val route =
      path("ping") {
        get {
          complete("pong")
          //complete(HttpEntity(ContentTypes.`application/json`, Time(14, 30)))
        }
      } ~ trainRoutes

    val port = 9198
    val bindingFuture = Http().bindAndHandle(route, "localhost", port)

    println(s"Server online at http://localhost:$port/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

}
