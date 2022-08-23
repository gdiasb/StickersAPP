import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Generator {

  public static void create(InputStream movieURL, String movieTitle) {


    try {
      BufferedImage poster = ImageIO.read(movieURL);

      int posterWidth = poster.getWidth();
      int posterHeight = poster.getHeight();


      BufferedImage resizedPoster = new BufferedImage(posterWidth, posterHeight + 200, BufferedImage.TRANSLUCENT);
      
      Graphics2D editedPoster = (Graphics2D) resizedPoster.getGraphics();

      editedPoster.drawImage(poster, 0, 0, null);

      Font textFont = new Font(Font.SANS_SERIF, Font.BOLD, 64);
      editedPoster.setFont(textFont);
      editedPoster.setColor(Color.MAGENTA);
      
      int textWidth = editedPoster.getFontMetrics().stringWidth(movieTitle);
      int textXPosition = (posterWidth-textWidth)/2;
      int textYPosition = posterHeight + 100;

      editedPoster.drawString(movieTitle, textXPosition, textYPosition);

      String folderName = "stickers";

      File folder = new File("./", folderName);
      if (!folder.exists()) {
        folder.mkdirs();
      }

      String formatedMovieTitle = movieTitle.replaceAll(" ", "_").replaceAll(":", "");

      ImageIO.write(resizedPoster, "png", new File(folderName + "/" + formatedMovieTitle + ".png"));
      
    } catch (IOException e) {
      e.printStackTrace();
    }
     
  }
}
