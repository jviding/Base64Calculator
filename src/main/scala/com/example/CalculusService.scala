package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import spray.httpx.SprayJsonSupport._
import ResponseJsonProtocol._
import CalculusSolver._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class CalculusServiceActor extends Actor with CalculusService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(calculusRoute)
}

// this trait defines our service behavior independently from the service actor
trait CalculusService extends HttpService {

val calculusRoute =
  get {
    path("calculus") {
      parameters('query.?) { (encodedFunction) =>
        complete {
          respondToCalculusRequest(encodedFunction)
        }
      }
    }
  } ~ get {
    complete {
      ErrorResponse(true, "Nonexistent path.")
    }
  }

  def createSuccessResponseFromEncodedFunction(encodedFunction: Option[String]) : SuccessResponse = {
    val ENCODED_FUNCTION_STRING = encodedFunction.get
    val FUNCTION_SOLUTION = CalculusSolver.decodeAndSolveBase64EncodedFunction(ENCODED_FUNCTION_STRING)
    SuccessResponse(false, FUNCTION_SOLUTION)
  }

  def respondToCalculusRequest(encodedFunction: Option[String]) : Either[SuccessResponse, ErrorResponse] = {
    val SUCCESSFUL_QUERY : Boolean = encodedFunction != None && encodedFunction.get.length > 0
    if (SUCCESSFUL_QUERY) {
      try {
        Left(createSuccessResponseFromEncodedFunction(encodedFunction))
      } catch {
        case e: Exception => Right(ErrorResponse(true, "Something went wrong with the input function!"))
      }
    } else {
      Right(ErrorResponse(true, "Query parameter empty or not found!"))
    }
  }

}