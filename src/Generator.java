import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Generator {
  /**
   * @param movie
   */

  public static void create(InputStream movieURL, String movieTitle) {


    try {
      BufferedImage poster = ImageIO.read(movieURL);

      Integer posterWidth = poster.getWidth();
      Integer posterHeight = poster.getHeight();


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

      ImageIO.write(resizedPoster, "png", new File(folderName + "/" + movieTitle + ".png"));
      
    } catch (IOException e) {
      e.printStackTrace();
    }
     
  }
}
