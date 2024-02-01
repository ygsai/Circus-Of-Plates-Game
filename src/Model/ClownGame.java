package Model;

import Controller.Strategy.LevelOneSpeed;
import Controller.Strategy.LevelThreeSpeed;
import Controller.Strategy.LevelTwoSpeed;
import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import view.MenuSingleton;
import static view.MenuSingleton.getInstance;

public class ClownGame extends javax.swing.JFrame {

    @SuppressWarnings("empty-statement")

    public ClownGame(int type) {
        JButton b = new JButton();

        JMenuBar menuBar = new JMenuBar();;
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        JMenuItem menuMenuItem = new JMenuItem("Menu");
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.addSeparator();
        menu.add(resumeMenuItem);
        menu.addSeparator();
        menu.add(menuMenuItem);
        menuBar.add(menu);
        if (type == 1) {

            final GameEngine.GameController gameController = GameEngine.start("Circus Of Plates", new ClownGameLevelOne(new LevelOneSpeed(), 1000, 700), menuBar, JFrame.DISPOSE_ON_CLOSE, Color.RED);

            newMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.changeWorld(new ClownGameLevelOne(new LevelOneSpeed(), 1000, 700));
                }
            });
            pauseMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.pause();
                }
            });
            resumeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.resume();
                }
            });
            menuMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MenuSingleton menu = getInstance();
                    menu.setIsVisible(true);

                }
            });
        } else if (type == 2) {
            final GameEngine.GameController gameController = GameEngine.start("Clown Game", new ClownGameLevelTwo(new LevelTwoSpeed(), 1000, 700), menuBar, JFrame.DISPOSE_ON_CLOSE, Color.RED);
            newMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //  gameController.changeWorld(new ClownGameLevelTwo(new LevelTwoSpeed(),1000, 700));
                    ClownGameLevelTwo gameLevelTwo = new ClownGameLevelTwo(new LevelTwoSpeed(), 1000, 700);

                    gameController.changeWorld(gameLevelTwo);

                }
            });
            pauseMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.pause();
                }
            });
            resumeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.resume();
                }
            });
            menuMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MenuSingleton menu = getInstance();
                    menu.setIsVisible(true);

                }
            });
        } else if (type == 3) {
            final GameEngine.GameController gameController = GameEngine.start("Clown Game", new ClownGameLevelThree(new LevelThreeSpeed(), 1000, 700), menuBar, JFrame.DISPOSE_ON_CLOSE, Color.RED);
            newMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //gameController.changeWorld(new ClownGameLevelThree(new LevelThreeSpeed(),1000, 700));
                    ClownGameLevelOne gameLevelThree = new ClownGameLevelThree(new LevelThreeSpeed(), 1000, 700);
                    gameController.changeWorld(gameLevelThree);

                }
            });
            pauseMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.pause();
                }
            });
            resumeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.resume();
                }
            });
            menuMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MenuSingleton menu = getInstance();
                    menu.setIsVisible(true);

                }
            });
        }
    }

}
