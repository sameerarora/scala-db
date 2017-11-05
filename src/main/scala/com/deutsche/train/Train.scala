package com.deutsche.train

import com.deutsche.train.Time.isIncreasing

case class Train(kind: String, number: Int, schedule: Seq[(Time, Station)]) {
  require(schedule.size >= 2, "schedule must contain at least two elements")
  require(isIncreasing(schedule.map(_._1)), "Schedule of train must be in an increasing order of time")

  val stations = schedule.map(_._2)

  def timeAt(station: Station): Option[Time] = schedule.find(timeAtStation => timeAtStation._2 == station).map(_._1)
}

case class Station(name: String)