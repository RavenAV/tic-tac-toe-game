package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketThread extends Thread {
    private final Socket client;
    private DataOutputStream output;
    private DataInputStream input;
    private TTTBoard board;

    private int counter;

    public SocketThread(Socket client) throws IOException {
        this.client = client;
        output = new DataOutputStream(this.client.getOutputStream());
        input = new DataInputStream(this.client.getInputStream());
        board = new TTTBoard();
        start();
    }

    @Override
    public void run() {
        try {
            output = new DataOutputStream(client.getOutputStream());
            input = new DataInputStream(client.getInputStream());
            counter = 0;
            while (true){
                String response = input.readUTF();
                Integer humanMove = Integer.parseInt(response);
                board = board.move(humanMove);
                if (board.isWin()){
                    output.writeUTF("Human_wins!");
                    break;
                } else if (board.isDraw()) {
                    output.writeUTF("Draw!");
                    break;
                }
                Integer computerMove = Minimax.findBestMove(board, 9);
                board = board.move(computerMove);
                String str = null;
                if (board.isWin()) {
                    //output.writeUTF("Computer wins!");
                    str = "Computer_wins!";
                } else if (board.isDraw()) {
                    //output.writeUTF("Draw!");
                    str = "Draw!";
                }
                if (str != null) {
                  output.writeUTF(computerMove.toString() + " " + str);
                } else {
                  output.writeUTF(computerMove.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
