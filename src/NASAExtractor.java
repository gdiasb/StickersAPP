import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NASAExtractor implements Extractor {

  public List<Content> extractor(String json) {

    JsonParser parser = new JsonParser();
    List<Map<String, String>> movieList = parser.parse(json);
    
    List<Content> movieListContents = new ArrayList<>();
    
    for (Map<String, String> movie : movieList) {
      
      String movieTitle = movie.get("title");
      String movieURLImage = movie.get("url");
      
      Content content = new Content(movieTitle, movieURLImage);
      
      movieListContents.add(content);
    }

    return movieListContents;

  }
}
