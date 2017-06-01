package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduarda.game.DisneyCrushDemo;
import com.eduarda.game.Sprites.Gem;

/**
 * Created by eduardacunha on 31/05/2017.
 */

public class PlayState extends State {
    private Gem[][] gems;

    protected PlayState(GameStateManager game) {
        super(game);
        gems = new Gem[8][8];
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                gems[i][j] = new Gem(((float) 1/10) * Gdx.graphics.getWidth()+(i * 100), ((float) 3/4) * Gdx.graphics.getHeight()-(j*100)); //medidas hipoteticas têm que ser alteradas. nao adaptadas a qualquer ecra
            }
        }
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                gems[i][j].update(dt);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (int i = 0; i < gems.length; i++) {
            for (int j = 0; j < gems[i].length; j++) {
                sb.draw(gems[i][j].getTexture(), gems[i][j].getPosition().x, gems[i][j].getPosition().y);
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
