import java.util.List;

public interface Extractor {
  List<Content> extractor(String json);
}
