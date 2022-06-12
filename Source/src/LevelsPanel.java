//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

import javax.swing.*;
import java.awt.*;

public class LevelsPanel extends JPanel {
    private final Image image;

    public LevelsPanel() {
        setLayout(null);
        image = Toolkit.getDefaultToolkit().createImage("spacePixel.gif");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null){
            g.drawImage(image,0,0, 1200, 800,this);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 120));
        g.drawString("Select Level", 250, 100);
    }
}
