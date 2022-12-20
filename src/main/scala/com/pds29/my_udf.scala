package com.pds29

import scala.annotation.tailrec

object AreaOfRectangle
{
  def area(length: Int, height: Int): Int = {
    length * height
  }
}

object Main
{

  def print_system_properties(): Unit = {
    val systemProperties = System.getProperties
    val keys = systemProperties.stringPropertyNames()

    keys.forEach(key =>
      println(key + "=" + systemProperties.getProperty(key)) // value can be null!
    )
  }

  def print_env_properties(): Unit = {
    val envProperties = System.getenv()
    val keys = envProperties.keySet()

    keys.forEach(key =>
      println(key + "=" + envProperties.get(key)) // value can be null!
    )
  }

  def main(args: Array[String]): Unit = {

    @tailrec
    def nextArg(map: Map[String, Any], list: List[String]): Map[String, Any] = {
      list match {
        case Nil => map
        case "--length" :: value :: tail =>
          nextArg(map ++ Map("length" -> value.toInt), tail)
        case "--height" :: value :: tail =>
          nextArg(map ++ Map("height" -> value.toInt), tail)
        case "--runMask" :: value :: tail =>
          nextArg(map ++ Map("runMask" -> value.toInt), tail)
        case "--runId" :: value :: tail =>
          nextArg(map ++ Map("runId" -> value.toInt), tail)
      }
    }

    print_system_properties()
    println()
    print_env_properties()
    println()

    val options = nextArg(Map(), args.toList)
    println(options)

    val runId = options.getOrElse("runId", 0).asInstanceOf[Int]
    val runMask = options.getOrElse("runMask", 0).asInstanceOf[Int]

    if ((runId & runMask) == 0) {
      println("runId & runMask is " + (runId & runMask) + ", exiting!!")
      return
    }

    val length = options.getOrElse("length", 0).asInstanceOf[Int]
    val height = options.getOrElse("height", 0).asInstanceOf[Int]
    println(AreaOfRectangle.area(length, height))

  }
}
