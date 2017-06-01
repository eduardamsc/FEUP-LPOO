package com.eduarda.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduarda.game.DisneyCrushDemo;
import com.eduarda.game.Sprites.Gem;

import java.util.Vector;

/**
 * Created by eduardacunha on 31/05/2017.
 */

public class PlayState extends State {
    private Gem gem;
    //private Gem[][] gems;

    protected PlayState(GameStateManager game) {
        super(game);
        /*gems = new Gem[5][5];
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                gems[i][j] = new Gem(50+(i*10), 100+(j*10));
            }
        }*/
        gem = new Gem(50, 100);
        //cam.setToOrtho(false, DisneyCrushDemo.WIDTH/2, DisneyCrushDemo.HEIGHT/2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        gem.update(dt);
       /* for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                gems[i][j].update(dt);
            }
        }*/
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
            sb.draw(gem.getTexture(), gem.getPosition().x, gem.getPosition().y);
        /*for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                sb.draw(gems[i][j].getTexture(), gems[i][j].getPosition().x, gems[i][j].getPosition().y);
            }
        }*/
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
