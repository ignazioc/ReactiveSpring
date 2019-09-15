package com.ignazioc.reactiveK

class JokeResponse {
  lateinit var type: String
  lateinit var value: Value
}


class Value {
  var id: Int = 0
  lateinit var joke: String
  var categories: Array<String>? = null
}
