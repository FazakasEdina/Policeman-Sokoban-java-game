//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

import modelPackage.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameControllel implements Runnable{
    private final GamePanel panel;

    private ArrayList<Stone> stones;
    private ArrayList<City> walls;
    private ArrayList<Prison> prisons;
    private ArrayList<Criminal> criminals;
    private Police player;

    private final int size;
    private final int offsetX;
    private final int offsetY;

    private  Boolean completed;
    private String level;
    private final Timer timer;
    private int score;

    public GameControllel(){
        size = 50;
        offsetX = 50;
        offsetY = 200;

        timer = new Timer(3,0);

        panel = new GamePanel();

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        //lekell ellenorizzem hogy mehet e a bal iranyba
                        //ha fal van nem mehet
                        // es ha doboz van ami mehet balra akkor balra tolja, es ha nem akkor a doboz nem mozdul
                        Move(0);
                        panel.setCompleted(completed);
                        break;
                    case KeyEvent.VK_RIGHT:
                        Move(1);
                        panel.setCompleted(completed);
                        break;
                    case KeyEvent.VK_UP:
                        Move(2);
                        panel.setCompleted(completed);
                        break;
                    case KeyEvent.VK_DOWN:
                        Move(3);
                        panel.setCompleted(completed);
                        break;
                    case KeyEvent.VK_R:
                        if (!getCompleted() || !timer.isGameOver()) {
                            resetLevel();
                        }
                    default:
                        break;

                }
                panel.repaint();
            }
        });
    }

    public GamePanel getPanel() {
        return panel;
    }

    public void setPanelDates() {
        panel.setCriminals(criminals);
        panel.setPlayer(player);
        panel.setSidewalk(stones);
        panel.setWalls(walls);
        panel.setTarget(prisons);
        panel.setTime(timer);
        panel.setCompleted(false);

        panel.setHelpLevel(level.equals("helplevel.txt"));

        panel.repaint();
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;

        setCompleted(false);
        timer.setGameOver(false);
        timer.setMin(3);
        timer.setSec(0);
        score = 3 * 60;

        levelDates(level);
        setPanelDates();
    }

    public void setGameOver(){
        timer.setGameOver(true);
    }

    public int getScore() {
        return score;
    }

    private void levelDates(String fileName) {
        //offset of the game panel
        int x = offsetX;
        int y = offsetY;

        stones = new ArrayList<>();
        walls = new ArrayList<>();
        prisons = new ArrayList<>();
        criminals = new ArrayList<>();

        //one object per character
        Stone stone;
        City wall;
        Criminal criminal;
        Prison prison;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;

            while(reader.ready()){
                line = reader.readLine();

                for (int i = 0; i < line.length(); i++) {

                    char item = line.charAt(i);

                    switch (item) {
                        case ' ': ///empty space
                            x += size;
                            break;

                        case '-': ///sidewalk
                            stone = new Stone (x, y);
                            stones.add(stone);
                            //step forward
                            x += size;
                            break;

                        case '#': ///wals
                            wall = new City(x, y);
                            walls.add(wall);
                            x += size;
                            break;

                        case '$': ///criminals
                            criminal = new Criminal(x, y);
                            criminals.add(criminal);

                            //I want the criminal to stand on stone
                            stone = new Stone (x, y);
                            stones.add(stone);

                            x += size;
                            break;

                        case '.': ///the prisons
                            prison = new Prison(x, y);
                            prisons.add(prison);

                            stone = new Stone (x, y);
                            stones.add(stone);

                            x += size;
                            break;

                        case '@': //policeman
                            player = new Police(x, y);

                            stone = new Stone (x, y);
                            stones.add(stone);

                            x += size;
                            break;

                        default:
                            break;
                    }


                }
                //new line begin from here
                y += size;

                x = offsetX;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //i make all dates to built my game world
    }

    public void Move(int dirrection){
        boolean letsMove = true;
        switch (dirrection){
            case 0:
                //eloszor bejarom a tolvajokat, ha tolvajt szereknenk tolni
                for (Criminal k : criminals){
                    if (player.isLeftAnObject(k)) {   ///ha a jatekos oldalan van a tolvaj
                                                      //megkell nezzem ennek a tolvajnak nincs e valami a bal oldalan, mert akkor nem lehet eltolni
                        letsMove = true;
                        for (Criminal i : criminals) {
                            if (i != k && k.isLeftAnObject(i)){
                                letsMove = false;
                            break;
                            }
                        }

                        for (City j : walls){
                            if (k.isLeftAnObject(j)) {
                                letsMove = false;
                                break;
                            }
                        }

                        //ha nem talalt eddig akadajt, akkor elmozditom a tolvajt
                        if (!getCompleted() && letsMove)
                            k.setX(k.getX() - size);
                    }
                }
                for (City i : walls) {
                    if (player.isLeftAnObject(i)){    //ha ez a fal az o baljan talalhato
                        letsMove = false;            //akkor elvegezheti a mozgast
                        break;
                    }
                }

                if (letsMove && !getCompleted())
                    this.player.setX(this.player.getX() - size);

                levelCompleted();
                break;
            case 1:
                for (Criminal k : criminals){
                    if (player.isRightAnObject(k)) {
                        for (Criminal i : criminals){
                            if (i != k && k.isRightAnObject(i)) {
                                letsMove = false;
                                break;
                            }
                        }

                        for (City j : walls){
                            if (k.isRightAnObject(j)) {
                                letsMove = false;
                                break;
                            }
                        }
                        if (!getCompleted() && letsMove)
                            k.setX(k.getX() + size);
                    }
                }

                for (City i : walls){
                    if (player.isRightAnObject(i)) {
                        letsMove = false;
                        break;
                    }
                }
                if (letsMove && !getCompleted())
                    player.setX(player.getX() + size);

                levelCompleted();
                break;
            case 2:
                for (Criminal k : criminals){
                    if (player.isUpAnObject(k)) {
                        for (Criminal i : criminals) {
                            if (i != k && k.isUpAnObject(i)){
                                letsMove = false;
                                break;
                            }
                        }

                        for (City j : walls){
                            if (k.isUpAnObject(j)) {
                                letsMove = false;
                                break;
                            }
                        }

                        if (!getCompleted() && letsMove)
                            k.setY(k.getY() - size);
                    }
                }

                for (City i : walls){
                    if (player.isUpAnObject(i)) {
                        letsMove = false;
                        break;
                    }
                }
                if (letsMove && !getCompleted())
                    player.setY(player.getY() - size);

                levelCompleted();
                break;

            case 3:

                for (Criminal k : criminals){
                    if (player.isDownAnObject(k)) {
                        for (Criminal i : criminals){
                            if (i != k && k.isDownAnObject(i)) {
                                letsMove = false;
                                break;
                            }
                        }

                        for (City j : walls){
                            if (k.isDownAnObject(j)) {
                                letsMove = false;
                                break;
                            }
                        }
                        if (!getCompleted() && letsMove)
                            k.setY(k.getY() + size);
                    }
                }

                for (City i : walls){
                    if (player.isDownAnObject(i)) {
                        letsMove = false;
                        break;
                    }
                }
                if (letsMove && !getCompleted())
                    player.setY(player.getY() + size);

                levelCompleted();
                break;

            default:
                break;
        }
    }

    public void resetLevel(){
        criminals.clear();
        stones.clear();
        walls.clear();
        prisons.clear();

        levelDates(level);

        panel.setCriminals(criminals);
        panel.setPlayer(player);
        panel.setSidewalk(stones);
        panel.setWalls(walls);
        panel.setTarget(prisons);
    }

    public void levelCompleted () {
        int numberOfCompleted = 0;
        for (Prison i : prisons)
            for (Criminal j : criminals)
                if (i.getX() == j.getX() && i.getY() == j.getY())
                    numberOfCompleted++;

        setCompleted(numberOfCompleted == criminals.size());
    }

    @Override
    public void run() {
        while(!timer.isGameOver()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timer.countTime();
            if (!timer.isGameOver())
                score--;
            panel.setTime(timer);
            panel.setScore(score);
            panel.repaint();
        }
    }
}
