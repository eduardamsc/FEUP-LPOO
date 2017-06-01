package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 * Created by eduardacunha on 31/05/2017.
 */

public class MenuState extends State {
    private Texture background;


    private float buttonX = ((float) 1/4*Gdx.graphics.getWidth());
    private float buttonY = ((float) 2/3*Gdx.graphics.getHeight());


    public MenuState(GameStateManager game) {
        super(game);
        background = new Texture("background.png");

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
          /*  Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            if (play.getBounds().contains(tmp.x, tmp.y)) {
                System.out.println("adeus");
                game.set(new PlayState(game));
                dispose();
            }*/
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
