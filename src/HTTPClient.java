import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HTTPClient {
  /**
   * @param url
   * @return
   */
  public String httpRequest(String url) {
    URI address = URI.create(url);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(address).build();

    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      String body = response.body();
      
      return body;

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
