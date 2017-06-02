package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by eduardacunha on 02/06/2017.
 */

public class HelpState extends State {
    private Texture helpInfo;
    private Texture back;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);

    protected HelpState(GameStateManager game) {
        super(game);
        helpInfo = new Texture("helpInfo.png");
        back = new Texture("back.png");
    }

    @Override
    protected void handleInput() {
        
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(helpInfo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            sb.draw(back, 0, 0, 2*WIDTH, WIDTH);
        sb.end();
    }

    @Override
    public void dispose() {
        helpInfo.dispose();
        back.dispose();
    }
}
