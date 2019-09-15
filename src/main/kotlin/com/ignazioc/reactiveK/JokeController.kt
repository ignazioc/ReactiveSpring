package com.ignazioc.reactiveK

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
internal class JokeController {

  @Autowired
  private val pingService: JokeService? = null

  @GetMapping(path = ["/joke"])
  fun ping(): String {
    return pingService!!.getJokeSync("Ignazio", "C")
  }

  @GetMapping(path = ["/async"])
  fun joke(): Mono<String> {
    return pingService!!.getJokeAsync("Ignazio", "C")
  }
}