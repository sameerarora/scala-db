package com.deutsche.train

class JourneyPlanner(val trains: Set[Train]) {

  val stations = trains.flatMap(_.stations)

  def trainsAt(station: Station) = trains.filter(t => t.stations.contains(station))

  def stopsAt(station: Station): Set[(Time, Train)] = for {
    train <- trainsAt(station)
    (time, `station`) <- train.schedule
  } yield (time, train)

  def isShortTrip(from: Station, to: Station) = {
    trains.exists(train =>
      train.stations.dropWhile(station => station != from) match {
        case `from` +: `to` +: _ => true
        case `from` +: _ +: `to` +: _ => true
        case _ => false
      }
    )
  }
}
