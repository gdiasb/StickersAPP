import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUploader {

  public String[] uploader() throws IOException, InterruptedException {

    
    File folder = new File("./stickers");
    String array[] = folder.list();
    int folderLength = array.length;

    String apiKey = "2c53e1bddba85cd950f727fe57e44501";
    URI address = URI.create("https://api.imgbb.com/1/upload?key=" + apiKey);

    HttpClient client = HttpClient.newHttpClient();
    Builder requestBuilder = HttpRequest.newBuilder().uri(address);


    if (folderLength != 0) {
      for (int i = 0; i < folderLength; i++) {

        Path imagePath = Paths.get("./stickers/" + array[i]).toAbsolutePath();

        HttpRequest request = requestBuilder
            .POST(BodyPublishers.ofFile(imagePath))
            .build();
        
            System.out.println(request);
   
      }
   }

    return array;

  }


  
}
