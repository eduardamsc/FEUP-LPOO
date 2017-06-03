package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eduarda.game.DisneyCrushDemo;
import com.eduarda.game.Sprites.Gem;

/**
 * Created by eduardacunha on 31/05/2017.
 */

public class PlayState extends State {
    private Gem[][] gems;
    private Texture lowerBound;

    private int score;
    private int time;
    private String labels;
    private BitmapFont b;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);

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
        labels = "score: " + score + "\n" + "time: " + time;
        b = new BitmapFont();
        b.setColor(1, 1, 1, 1);
        b.getData().scale(3);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {

            for (int i = 0; i < gems.length; i++) {
                for (int j = 0; j < gems[i].length; j++) {

                    if (Gdx.input.getX()>gems[i][j].getPosition().x && Gdx.input.getX()<(gems[i][j].getPosition().x+WIDTH))
                    {
                        float realY = Gdx.graphics.getHeight() - Gdx.input.getY();

                        if (realY>gems[i][j].getPosition().y && realY<(gems[i][j].getPosition().y+WIDTH)) {
                            
                        }
                    }
                }
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                if (j!=12){
                    if(!gems[i][j].collides(gems[i][j+1].getBounds())) gems[i][j].update(dt);
                }
            }
        }
        labels = "score: " + score + "\n" + "time: " + time;
        time --;
        if (time==0) {
            game.set(new GameOverState(game));
            dispose();
        }

    }

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

    @Override
    public void dispose() {
        for (int i = 0; i < gems.length; i++) {
            for (int j = 3; j < gems[i].length; j++) {
                gems[i][j].getTexture().dispose();
            }
        }
    }
}
