package com.example

import Decoder._
import Calculator._

object CalculusSolver {

	private var INDEX = 0
	private var FUNCTION = ""

	private def incrementIndex() : Unit = {
		INDEX = INDEX + 1
	}

	private def readAndSolveNewFunctionInsideBrackets(functionBetweenBrackets: String) : String = {
		val NEXT_VALUE = solveFunctionInsideBrackets()
		val CONTINUE_READING = functionBetweenBrackets + NEXT_VALUE
		readNextValueInsideBrackets(CONTINUE_READING)
	}

	private def continueReadingInsideBrackets(functionBetweenBrackets: String) : String = {
		val NEW_FUNCTION_STARTS = FUNCTION.charAt(INDEX) == '('
		val CONTINUE_READING = functionBetweenBrackets + FUNCTION.charAt(INDEX).toString
		incrementIndex
		if (NEW_FUNCTION_STARTS) {
			readAndSolveNewFunctionInsideBrackets(functionBetweenBrackets)
		} else {
			readNextValueInsideBrackets(CONTINUE_READING)
		}
	}

	private def readNextValueInsideBrackets(functionBetweenBrackets: String) : String = {
		val BRACKET_CLOSES = INDEX == FUNCTION.length || FUNCTION.charAt(INDEX) == ')'
		if (BRACKET_CLOSES) {
			incrementIndex
			functionBetweenBrackets
		} else {
			continueReadingInsideBrackets(functionBetweenBrackets)
		}
	}

	private def solveFunctionInsideBrackets() : String = {
		var FUNCTION_INITIAL = ""
		val FUNCTION_RESULT = readNextValueInsideBrackets(FUNCTION_INITIAL)
		Calculator.solveFunction(FUNCTION_RESULT).toString
	}

	private def solveFunction() : Double = {
		solveFunctionInsideBrackets().toDouble
	}

	private def initializeIndexAndFunction(function: String) : Unit = {
		INDEX = 0
		FUNCTION = function
	}

	def decodeAndSolveBase64EncodedFunction(encodedFunction: String) : Double = {
		val FUNCTION = Decoder.decodeAndTrimBase64Encoding(encodedFunction)
		initializeIndexAndFunction(FUNCTION)
		solveFunction()
	}

}