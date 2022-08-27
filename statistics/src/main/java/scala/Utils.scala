package scala

import it.uniroma1.commons.queue.enums.{CarType, FuelType, RoadType}

object Utils {
  def mostActiveSpeedCamera(list: List[Detection]): Long = {
    list.groupBy(_.speedCameraId).maxBy(_._2.length)._1
  }

  def highestSpeed(list: List[Detection]): Int = {
    list.maxBy(_.speedValue).speedValue
  }

  def highestAverageSpeedCamera(list: List[Detection]): Long = {
    list.groupBy(_.speedCameraId).map(t => (t._1, t._2.map(_.speedValue).reduce(_ + _).toDouble / t._2.length.toDouble)).maxBy(_._2)._1
  }

  def busiestRegion(list: List[Detection]): String = {
    list.groupBy(_.region).maxBy(_._2.length)._1
  }

  def ecoFriendlyPercentage(list: List[Detection]): Double = {
    list.filter(t => t.fuelType == FuelType.HYBRID || t.fuelType == FuelType.FULL_ELECTRIC).length.toDouble / list.length.toDouble
  }

  def mostUsedFuel(list: List[Detection]): FuelType = {
    list.groupBy(_.fuelType).maxBy(_._2.length)._1
  }

  def mostCriminalRegion(list: List[Detection]): String = {
    list.filter(_.overcameLimit).groupBy(_.region).maxBy(_._2.length)._1
  }

  def highestAverageRegion(list: List[Detection]): String = { //regione con percentuale di rilevazioni sopra il limite di velocità più alto
    list.groupBy(_.region).map(t => (t._1, t._2.filter(_.overcameLimit).length.toDouble / t._2.length.toDouble)).maxBy(_._2)._1
  }

  def mostUsedCarType(list: List[Detection]): CarType = {
    list.groupBy(_.carType).maxBy(_._2.length)._1
  }

  def highestAverageCriminalRoad(list: List[Detection]): RoadType = { //regione con percentuale di rilevazioni sopra il limite di velocità più alto
    list.groupBy(_.roadType).map(t => (t._1, t._2.filter(_.overcameLimit).length.toDouble / t._2.length.toDouble)).maxBy(_._2)._1
  }
}