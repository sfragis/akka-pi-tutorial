package tutorial

import akka.actor.ActorSystem
import akka.testkit.TestKit.shutdownActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, FunSuiteLike, Matchers}

import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Unit tests for [[tutorial.PiActor PiActor]].
  */
class PiActorTest extends TestKit(ActorSystem("PiActorTest"))
  with FunSuiteLike
  with BeforeAndAfterAll
  with Matchers
  with ImplicitSender {

  override def afterAll() {
    shutdownActorSystem(system)
  }

  test("PiActor can generate some random points") {
    val actor = system.actorOf(PiActor.props(1))

    actor ! Generate(10)

    within(3 seconds) {
      expectMsgPF(hint = "Some points returned") {
        case Points(p) ⇒ p
      }
    }
  }

  test("PiActor generates a non-negative number of points inside the circle") {
    val actor = system.actorOf(PiActor.props(1))

    actor ! Generate(10)

    within(3 seconds) {
      val points = expectMsgPF(hint = "Some points returned") {
        case Points(p) ⇒ p
      }

      points should be >= 0L
    }
  }

  test("PiActor does not reply for unknonw messages") {
    val actor = system.actorOf(PiActor.props(1))

    actor ! Calculate(10, 10)

    expectNoMessage(2 seconds)
  }

}
