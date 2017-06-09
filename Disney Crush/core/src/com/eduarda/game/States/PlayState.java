package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduarda.game.Sprites.Gem;

/**
 * Created by eduardacunha on 31/05/2017.
 */

/**
 * The Class PlayState.
 */
public class PlayState extends State {
    private Gem[][] gems;
    private Texture lowerBound;

    private static int score;
    private int time;
    private String labels;
    private BitmapFont b;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);

    private Gem gemAux1 = new Gem(0,0);
    private Gem gemAux2 = new Gem(0,0);
    private int x = 0, y = 0;

    /**
     * Constructor for PlayState.
     *
     * @param game GameStateManager.
     */
    protected PlayState(GameStateManager game) {
        super(game);
        gems = new Gem[8][13];
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                gems[i][j] = new Gem(((float) 1/10) * Gdx.graphics.getWidth()+(i * WIDTH), ((float) 9/10) * Gdx.graphics.getHeight()-(j * WIDTH));
            }
        }
        lowerBound = new Texture ("lowerBound.png");

        score = 0;
        time = 18000;
        labels = "score: " + getScore() + "\n" + "time: " + time;
        b = new BitmapFont();
        b.setColor(1, 1, 1, 1);
        b.getData().scale(3);
    }

    /**
     * Handles consequences of user input.
     */
    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            for (int i = 0; i < gems.length; i++) {
                for (int j = 0; j < gems[i].length; j++) {
                    if (Gdx.input.getX()>gems[i][j].getPosition().x && Gdx.input.getX()<(gems[i][j].getPosition().x+WIDTH))
                    {
                        float realY = Gdx.graphics.getHeight() - Gdx.input.getY();
                        if (realY>gems[i][j].getPosition().y && realY<(gems[i][j].getPosition().y+WIDTH)) {
                            if (gemAux1.getPosition().x == 0) {
                                gemAux1 = gems[i][j];
                                gemAux1.setGem(gems[i][j].getTexture());
                                x = i;
                                y = j;
                            } else if (gemAux2.getPosition().x == 0) {
                                if (consecutiveMove(i,j) && (hasMatch(i, j, gems[x][y].getTexture()) || hasMatch(x, y, gems[i][j].getTexture())))
                                {
                                    gemAux2 = gems[i][j];
                                    gemAux2.setGem(gems[i][j].getTexture());
                                    Texture aux = gems[i][j].getTexture();
                                    gems[i][j].setGem(gemAux1.getTexture());
                                    gems[i][j].setGem(aux);
                                } else gemAux2 = gems[i][j];
                            } else {
                                gemAux1 = new Gem(0,0);
                                gemAux2 = new Gem(0,0);

                                gemAux1 = gems[i][j];
                                gemAux1.setGem(gems[i][j].getTexture());
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Updates game.
     */
    @Override
    public void update(float dt) {
        handleInput();

        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                if (j!=12) if(!gems[i][j].collides(gems[i][j+1].getBounds())) gems[i][j].update(dt);
            }
        }

        labels = "score: " + getScore() + "\n" + "time: " + time;

        timeRunningOut();
        victory();
    }

    /**
     * Renders all images, textures, fonts...
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (int i = 0; i < gems.length; i++) {
            for (int j = 3; j < gems[i].length; j++) {
                sb.draw(gems[i][j].getTexture(), gems[i][j].getPosition().x, gems[i][j].getPosition().y);
            }
        }
        sb.draw(lowerBound, 0, 0, Gdx.graphics.getWidth(), 3*WIDTH);
        b.draw(sb, labels, (float) 1/10*Gdx.graphics.getWidth(), (float) 11/12*Gdx.graphics.getHeight());
        sb.end();

    }

    /**
     * Disposes of all images, textures, fonts...
     */
    @Override
    public void dispose() {
        for (int i = 0; i < gems.length; i++) {
            for (int j = 3; j < gems[i].length; j++) {
                gems[i][j].getTexture().dispose();
            }
        }
    }
    
    /**
     * Gets static variable score.
     *
     * @return Score of type int.
     */
    public static int getScore() {
        return score;
    }

    /**
     * Decreases time and switches to GameOverState if time has run out.
     */
    public void timeRunningOut() {
        time --;
        if (time==0) {
            game.set(new GameOverState(game));
            dispose();
        }
    }

    /**
     * Switches to VictoryState if score has reached 5000 points.
     */
    public void victory() {
        if (getScore() >= 5000) {
            game.set(new victoryState(game));
            dispose();
        }
    }

    /**
     * Checks if the attempted move is being done 1 unit to the left, right, up or down. If not, the move is not allowed.
     *
     * @param i Collumn.
     * @param j Line.
     *
     * @return True if move is allowed.
     */
    public boolean consecutiveMove(int i, int j) {
        if (Math.abs(i-x) + Math.abs(j-y) != 1) return false;
        if (i == 0 && j == 0) return x > i || y > j;
        if (i == 7 && j == 3) return x < i || y < j;
        if (i == 7) return x <= i;
        if (j == 13) return y <= j;

        return false;
    }

    /**
     * Checks if the attempted move causes a horizontal match.
     *
     * @param i Collumn.
     * @param j Line.
     * @param texture Texture that falls into that place if the move is done.
     *
     * @return True if move causes match.
     */
    public boolean isHorizontalMatch(int x, int y, Texture texture){
        if (x < 0 || y + 2 > 7) return false;
        return (texture.equals(gems[x+1][y].getTexture()) && texture.equals(gems[x+2][y].getTexture()));
    }

    /**
     * Checks if the attempted move causes a vertical match.
     *
     * @param i Collumn.
     * @param j Line.
     * @param texture Texture that falls into that place if the move is done.
     *
     * @return True if move causes match.
     */
    public boolean isVerticalMatch(int x, int y, Texture texture){
        if (y < 0 || y + 2 > 13) return false;
        return (texture.equals(gems[x][y+1].getTexture()) && texture.equals(gems[x][y+2].getTexture()));
    }

    /**
     * Checks if the attempted move causes any matches. If not, the move is not allowed.
     *
     * @param i Collumn.
     * @param j Line.
     * @param texture Texture that falls into that place if the move is done.
     *
     * @return True if move causes any matches which means it is allowed.
     */
    public boolean hasMatch(int x, int y, Texture texture) {
        for(int i = -2; i <= 0; i++){
            if(isHorizontalMatch(x, y, texture) || isVerticalMatch(x, y, texture)) return true;
        }
        return false;
    }
}
