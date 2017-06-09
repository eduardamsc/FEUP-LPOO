package com.eduarda.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by eduardacunha on 31/05/2017.
 */

/**
 * The Class State.
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager game;
    
    /**
     * Constructor for State.
     *
     * @param game GameStateManager.
     */
    protected State(GameStateManager game) {
        this.game = game;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    /**
     * Handles consequences of user input.
     */
    protected abstract void handleInput();
    
    /**
     * Updates game.
     */
    public abstract void update(float dt);
    
    /**
     * Renders all images, textures, fonts...
     */
    public abstract void render(SpriteBatch sb);
    
    /**
     * Disposes of all images, textures, fonts...
     */
    public abstract void dispose();
}
