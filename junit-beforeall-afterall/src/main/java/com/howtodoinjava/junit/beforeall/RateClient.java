package com.howtodoinjava.junit.beforeall;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Reads currency exchange rates from a REST API, for example GET /rates/USD/EUR returns 0.92.
 */
public class RateClient {

  private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build();
  private final URI baseUri;

  public RateClient(URI baseUri) {
    this.baseUri = baseUri;
  }

  public BigDecimal rate(String from, String to) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder(baseUri.resolve("/rates/" + from + "/" + to))
        .timeout(Duration.ofSeconds(2))
        .GET()
        .build();
    HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() != 200) {
      throw new IllegalStateException("No rate for " + from + "/" + to + ", HTTP " + response.statusCode());
    }
    return new BigDecimal(response.body().strip());
  }
}
