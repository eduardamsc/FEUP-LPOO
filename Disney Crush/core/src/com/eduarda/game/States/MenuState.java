package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduarda.game.DisneyCrushDemo;

/**
 * Created by eduardacunha on 31/05/2017.
 */

public class MenuState extends State {
    private Texture background;

    public MenuState(GameStateManager game) {
        super(game);
        background = new Texture("background.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            game.set(new PlayState(game));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
