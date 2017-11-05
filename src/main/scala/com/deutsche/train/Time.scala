package com.deutsche.train

import play.api.libs.json.{JsValue, Json}

import scala.annotation.tailrec
import scala.util.Try

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {

  require(hours >= 0 && hours <= 23, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes <= 59, "minutes must be within 0 and 59")

  def asMinutes: Int = hours * 60 + minutes

  def minus(that: Time) = this.asMinutes - that.asMinutes

  def -(that: Time) = minus(that)

  override def toString: String = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int = this - that

  def toJson: JsValue =
    Json.obj("hours" -> hours, "minutes" -> minutes)
}

object Time {
  def fromMinutes(minutes: Int): Time =
    new Time(minutes / 60, minutes % 60)

  def fromJson(json: JsValue): Option[Time] = {
    val time = for {
      hours <- Try((json \ "hours").as[Int])
      minutes <- Try((json \ "minutes").as[Int])
    } yield Time(hours, minutes)
    time.toOption
  }

  @tailrec
  def isIncreasing(times: Seq[Time]): Boolean = {
    times match {
      case t1 +: t2 +: _ => (t2 > t1) && isIncreasing(times.tail)
      case _ => true
    }
  }

}
