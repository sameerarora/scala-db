package com.deutsche.train

object TestData {

  val delhi = Station("Delhi")

  val jaipur = Station("Jaipur")

  val ahmedabad = Station("Ahmedabad")

  val mumbai = Station("Mumbai")

  val bangalore = Station("Bangalore")

  val rajdhani112DelhiTime = Time(8, 50)

  val rajdhani112JaipurTime = Time(10)

  val rajdhani112MumbaiTime = Time(19, 20)

  val rajdhani112BangaloreTime = Time(23, 30)

  val rajdhani415DelhiTime = Time(14, 20)

  val rajdhani415AhmedabadTime = Time(19)

  val rajdhani415MumbaiTime = Time(23, 30)

  val rajdhani112 = Train("Rajdhani", 112, Vector(rajdhani112DelhiTime -> delhi, rajdhani112JaipurTime -> jaipur,
    rajdhani112MumbaiTime -> mumbai, rajdhani112BangaloreTime -> bangalore))

  val rajdhani415 = Train("Rajdhani", 415, Vector(rajdhani415DelhiTime -> delhi, rajdhani415AhmedabadTime -> ahmedabad, rajdhani415MumbaiTime -> mumbai))

  val planner = new JourneyPlanner(Set[Train](rajdhani112, rajdhani415))
}