//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

package modelPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class City extends Block {

    public City(int x, int y) {
        super(x, y);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("city.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImg(img);
    }
}
