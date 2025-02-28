package Server;

import java.io.Serializable;

public enum TTTPiece implements Piece, Serializable {
    X, O, E; // E — пустая клетка

    @Override
    public TTTPiece opposite() {
        switch (this) {
            case X:
                return TTTPiece.O;
            case O:
                return TTTPiece.X;
            default: // E, пустая клетка
                return TTTPiece.E;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case X:
                return "X";
            case O:
                return "O";
            default: // E, пустая клетка
                return " ";
        }
    }
}
