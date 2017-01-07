package com.example

import java.util.Base64

object Decoder {

	private def convertByteArrayToUTF8String(input: Array[Byte]) : String = {
		new String(input, "UTF-8")
	}

	private def decodeBase64Encoding(encodedString: String) : String = {
		val Decoder = Base64.getDecoder()
		val DECODED_INTO_BYTE_ARRAY = Decoder.decode(encodedString)
		convertByteArrayToUTF8String(DECODED_INTO_BYTE_ARRAY)
	}

	private def removeWhitespaceFromString(text: String) : String = {
		text.replaceAll("\\s", "")
	}

	def decodeAndTrimBase64Encoding(encodedString: String) : String = {
		val DECODED_STRING = decodeBase64Encoding(encodedString)
		removeWhitespaceFromString(DECODED_STRING)
	}

}