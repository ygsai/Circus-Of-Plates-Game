package view;

import Model.ClownGame;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.JOptionPane;

public class GameMenu extends JPanel {

    private static GameMenu obj = null;
    private JFrame fm;
    private JPanel pn;
    private JLabel bg;
    private JButton l1, l2, l3, exit;
    public GameMenu() {
        fm = new JFrame("Circus Of Plates");
        fm.setSize(1000, 563);
        fm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fm.getContentPane().add(this);
        setLayout(null);
        pn = new JPanel();
        pn.setLayout(new OverlayLayout(this.pn));
        pn.setOpaque(false);

        l1 = buttonIcon(330, 140, 330, 100, "/Level1.png");
        l2 = buttonIcon(330, 235, 330, 100, "/Level2.png");
        l3 = buttonIcon(330, 330, 330, 100, "/Level3.png");
        exit = buttonIcon(330, 420, 330, 100, "/Exit.png");
        
        l1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             ClownGame clownGame = new ClownGame(1);
                setIsVisible(false);
            }
        });

        l2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClownGame clownGame = new ClownGame(2);
                setIsVisible(false);
            }
        });

        l3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClownGame clownGame = new ClownGame(3);
                setIsVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon exitImage = new ImageIcon(getClass().getResource("/ExitIcon.png"));
                setIsVisible(false);
                int op = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, exitImage);
                if (op == 0) {
                    System.exit(0);
                } else {
                    fm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    setIsVisible(true);
                }

            }
        });
        add(l1);
        add(l2);
        add(l3);
        add(exit);
        Audio audio = new Audio();
        
       audio.play("CircusMusic.wav",0);
    }

    public void setIsVisible(boolean isVisible) {
        fm.setVisible(isVisible);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image bgImage = new ImageIcon(getClass().getResource("/Background.jpg")).getImage();
        Image nameImage = new ImageIcon(getClass().getResource("/GameName.png")).getImage();
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(nameImage, 0, -170, getWidth(), getHeight(), this);
    }

    private JButton buttonIcon(int x, int y, int width, int height, String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        JButton button = new JButton(icon);
        button.setBounds(x, y, width, height);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }
    

}

             


