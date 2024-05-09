package com.kma.library.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse.BodyHandlers;

public class API {
  protected String baseUrl;
  protected HttpClient client;

  public API(String baseUrl) {
    this.baseUrl = baseUrl;
    this.client = HttpClient
        .newBuilder()
        .followRedirects(Redirect.NORMAL)
        .build();
  }

  public String fetch(String url) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(url))
        .build();

    var response = client.send(request, BodyHandlers.ofString());
    if (response.statusCode() == 200)
      return response.body();

    throw new Error(response.toString());
  }
}
