import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ImageUploader {

  public String[] uploader() throws IOException, InterruptedException {

    
    File folder = new File("./stickers");
    String folderList[] = folder.list();
    int folderLength = folderList.length;

    String apiKey = "2c53e1bddba85cd950f727fe57e44501";
//    URI address = URI.create("https://api.imgbb.com/1/upload?key=" + apiKey);
    String apiAddress = "https://api.imgbb.com/1/upload?key=" + apiKey;

//    String apiAddress = "https://api.imgbb.com/1/upload?";
    // HttpClient client = HttpClient.newHttpClient();
    //    Builder requestBuilder = HttpRequest.newBuilder().uri(address);

    OkHttpClient client = new OkHttpClient().newBuilder().build();

    
    if (folderLength != 0) {
      for (int i = 0; i < folderLength; i++) {
        
        Path imagePath = Paths.get("stickers/" + folderList[i]).toAbsolutePath();

        String imagePathAddress = imagePath.toString();

        File imageFile = new File(imagePathAddress);

        System.out.println(imageFile);

        MediaType mediaType = MediaType.parse("image/png");

        RequestBody requestBody = RequestBody.create(imageFile, mediaType);

        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("title", folderList[i])
                .addFormDataPart("image", folderList[i], requestBody)
                .build();

        Request request = new Request.Builder()
                .url(apiAddress)
                .post(multipartBody)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response);
        
//         RequestBody requestBody = new MultipartBody.Builder()
//                 .addFormDataPart("title", folderList[i])
//                 .addFormDataPart("image", folderList[i], RequestBody.create(imageFile, MediaType.parse("image/png")))
//             .build();
//
//        Request request = new Request.Builder()
//                .url(address)
//                .post(requestBody).build();
//
//        Response requestResponse = client.newCall(request).execute();
//         System.out.println(folderList[i]);
//        FormData form = new FormData("POST", address)
//                .param("title", title)
//                .file("image", imagePath);

//        HttpRequest request = requestBuilder
//            .POST(BodyPublishers.ofFile(imagePath))
//            .build();
//
//        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
//            System.out.println(response);
   
      }
   }

    return folderList;

  }


  
}
