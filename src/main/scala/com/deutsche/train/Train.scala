package com.deutsche.train

case class Train(kind: String, number: Int, schedule: Seq[(Time, Station)]) {
  require(schedule.size >= 2, "schedule must contain at least two elements")

  val stations = schedule.map(_._2)

  def timeAt(station: Station): Option[Time] = schedule.find(timeAtStation => timeAtStation._2 == station).map(_._1)
}

case class Station(name: String)