package tutorial

import akka.actor.{Actor, ActorRef, Props}

import scala.util.Random

/**
  * Spawns some [[tutorial.PiActor PiActor]]s and calculates PI.
  */
//noinspection ActorMutableStateInspection
class HubActor() extends Actor {
  private val random = new Random()

  private var caller: ActorRef = _
  private var expectedReplies = 0L
  private var pointsInside = 0L
  private var totalPoints = 0L

  override def receive: Receive = {

    case Calculate(numberOfActors, pointsPerActor) ⇒
      this.expectedReplies = numberOfActors
      this.caller = sender()
      this.totalPoints = numberOfActors * pointsPerActor
      this.pointsInside = 0

      for (_ ← 1L to numberOfActors) {
        val child = context.actorOf(PiActor.props(random.nextInt()))
        child ! Generate(pointsPerActor)
      }

    case Points(n) ⇒
      pointsInside += n
      expectedReplies -= 1

      if (expectedReplies == 0) {
        val pi = 4.0 * pointsInside.toDouble / totalPoints
        this.caller ! Pi(pi)
        context stop self
      }
  }

}

object HubActor {
  def props(): Props = Props(new HubActor())
}
