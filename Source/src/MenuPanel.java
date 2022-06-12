//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private BufferedImage img;

    public MenuPanel(){
        setLayout(null);
        try {
            img = ImageIO.read(new File("menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 1200, 800, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 120));
        g.drawString("POLICEMAN", 1200/4, 120);
    }
}
