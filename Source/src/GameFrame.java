//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GameFrame extends JFrame {
    private final GamePanel panel;
    private final GameControllel control;
    private final int[] scores;

    public GameFrame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        //-------------------------------------Inicializalas
        scores = new int[3];
        for (int i = 0; i < 3; i++) {
            scores[i] = 0;
        }

        ImageIcon icon = new ImageIcon("police.png");
        setIconImage(icon.getImage());
        setTitle("Policeman");
        Music sounds = new Music();

        JPanel cardPanel = new JPanel();
        CardLayout layout = new CardLayout();
        cardPanel.setLayout(layout);


        //--------------------------Menu panel:
        MenuPanel menu = new MenuPanel();

        JButton start = new JButton("Start");
        JButton exit = new JButton("Exit");
        JButton listScores = new JButton("Score");
        start.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        exit.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        listScores.setFont(new Font("Bauhaus 93", Font.BOLD, 36));
        start.setBounds(500, 400, 200, 70);
        listScores.setBounds(500, 500, 200, 70);
        exit.setBounds(500, 600, 200, 70);
        start.setBackground(Color.RED);
        exit.setBackground(Color.BLUE);
        listScores.setBackground(new Color (135,206,250));
        menu.add(start);
        menu.add(exit);
        menu.add(listScores);

        cardPanel.add(menu, "Menu");

        //------------------------Game panel:
        control = new GameControllel();
        panel = control.getPanel();

        JButton backToMenuFromLevel = new JButton("Back to Menu");
        JButton backToLevels = new JButton("Back to Levels");
        backToLevels.setFont(new Font("Bauhaus 93", Font.BOLD, 20));
        backToMenuFromLevel.setFont(new Font("Bauhaus 93", Font.BOLD, 20));
        backToMenuFromLevel.setBounds(800, 30, 180, 30);
        backToLevels.setBounds(800, 70, 180, 30);
        panel.add(backToMenuFromLevel);
        panel.add(backToLevels);

        panel.setBackground(new Color(0, 0, 70));
        cardPanel.add(panel, "Game");

        //-----------------------------Levels panel:
        LevelsPanel levelsPanel = new LevelsPanel();

        JButton help = new JButton("Help Level");
        JButton level1 = new JButton("Level 1");
        JButton level2 = new JButton("Level 2");
        JButton level3 = new JButton("Level 3");
        JButton backToMenuFromLevels = new JButton("Back to Menu");
        help.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        level1.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        level2.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        level3.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        backToMenuFromLevels.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        help.setBounds(375, 250, 300, 70);
        level1.setBounds(375, 350, 300, 70);
        level2.setBounds(375, 450, 300, 70);
        level3.setBounds(375, 550, 300, 70);
        backToMenuFromLevels.setBounds(375, 650, 300, 70);
        levelsPanel.add(help);
        levelsPanel.add(level1);
        levelsPanel.add(level2);
        levelsPanel.add(level3);
        levelsPanel.add(backToMenuFromLevels);

        cardPanel.add(levelsPanel, "Level");

        //------------------------------ Scores Panel:
        ScorePanel scorePanel = new ScorePanel();

        JButton backToMenuFromScores = new JButton("Back to Menu");
        backToMenuFromScores.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        backToMenuFromScores.setBounds(375, 650, 300, 70);
        scorePanel.add(backToMenuFromScores);

        cardPanel.add(scorePanel, "Scores");

        //-------------------------------- Action:
        exit.addActionListener(e -> System.exit(0));

        start.addActionListener(e -> {
            menu.requestFocusInWindow();
            layout.show(cardPanel, "Level");
            levelsPanel.setFocusable(true);
        });

        listScores.addActionListener(e -> {
            menu.requestFocusInWindow();
            layout.show(cardPanel, "Scores");
            scorePanel.setScores(scores);
            scorePanel.setFocusable(true);
        });

        backToLevels.addActionListener(e -> { //game panel -> level panel
            control.setGameOver();
            saveScores();
            panel.requestFocusInWindow();
            layout.show(cardPanel, "Level");
            levelsPanel.setFocusable(true);
        });

        backToMenuFromScores.addActionListener(e -> {
            scorePanel.requestFocusInWindow();
            layout.show(cardPanel, "Menu");
            menu.setFocusable(true);
        });

        backToMenuFromLevel.addActionListener(e -> { //game panel -> menu panel
            control.setGameOver();
            saveScores();
            panel.requestFocusInWindow();
            layout.show(cardPanel, "Menu");
            menu.setFocusable(true);
        });

        backToMenuFromLevels.addActionListener(e -> {
           levelsPanel.requestFocusInWindow();
            layout.show(cardPanel, "Menu");
            menu.setFocusable(true);
        });

        help.addActionListener(e -> {
            levelsPanel.requestFocusInWindow();
            layout.show(cardPanel, "Game");
            control.setLevel("helplevel.txt");
            new Thread(control).start();
            panel.setFocusable(true);
        });
        level1.addActionListener(e -> {
            levelsPanel.requestFocusInWindow();
            layout.show(cardPanel, "Game");
            control.setLevel("level1.txt");
            new Thread(control).start();
            panel.setFocusable(true);
        });
        level2.addActionListener(e -> {
            levelsPanel.requestFocusInWindow();
            layout.show(cardPanel, "Game");
            control.setLevel("level2.txt");
            new Thread(control).start();
            panel.setFocusable(true);
        });
        level3.addActionListener(e -> {
            levelsPanel.requestFocusInWindow();
            layout.show(cardPanel, "Game");
            control.setLevel("level3.txt");
            new Thread(control).start();
            panel.setFocusable(true);
        });

        //--------------------------------------
        add(cardPanel);

        //-------------------------------------- Menu Bar a zene leallitasara
        JMenuBar menuBar = new JMenuBar();
        JMenu setting = new JMenu("Settings");
        JCheckBoxMenuItem soundOn = new JCheckBoxMenuItem("Sound on");

        soundOn.setSelected(true);
        soundOn.addActionListener(e -> {
            if (soundOn.isSelected())
                sounds.start();
            else
                sounds.stop();

        });

        setting.add(soundOn);
        menuBar.add(setting);
        setJMenuBar(menuBar);

        //--------------------------------------Close:
        //a zene es thread leallitasahoz close eseten:
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                control.setGameOver();
                sounds.stop();
                dispose();
                System.exit(0);
            }
        });

        setBounds(100, 50, 1200, 800);
        setVisible(true);
    }

    public void saveScores(){
        if (control.getLevel().equals("level1.txt")) {
            if (control.getCompleted())
                scores[0] = control.getScore();
            else
                scores[0] = 0;
        }
        if (control.getLevel().equals("level2.txt"))
        {
            if (control.getCompleted())
                scores[1] = control.getScore();
            else
                scores[1] = 0;
        }
        if (control.getLevel().equals("level3.txt"))
        {
            if (control.getCompleted())
                scores[2] = control.getScore();
            else
                scores[2] = 0;
        }
    }

}
