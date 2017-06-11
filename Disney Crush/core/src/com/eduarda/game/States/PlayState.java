package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    public float WIDTH = (float) ((((float) 8/10)*Gdx.graphics.getWidth())/8);

    private Gem gemAux1 = new Gem(0,0);
    private Gem gemAux2 = new Gem(0,0);
    private int x = 0, y = 0;

    private Stage stage;
    private Viewport vp;
    
    private int limitX=7;
    private int limitY=12;

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
                gems[i][j] = new Gem(((float) 1/10)*Gdx.graphics.getWidth()+(i*WIDTH), ((float) 9/10)*Gdx.graphics.getHeight()-(j*WIDTH));
            }
        }
        lowerBound = new Texture ("lowerBound.png");

        score = 0;
        time = 18000;
        labels = "score: " + getScore() + "\n" + "time: " + time;
        b = new BitmapFont();
        b.setColor(1, 1, 1, 1);
        b.getData().scale(3);

        vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(vp);

        Gdx.input.setInputProcessor(stage);
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
                                if (consecutiveMove(i,j)) {
                                    gemAux2 = gems[i][j];
                                    gemAux2.setGem(gems[i][j].getTexture());

                                    Texture aux = gems[i][j].getTexture();
                                    Texture aux2 = gems[x][y].getTexture();

                                    gems[i][j].setGem(aux2);
                                    String a = gems[i][j].l;
                                    gems[i][j].setL(gems[x][y].l);
                                    gems[x][y].setGem(aux);
                                    gems[x][y].setL(a);

                                    if (!hasMatch(i, j, gems[i][j].l) && !hasMatch(x, y, gems[x][y].l)) {
                                        gems[i][j].setGem(aux);
                                        gems[x][y].setGem(aux2);

                                    } else {
                                        if (hasMatch(i, j, gems[x][y].l)) {
                                            gems[x][y].getTexture().dispose();
                                            gems[x][y].setBounds(new Rectangle(gems[x][y+1].getPosition().x, gems[x][y+1].getPosition().y, WIDTH, WIDTH));
                                        }
                                        else {
                                            gems[i][j].getTexture().dispose();
                                            gems[i][j].setBounds(new Rectangle(gems[i][j+1].getPosition().x,gems[i][j+1].getPosition().y, WIDTH, WIDTH));
                                        }
                                    }
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
                if (j!=limitY) if(!gems[i][j].collides(gems[i][j+1].getBounds())) gems[i][j].update(dt);
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
        stage.draw();
    }

    /**
     * Disposes of all images, textures, fonts...
     */
    @Override
    public void dispose() {
        stage.dispose();

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
            game.set(new GameOverState(game, 0));
            dispose();
        }
    }

    /**
     * Switches to VictoryState if score has reached 5000 points.
     */
    public void victory() {
        if (getScore() >= 5000) {
            game.set(new GameOverState(game, 1));
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
        if (i == limitX && j == limitY) return x < i || y < j;
        if (i == limitX) return x <= i;
        if (j == limitY) return y <= j;

        return true;
    }

    /**
     * Checks if the attempted move causes any matches. If not, the move is not allowed.
     *
     * @param x Collumn.
     * @param y Line.
     * @param texture Texture that falls into that place if the move is done.
     *
     * @return True if move causes any matches which means it is allowed.
     */
    public boolean hasMatch(int x, int y, String texture) {
        if(isMatch(x-2, y, x-1, y, texture)) return true;
        if(isMatch(x-1, y, x+1, y, texture)) return true;
        if(isMatch(x+1, y, x+2, y, texture)) return true;
        if(isMatch(x, y-2, x, y-1, texture)) return true;
        if(isMatch(x, y-1, x, y+1, texture)) return true;
        if(isMatch(x, y+1, x, y+2, texture)) return true;
        return false;
    }

    /**
     * Checks if the attempted move causes a match.
     *
     * @param x1 Collumn.
     * @param y1 Line.
     * @param x2 Collumn.
     * @param y2 Line.
     * @param texture Texture that falls into that place if the move is done.
     *
     * @return True if move causes match.
     */
    boolean isMatch(int x1, int y1, int x2, int y2, String texture) {
        if(x1 < 0 || x2 < 0 || x1 > limitX || x2 > limitX ||
                y1 < 0 || y2 < 0 || y1 > limitY || y2 > limitY) return false;
        if (texture == gems[x1][y1].l && texture==gems[x2][y2].l) {
            gems[x1][y1].getTexture().dispose();
            gems[x2][y2].getTexture().dispose();
            gems[x1][y1].setBounds(new Rectangle(gems[x1][y+1].getPosition().x,gems[x1][y+1].getPosition().y, WIDTH, WIDTH));
            gems[x2][y2].setBounds(new Rectangle(gems[x2][y+1].getPosition().x,gems[x2][y+1].getPosition().y, WIDTH, WIDTH));
            score += 50;
        }
        return (texture == gems[x1][y1].l && texture==gems[x2][y2].l);
    }

}
