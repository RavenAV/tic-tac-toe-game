package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartGame {
    //public static final int SERVER_PORT = 4080;

    public static void main(String[] args) {
        //Scanner console = new Scanner(System.in);
        try {
            JFrame startFrame = new JFrame("TicTacToe");
            startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            startFrame.setSize(200,200);
            JButton startButton = new JButton("Start game!");
            startFrame.getContentPane().add(startButton);
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        new GameFrame();
                    }catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            startFrame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
