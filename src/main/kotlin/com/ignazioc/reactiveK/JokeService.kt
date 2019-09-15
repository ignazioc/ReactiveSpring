package com.ignazioc.reactiveK

import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class JokeService @Autowired
constructor(builder: RestTemplateBuilder) {

  private val template: RestTemplate = builder.build()

  fun getJokeSync(first: String, last: String): String {
    val base = "http://api.icndb.com/jokes/random?limitTo=nerdy"
    val url = String.format("%s&firstName=%s&lastName=%s", base, first, last)
    return template.getForObject(url, JokeResponse::class.java)!!.value.joke

  }

  fun getJokeAsync(first: String, last: String): Mono<String> {
    val client = WebClient.create("http://api.icndb.com")
    val path = "/jokes/random?limitTo=[nerdy]&firstName={first}&lastName={last}"
    return client.get()
        .uri(path, first, last)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(JokeResponse::class.java)
        .map { jokeResponse -> jokeResponse.value.joke }
  }
}