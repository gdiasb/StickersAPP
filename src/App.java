import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String api = "NASA";

        String url = SelectAPI.valueOf(api).getValue();
        
        HTTPClient client = new HTTPClient();
        String body = client.httpRequest(url);
       
        NASAExtractor extractor = new NASAExtractor();
        
        List<Content> contents = extractor.extractor(body);
       
        
        for (Content movie : contents) {

            // System.out.println(movie.getTitle());
            // System.out.println(movie.getImageURL());

            String getMovieURL = movie.getImageURL();
            String movieTitle = movie.getTitle();

            try (InputStream movieURL = new URL(getMovieURL).openStream()) {
                Generator.create(movieURL, movieTitle);
            } catch (Exception e) {
                System.out.println("Input Stream Error");
            }
        }

        ImageUploader imageUploader = new ImageUploader();
        imageUploader.uploader();

    }



}
