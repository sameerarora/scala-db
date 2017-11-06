package com.deutsche.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.deutsche.http.json.JsonSupport
import com.deutsche.repository.TrainRepository
import com.deutsche.train.Train

import scala.util.{Failure, Success, Try}

trait TrainHttpRoutes extends JsonSupport {

  lazy val trainRoutes: Route =
    pathPrefix("train") {
      path("all") {
        get {
          complete {
            TrainRepository.listAll
          }
        }
      } ~
        post {
          entity(as[Train]) { train =>
            complete {
              TrainRepository.save(train)
              s"Successfully Added Train ${train.number} to Route"
            }
          }
        } ~
        put {
          entity(as[Train]) { train =>
            complete {
              TrainRepository.update(train)
              s"Successfully updated Train ${train.number}"
            }
          }
        } ~
        path(Segment) { trainId =>
          delete {
            complete {
              TrainRepository.delete(Integer.parseInt(trainId))
              s"Successfully deleted Train $trainId"
            }
          }
        }~
        path(Segment) { trainId =>
          get {
            complete {
              Try(TrainRepository.findByNumber(Integer.parseInt(trainId))) match {
                case Success(r) => r
                case Failure(ex) => StatusCodes.NotFound -> "The requested resource could not be found on the server"
              }
            }
          }
        }
    }
}
