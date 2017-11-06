package com.deutsche.repository

import com.deutsche.train.Train

object TrainRepository {

  var store = Map[Int, Train]()

  def save(train: Train) = store += (train.number -> train)

  def findByNumber(id: Int) = store.get(id) match {
    case Some(t) => t
    case None => throw new IllegalStateException("Requested train could not be found in database...")
  }

  def update(train: Train) = {
    val t = findByNumber(train.number)
    if (t != null) {
      store -= (t.number)
      store += (train.number -> train)
    } else {
      throw new IllegalStateException("Requested train could not be found in database...")
    }
  }

  def delete(number: Int) = store -= number

  def listAll = store.toList.map(_._2)

}
