package Client;

import Server.SocketThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    // for sockets
    public static final int SERVER_PORT = 4080;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;

    // for board
    private int[] positions = new int[9];

    //for UI
    private JFrame gameFrame = new JFrame("TicTacToe");

    public GameFrame() throws IOException {
        this.socket = new Socket("localhost", SERVER_PORT);;
        output = new DataOutputStream(this.socket.getOutputStream());
        input = new DataInputStream(this.socket.getInputStream());

        for(int i = 0; i < 9; i++){
            positions[i] = -1;
        }
        this.initComponent();
        // Socket socket = new Socket("localhost", SERVER_PORT);
    }

    private void initComponent() {
        System.out.println("Server is ready!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton[] buttons = new JButton[9];
        for(int i = 0; i < 9; i++){
            if (positions[i] == 1){
                buttons[i] = new JButton("X");
                buttons[i].setEnabled(false);
            } else if (positions[i] == 0) {
                buttons[i] = new JButton("O");
                buttons[i].setEnabled(false);
            } else {
                buttons[i] = new JButton("");
                int finalI = i;
                buttons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {


                        try {
                            output.writeUTF(Integer.toString(finalI));//Отправляем серверу запрос
                            String response = input.readUTF();//Ожидаем ответ от сервера
                            if(response.contains("!")) {
                                buttons[finalI].setText("X");
                                buttons[finalI].setEnabled(false);
                                positions[finalI] = 1;
                                String[] responseFinal = response.split(" ");

                                try {
                                    int k = Integer.parseInt(responseFinal[0]);
                                    positions[k] = 0;
                                    buttons[k].setText("0");
                                    buttons[k].setEnabled(false);
                                    System.out.print(responseFinal[1]);
                                    for (int u = 0; u < 9; u++) {
                                        buttons[u].setEnabled(false);
                                    }
                                    JFrame jFrame = new JFrame();
                                    JOptionPane.showMessageDialog(jFrame, responseFinal[1]);
                                }
                                catch (Exception ex) {
                                    System.out.print(responseFinal[0]);
                                    for(int u = 0; u < 9; u++) {
                                        buttons[u].setEnabled(false);
                                    }
                                    JFrame jFrame = new JFrame();
                                    JOptionPane.showMessageDialog(jFrame, responseFinal);
                                }
                                return;
                                //break;
                            } else {
                                buttons[finalI].setText("X");
                                buttons[finalI].setEnabled(false);
                                positions[finalI] = 1;
                                int k = Integer.parseInt(response);
                                positions[k] = 0;
                                buttons[k].setText("0");
                                buttons[k].setEnabled(false);
                                //buttons[k] = new JButton("O");
                                //buttons[k].setEnabled(false);
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }

            switch (i) {
                case 0:
                    buttons[i].setBounds(50,50,50,50);
                    break;
                case 1:
                    buttons[i].setBounds(120,50,50,50);
                    break;
                case 2:
                    buttons[i].setBounds(190,50,50,50);
                    break;
                case 3:
                    buttons[i].setBounds(50,120,50,50);
                    break;
                case 4:
                    buttons[i].setBounds(120,120,50,50);
                    break;
                case 5:
                    buttons[i].setBounds(190,120,50,50);
                    break;
                case 6:
                    buttons[i].setBounds(50,190,50,50);
                    break;
                case 7:
                    buttons[i].setBounds(120,190,50,50);
                    break;
                case 8:
                    buttons[i].setBounds(190,190,50,50);
                    break;
            }
            gameFrame.add(buttons[i]);
        }

        gameFrame.setSize(310,350);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
    }
}
