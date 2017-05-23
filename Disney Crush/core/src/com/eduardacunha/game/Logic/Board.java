package com.eduardacunha.game.Logic;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by eduardacunha on 16/05/2017.
 */

public class Board {
    char[][] board;

    public Board() {
        this.board=new char[9][9];
    }

    /**
     * Fills the board with random characters by associating 5 chars to ints from 1 to 5 generated randomly.
     */
    public void fillBoard() {
        int aux;
        for (int x = 0; x < this.board.length; x++){
            for (int y = 0; y < this.board[x].length; y++) {
                aux = random.nextInt(5 - 1 + 1) + 1;
                switch(aux) {
                    case '1':
                        this.board[x][y] = 'A'; //personagem 1
                        break;
                    case '2':
                        this.board[x][y] = 'B'; //personagem 2
                        break;
                    case '3':
                        this.board[x][y] = 'C'; //personagem 3
                        break;
                    case '4':
                        this.board[x][y] = 'D'; //personagem 4
                        break;
                    case '5':
                        this.board[x][y] = 'E'; //personagem 5
                }
            }
        }
    }
}
