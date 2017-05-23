package com.eduardacunha.game.Logic;

import java.util.Vector;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by eduardacunha on 22/05/2017.
 */

public class Level {

    public Board board = new Board();
    public Vector<Gem> gems;

    /**
     * Moves the character according to the direction received (up, down, left or right).
     * @param direction Chars 'a', 'd', 'w' and 's'.
     */
    public void move(char direction) {
       /* this.gem.increment(direction);
        if (this.gem.wallChecker() && rightMove()) {
            this.gem.x += this.gem.dx;
            this.gem.y += this.gem.dy;
        }*/
    }

    /**
     * Checks if the character is moving causing a match.
     * @return True If the character is moving causing a match.
     */
 /*   public boolean rightMove() {
        if (this.board.board[this.gem.x][this.gem.y] == this.gem.symbol) return true;
        else return false;
    }*/


    /**
     * Refills the gems vector according to the symbols on the board,
     */
    public void updateMap() {
        this.gems.clear();
        for (int x = 0; x < this.board.board.length; x++){
            for (int y = 0; y < this.board.board[x].length; y++) {
                this.gems.add(new Gem(this.board.board[x][y], x, y));
            }
        }
    }
}
