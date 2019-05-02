package tutorial

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import akka.util.Timeout.durationToTimeout

import scala.concurrent.duration._
import scala.language.{implicitConversions, postfixOps}
import scala.util.{Failure, Success}

object CalculatePi {

  def main(args: Array[String]) {
    if (args.length < 2) {
      println("Usage: CalculatePi <#actors> <#points per actor>")
      return
    }

    val numberOfActors = args(0).toLong
    val pointsPerActor = args(1).toLong

    println(s"Calculating 𝜋 with $numberOfActors actors and $pointsPerActor points per actor...")

    implicit val timeout: Timeout = durationToTimeout(10 minutes)

    val system = ActorSystem()
    import system.dispatcher
    val hub = system.actorOf(HubActor.props())
    val msg = Calculate(numberOfActors, pointsPerActor)
    val future = hub.ask(msg).mapTo[Pi]

    future andThen {
      case Success(pi) ⇒ println(s"Calculate PI is: ${pi.value}\nJava Math.PI is: ${Math.PI}")
      case Failure(t) ⇒ println(s"Something went wrong: ${t.getMessage}")
    } onComplete {
      _ ⇒ system.terminate()
    }
  }

}
