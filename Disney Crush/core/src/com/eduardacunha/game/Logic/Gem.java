package com.eduardacunha.game.Logic;

/**
 * Created by eduardacunha on 16/05/2017.
 */

public class Gem {
    int x;          //column
    int y;          //line
    int dx = 0;     //increment according to direction
    int dy = 0;     //increment according to direction

    char symbol;

    public Gem(char symbol, int x, int y) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    /**
     * Increments dx and dy according to the direction received (up, down, left or right).
     * @param direction Chars 'a', 'd', 'w' and 's'.
     */
    public void increment (char direction) {
        switch (direction) {
            case 'w':
                this.dy = -1;
                break;
            case 's':
                this.dy = 1;
                break;
            case 'a':
                this.dx = -1;
                break;
            case 'd':
                this.dx = 1;
        }
    }

    /**
     * Checks if the character is moving past the limits of the board.
     * @return True If the character isn't moving past the limits of the board.
     */
    public boolean wallChecker() {
        if (this.x+this.dx > 0 && this.x+this.dx < 8 && this.y+this.dy > 0 && this.y+this.dy < 8) return true;
        else return false;
    }
}
