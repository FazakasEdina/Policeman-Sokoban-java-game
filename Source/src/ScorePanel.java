//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private final Image image;
    private int[] scores;

    public ScorePanel() {
        setLayout(null);
        image = Toolkit.getDefaultToolkit().createImage("spacePixel.gif");
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null){
            g.drawImage(image,0,0, 1200, 800,this);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 120));
        g.drawString("Your scores: ", 250, 100);

        g.setFont(new Font("Bauhaus 93", Font.BOLD, 30));
        g.drawString("Level1: "+ scores[0], 400, 300);
        g.drawString("Level2: "+ scores[1], 400, 350);
        g.drawString("Level3: "+ scores[2], 400, 400);
        int sum = scores[0]+scores[1]+scores[2];
        g.drawString("All: "+ sum, 400, 450);

    }
}
