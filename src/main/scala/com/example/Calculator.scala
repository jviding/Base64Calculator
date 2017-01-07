package com.example

object Calculator {

	private def createArrayOfValuesForMultiplication(function: String) : Array[String] = {
		function.split("\\*")
	}

	private def solveMultiplications(function: String) : Double = {
		var RESULT : Double = 1.0
		var VALUES = createArrayOfValuesForMultiplication(function)
		for (index <- 0 to VALUES.length - 1) {
			RESULT = RESULT * VALUES(index).toDouble
		}
		RESULT
	}

	private def divideResultsOfMultiplicationsWithEachOther(dividers: Array[Double]) : Double = {
		var RESULT = dividers(0)
		for (index <- 1 to dividers.length - 1) {
			RESULT = RESULT / dividers(index)
		}
		RESULT
	}

	private def createArrayOfFunctionsWithOnlyMultiplicationOperators(function: String) : Array[String] = {
		function.split("/")
	}

	private def solveMultiplicationsAndDivisions(function: String) : Double = {
		var MUL_RESULTS = Array[Double]()
		var MUL_FUNCTIONS = createArrayOfFunctionsWithOnlyMultiplicationOperators(function)
		for (index <- 0 to MUL_FUNCTIONS.length - 1) {
			MUL_RESULTS = MUL_RESULTS :+ solveMultiplications(MUL_FUNCTIONS(index))
		}
		divideResultsOfMultiplicationsWithEachOther(MUL_RESULTS)
	}

	private def addResultsOfSubMulDivFunctionsTogether(additions: Array[Double]) : Double = {
		var SUM = 0.0
		for (index <- 0 to additions.length - 1) {
			SUM = SUM + additions(index)
		}
		SUM
	}

	private def subtractResultsOfDivisionsAndMultiplicationsFromEachOther(subtractions: Array[Double]) : Double = {
		var SUM = subtractions(0)
		for (index <- 1 to subtractions.length - 1) {
			SUM = SUM - subtractions(index)
		}
		SUM
	}

	private def createArrayOfFunctionsWithOnlyMulAndDivOperators(function: String) : Array[String] = {
		function.split("-")
	}

	private def solveSubtractionsMultiplicationsAndDivisions(function: String) : Double = {
		var DIV_MUL_RESULTS = Array[Double]()
		var DIV_MUL_FUNCTIONS = createArrayOfFunctionsWithOnlyMulAndDivOperators(function)
		for (index <- 0 to DIV_MUL_FUNCTIONS.length - 1) {
			DIV_MUL_RESULTS = DIV_MUL_RESULTS :+ solveMultiplicationsAndDivisions(DIV_MUL_FUNCTIONS(index))
		}
		subtractResultsOfDivisionsAndMultiplicationsFromEachOther(DIV_MUL_RESULTS)
	}

	private def createArrayOfFunctionsWithOnlySubDivAndMulOperators(function: String) : Array[String] = {
		function.split("\\+")
	}

	private def solveAdditionsSubtractionsMultiplicationsAndDivisions(function: String) : Double = {
		var SUB_DIV_MUL_RESULTS = Array[Double]()
		var SUB_DIV_MUL_FUNCTIONS = createArrayOfFunctionsWithOnlySubDivAndMulOperators(function)
		for (index <- 0 to SUB_DIV_MUL_FUNCTIONS.length - 1) {
			SUB_DIV_MUL_RESULTS = SUB_DIV_MUL_RESULTS :+ solveSubtractionsMultiplicationsAndDivisions(SUB_DIV_MUL_FUNCTIONS(index))
		}
		addResultsOfSubMulDivFunctionsTogether(SUB_DIV_MUL_RESULTS)
	}

	def solveFunction(function: String) : Double = {
		solveAdditionsSubtractionsMultiplicationsAndDivisions(function)
	}

}