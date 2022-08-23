import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


public class ImageUploader {

  public String[] uploader() throws IOException {

    
    File folder = new File("./stickers");
    String[] folderList = folder.list();
    
    int folderLength = folderList != null ? folderList.length : 0;


//    String apiKey = "2c53e1bddba85cd950f727fe57e44501"; // ImgBB
//    String apiKey = "26qrIzVXzPe1m1NrnbvgRvMslW0NAvzPmrCgWLDd"; // Im.ge
//     String apiAddress = "https://im.ge/api/1/upload/?key=26qrIzVXzPe1m1NrnbvgRvMslW0NAvzPmrCgWLDd";

    String apiKey = "569bba4a1971ad6";
//    String apiAddress = "https://api.imgbb.com/1/upload?key=" + apiKey;
//    String apiAddress = "https://im.ge/api/upload";
    String apiAddress = "https://api.imgur.com/3/image";

//    OkHttpClient client = new OkHttpClient().newBuilder()
//            .protocols(Arrays.asList(Protocol.HTTP_1_1))
//            .build();
//    OkHttpClient client = new OkHttpClient();
//    client.newBuilder()
////            .setProtocols(Arrays.asList(Protocol.HTTP_1_1));
////            .protocols(Arrays.asList(Protocol.HTTP_1_1));
//

    
    if (folderLength != 0) {
      for (int i = 0; i < folderLength; i++) {
        
        Path imagePath = Paths.get("stickers/" + folderList[i]).toAbsolutePath();
        String imagePathAddress = imagePath.toString();

        File imageFile = new File(imagePathAddress);
//        MediaType mediaType = MediaType.parse("image/png");
        MediaType mediaType = MediaType.parse("text/plain");

        OkHttpClient client = new OkHttpClient();
        client.newBuilder().cache(null);


//        RequestBody requestBody = RequestBody.create(imageFile, mediaType);

//        IMGUR
//        MultipartBody multipartBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("title", folderList[i])
//                .addFormDataPart("image", folderList[i], requestBody)
//                .build();
//
//
//        Request request = new Request.Builder()
//                .header("Authorization", "Client-ID " + apiKey)
//                .url(apiAddress)
//                .post(multipartBody)
//                .build();

//        IM.GE
//        MultipartBody multipartBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("source", folderList[i], requestBody)
//                .build();


//        Request request = new Request.Builder()
//                .url(apiAddress)
//                .post(requestBody)
//                .build();


//        Response response = client.newCall(request).execute();
//        response.close();

//        System.out.println(response);


        String imageBase64 = imageConverter(imageFile);

//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("action", "upload")
//                .addFormDataPart("source", imageBase64)
//                .addFormDataPart("format", "json")
//                .build();
//
//        Request request = new Request.Builder()
//                .url(apiAddress)
//                .post(requestBody)
//                .build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageBase64)
                .build();

        Request request = new Request.Builder()
                .url(apiAddress)
                .method("POST", requestBody)
                .addHeader("Authorization", "Client-ID " + apiKey)
                .build();


        Response response = client.newCall(request).execute();

        String responseString = response.body().string();
//        Gson gson = new Gson();
//        Data data = gson.fromJson(responseString, Data.class);

        JSONObject responseJSON = new JSONObject(responseString);
        JSONObject data = responseJSON.getJSONObject("data");



        System.out.println(data.getString("link"));
      }
   }

    return folderList;

  }

  private String imageConverter(File image) {

    String encodedImage = null;

    try {
      FileInputStream fileInputStream = new FileInputStream(image);
      byte[] bytes = new byte[(int) image.length()];
      fileInputStream.read(bytes);
      encodedImage = Base64.getEncoder().encodeToString(bytes);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return encodedImage;

  }
  
}
