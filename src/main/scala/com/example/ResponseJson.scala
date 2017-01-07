package com.example

import spray.json.DefaultJsonProtocol

object ResponseJsonProtocol extends DefaultJsonProtocol {
  implicit val successResponseFormat = jsonFormat2(SuccessResponse)
  implicit val errorResponseFormat = jsonFormat2(ErrorResponse)
}

case class SuccessResponse(error: Boolean, result: Double)

case class ErrorResponse(error: Boolean, message: String)