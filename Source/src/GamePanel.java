//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

import modelPackage.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private ArrayList<Stone> sidewalk;
    private ArrayList<City> walls;
    private ArrayList<Prison> target;
    private ArrayList<Criminal> criminals;
    private Police player;
    private boolean helpLevel;

    private BufferedImage backgroundImage;
    private BufferedImage helpImage;

    private  Boolean completed;
    private Timer time;
    private int score;

    public GamePanel(){
        setLayout(null);
        try {
            backgroundImage = ImageIO.read(new File("cityNight.jpg"));
            helpImage = ImageIO.read(new File("keys.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }


        completed = false;
        setFocusable(true);
        helpLevel = false;
    }

    public void setHelpLevel(boolean helpLevel) {
        this.helpLevel = helpLevel;
    }

    public void setSidewalk(ArrayList<Stone> sidewalk) {
        this.sidewalk = sidewalk;
    }

    public void setWalls(ArrayList<City> walls) {
        this.walls = walls;
    }

    public void setTarget(ArrayList<Prison> target) {
        this.target = target;
    }

    public void setCriminals(ArrayList<Criminal> criminals) {
        this.criminals = criminals;
    }

    public void setPlayer(Police player) {
        this.player = player;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setTime(Timer time) {
        this.time = time;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        background(g);
        buildWorld(g);

        //score and time display:
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 36));
        g.setColor(Color.WHITE);
        g.drawString(time.toString(), 100, 100);
        g.drawString("Score: ",10, 700);
        g.drawString(String.valueOf(score), 120,700);


        //goal or game over display:
        if (getCompleted()) {
            g.setFont(new Font("Bauhaus 93", Font.BOLD, 82));
            g.setColor(Color.WHITE);
            g.drawString("Completed", 350, 350);
            time.setGameOver(true);
        }
        else {
            if (time.isGameOver()) {
                g.setFont(new Font("Bauhaus 93", Font.BOLD, 82));
                g.setColor(Color.WHITE);
                g.drawString("Game Over", 350, 350);
            }
        }

    }

    public void background (Graphics g){

        g.drawImage(backgroundImage, 0, 0, 1200, 200, null);

        if (helpLevel){
           helpPaint(g);
        }
    }

    private void helpPaint(Graphics g){
        g.drawImage(helpImage, 600, 200, 500, 250, null);

        g.setFont(new Font("Bauhaus 93", Font.BOLD, 26));
        g.setColor(Color.WHITE);
        g.drawString("Move with the arrows", 600, 500);
        g.drawString("Press R to reset",600, 550);
        g.drawString("You have 3 minutes to complete the level", 600, 600);
        g.setColor(new Color(135,206,250));
        g.drawString("The goal is to move the", 600, 650);
        g.drawString("criminals to the prison", 600, 700);
    }

    private void buildWorld(Graphics g) {
        for (Stone i : sidewalk){
            i.paintObject(g);
        }

        for (City i : walls){
            i.paintObject(g);
        }

        for (Criminal i : criminals){
            i.paintObject(g);
        }

        for (Prison i : target){
            i.paintObject(g);
        }

        player.paintObject(g);
    }

}
