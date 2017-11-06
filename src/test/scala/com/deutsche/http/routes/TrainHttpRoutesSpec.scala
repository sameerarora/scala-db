package com.deutsche.http.routes

import akka.http.scaladsl.model.{HttpRequest, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.deutsche.http.{rajdhani112, rajdhani415}
import com.deutsche.repository.TrainRepository
import org.scalatest.{Matchers, WordSpec}

class TrainHttpRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest with TrainHttpRoutes {

  lazy val routes = trainRoutes

  "Train Routes" should {
    "be able to retrieve all trains " in {
      TrainRepository.save(rajdhani415)
      val request = HttpRequest(uri = "/train/all")
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
        entityAs[String] should ===("[{\n  \"kind\": \"Rajdhani\",\n  \"number\": 415,\n  \"schedule\": [[{\n    \"hours\": 14,\n    \"minutes\": 20\n  }, {\n    \"name\": \"Delhi\"\n  }], [{\n    \"hours\": 19,\n    \"minutes\": 0\n  }, {\n    \"name\": \"Ahmedabad\"\n  }], [{\n    \"hours\": 23,\n    \"minutes\": 30\n  }, {\n    \"name\": \"Mumbai\"\n  }]]\n}]")
      }
    }

    "be able to add train in" in {
      Post("/train", rajdhani112) ~> routes ~> check {
        status should ===(StatusCodes.Created)
        entityAs[String] should ===("Successfully Added Train 112 to Route")
      }
    }
  }

}
