package com.deutsche.train

import com.deutsche.train.TestData._
import org.scalatest.{Matchers, WordSpec}


class JourneyPlannerSpec extends WordSpec with Matchers {

  "stations" should {
    "be initialized correctly" in {
      planner.stations shouldEqual Set(delhi, jaipur, ahmedabad, mumbai, bangalore)
    }
  }

  "Calling trainsAt" should {
    "return the correct trains" in {
      planner.trainsAt(delhi) shouldEqual Set(rajdhani112, rajdhani415)
      planner.trainsAt(jaipur) shouldEqual Set(rajdhani112)
    }
  }

  "Calling stopsAt" should {
    "return the correct stops" in {
      planner.stopsAt(mumbai) shouldEqual Set(rajdhani112MumbaiTime -> rajdhani112,
        rajdhani415MumbaiTime -> rajdhani415)
    }
  }

  "Calling isShortTrip" should {
    "return true for at most one station in between" in {
      planner.isShortTrip(delhi, jaipur) shouldBe true
      planner.isShortTrip(delhi, mumbai) shouldBe true
    }
    "return false for more than one stations in between" in {
      planner.isShortTrip(delhi, bangalore) shouldBe false
    }
  }

}
