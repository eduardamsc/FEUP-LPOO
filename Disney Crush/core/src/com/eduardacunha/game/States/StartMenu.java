package com.eduardacunha.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by eduardacunha on 16/05/2017.
 */

public abstract class StartMenu {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
   /* protected GameStateManager gam;

    protected State(GameStateManager gam) {
        this.gam = gam;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }*/

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render (SpriteBatch ab);

}
