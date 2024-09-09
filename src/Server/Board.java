package Server;

import java.util.List;

public interface Board<Integer> {
    Piece getTurn();

    Board<Integer> move(Integer location);

    List<Integer> getLegalMoves();

    boolean isWin();

    default boolean isDraw() {
        return !isWin() && getLegalMoves().isEmpty();
    }

    double evaluate(Piece player);
}
