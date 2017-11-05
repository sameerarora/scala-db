package com.deutsche.train

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {

  require(hours >= 0 && hours <= 23, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes <= 59, "minutes must be within 0 and 59")

  def asMinutes: Int = hours * 60 + minutes

  def minus(that: Time) = this.asMinutes - that.asMinutes

  def -(that: Time) = minus(that)

  override def toString: String = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int = this - that
}

object Time {
  def fromMinutes(minutes: Int): Time =
    new Time(minutes / 60, minutes % 60)
}
