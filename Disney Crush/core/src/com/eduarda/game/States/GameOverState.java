package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by eduardacunha on 02/06/2017.
 */

/**
 * The Class GameOverState.
 */
public class GameOverState extends State{
    private Texture background;

    /**
     * Constructor for GameOverState.
     * 
     * @param game GameStateManager.
     */
    protected GameOverState(GameStateManager game) {
        super(game);
        background = new Texture("gameOverBackground.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    /**
     * Renders all images, textures, fonts...
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

    }

    /**
     * Disposes of all images, textures, fonts...
     */
    @Override
    public void dispose() {
        background.dispose();
    }
}
