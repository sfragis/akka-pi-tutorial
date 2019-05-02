package tutorial

import akka.actor.{Actor, Props}

import scala.util.Random

/**
  *
  * @author Fabio G. Strozzi
  */
class PiActor(seed: Int) extends Actor {
  private val random = new Random(seed)

  override def receive: Receive = {
    case Generate(points) ⇒
      val pointsInside = generatePoints(points)
      sender() ! Points(pointsInside)
  }

  private def generatePoints(n: Long): Long = {
    var inside = 0

    for (_ ← 1L to n) {
      val x = random.nextDouble()
      val y = random.nextDouble()
      if (x * x + y * y < 1.0) {
        inside += 1
      }
    }

    inside
  }
}

object PiActor {
  def props(id: Int): Props = Props(new PiActor(id))
}
