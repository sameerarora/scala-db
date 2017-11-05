package com.deutsche.train

import java.lang.{IllegalArgumentException => IAE}

import org.scalatest.{Matchers, WordSpec}
import TestData._


class TrainSpec extends WordSpec with Matchers {

  "Creating a Train" should {
    "throw an IllegalArgumentException for a schedule with 0 or 1 elements" in {
      an[IAE] should be thrownBy Train("Rajdhani", 112, Vector())
      an[IAE] should be thrownBy Train("Rajdhani", 112, Vector(rajdhani112DelhiTime -> delhi))
    }
  }

  "stations" should {
    "be initialized correctly" in {
      rajdhani112.stations shouldEqual Vector(delhi, jaipur, mumbai, bangalore)
    }
  }

  "Train Rajdhani 112" should {
    "stop in Jaipur" in {
      rajdhani112.timeAt(jaipur) shouldEqual Some(rajdhani112JaipurTime)
    }
    "not stop in Ahmedabad" in {
      rajdhani112.timeAt(ahmedabad) shouldEqual None
    }
  }

}
