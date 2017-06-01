package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.eduarda.game.DisneyCrushDemo;
import com.eduarda.game.Sprites.Gem;

/**
 * Created by eduardacunha on 31/05/2017.
 */

public class PlayState extends State {
    private Gem[][] gems;
    private Texture lowerBound;
    //private Vector2 lowerBoundPos;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);
    public float HEIGHT = (float) ((((float) 3/4) * Gdx.graphics.getHeight())/14);

    protected PlayState(GameStateManager game) {
        super(game);
        gems = new Gem[8][14];
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                gems[i][j] = new Gem(((float) 1/10) * Gdx.graphics.getWidth()+(i * WIDTH), ((float) 9/10) * Gdx.graphics.getHeight()-(j * HEIGHT));
            }
        }
        lowerBound = new Texture ("lowerBound.png");
        //lowerBoundPos = new Vector2(0, 0);
    }

    @Override
    protected void handleInput() {
       /* Gem gem;

        if (Gdx.input.justTouched()) {
            for (int i = 0; i < gems.length; i++) {
                for (int j = 0; j < gems[i].length; j++) {
                    if ((gems[i][j].getPosition().x<Gdx.input.getX() && (gems[i][j].getPosition().x+WIDTH)>Gdx.input.getX())&&(gems[i][j].getPosition().y>Gdx.input.getY() && (gems[i][j].getPosition().y-HEIGHT)<Gdx.input.getY())){

                    }
                }
            }
        }*/
    }

    @Override
    public void update(float dt) {
        handleInput();
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                if (j!=13){
                    if(!gems[i][j].collides(gems[i][j+1].getBounds())) gems[i][j].update(dt);
                }
            }
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (int i = 0; i < gems.length; i++) {
            for (int j = 4; j < gems[i].length; j++) {
                sb.draw(gems[i][j].getTexture(), gems[i][j].getPosition().x, gems[i][j].getPosition().y);
            }
        }
        sb.draw(lowerBound, 0, 0, Gdx.graphics.getWidth(), ((float) 1/8) * Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
