package com.deutsche.http.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.deutsche.train.{Station, Time, Train}
import spray.json._

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val printer = PrettyPrinter
  implicit val timeFormat = jsonFormat2(Time.apply)
  implicit val stationFormat = jsonFormat1(Station)
  implicit val trainFormat = jsonFormat(Train, "kind", "number", "schedule")

}

