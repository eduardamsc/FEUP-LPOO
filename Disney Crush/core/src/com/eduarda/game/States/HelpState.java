package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by eduardacunha on 02/06/2017.
 */

/**
 * The Class HelpState.
 */
public class HelpState extends State {
    private Texture background;
    private Texture back;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);

    /**
     * Constructor for HelpState.
     *
     * @param game GameStateManager.
     */
    protected HelpState(GameStateManager game) {
        super(game);
        background = new Texture("helpBackground.png");
        back = new Texture("back.png");
    }

    /**
     * Handles consequences of user input.
     */
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            float realY = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (Gdx.input.getX()<2*WIDTH && realY<WIDTH) {
                game.set(new MenuState(game));
                dispose();
            }
        }
    }

    /**
     * Updates game.
     */
    @Override
    public void update(float dt) {
        handleInput();
    }

    /**
     * Renders all images, textures, fonts...
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            sb.draw(back, 0, 0, 2*WIDTH, WIDTH);
        sb.end();
    }

    /**
     * Disposes of all images, textures, fonts...
     */
    @Override
    public void dispose() {
        background.dispose();
        back.dispose();
    }
}
