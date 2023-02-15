package br.upe.ppsw.jabberpoint.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.util.ResourceUtils;

import br.upe.ppsw.jabberpoint.view.Style;

public class BitmapItem extends SlideItem {

  public BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "Arquivo ";
  protected static final String NOTFOUND = " não encontrado";

  public BitmapItem(int level, String name) {
    super(level);

    imageName = name;

    try {
      bufferedImage = ImageIO.read(ResourceUtils.getFile(imageName).getAbsoluteFile());
    } catch (IOException e) {
      System.err.println(FILE + imageName + NOTFOUND);
    }

  }

  public BitmapItem() {
    this(0, null);
  }

  public String getName() {
    return imageName;
  }

  public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
    return new Rectangle((int) (myStyle.indent * scale), 0,
        (int) (bufferedImage.getWidth(observer) * scale),
        ((int) (myStyle.leading * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
  }

  /**
 * @deprecated Use {@link br.upe.ppsw.jabberpoint.view.Style#draw(int,int,float,Graphics,br.upe.ppsw.jabberpoint.model.BitmapItem,ImageObserver)} instead
 */
  public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
	myStyle.draw(x, y, scale, g, this, observer);
}

  public String toString() {
    return "BitmapItem[" + getLevel() + "," + imageName + "]";
  }
}
