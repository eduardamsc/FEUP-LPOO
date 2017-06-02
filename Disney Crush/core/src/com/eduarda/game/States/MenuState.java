package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.text.Utilities;

/**
 * Created by eduardacunha on 31/05/2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture play;
    private Texture score;
    private Texture help;

    private float buttonX = ((float) 1/4*Gdx.graphics.getWidth());
    private float buttonPlay = ((float) 2/3*Gdx.graphics.getHeight());
    private float buttonHeight = buttonPlay/6;
    private float buttonScore = ((float) 2/3*Gdx.graphics.getHeight())-2*buttonHeight;
    private float buttonHelp = ((float) 1/3*Gdx.graphics.getHeight())-buttonHeight;



    public MenuState(GameStateManager game) {
        super(game);
        Gdx.gl.glClearColor(0.3f, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background = new Texture("background.png");
        play = new Texture("play.png");
        score = new Texture("score.png");
        help = new Texture("help.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            System.out.println(Gdx.input.getY());
            System.out.println(buttonPlay);
            if (Gdx.input.getX()>buttonX && Gdx.input.getX()<(buttonX*2))
            {
                if (Gdx.input.getY()<buttonPlay && Gdx.input.getY()>buttonHeight) {
                    game.set(new PlayState(game));
                    dispose();
                }
            }
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
            sb.draw(play, buttonX, buttonPlay, buttonX*2, buttonHeight);
            sb.draw(score, buttonX, buttonScore, buttonX*2, buttonHeight);
            sb.draw(help, buttonX, buttonHelp, buttonX*2, buttonHeight);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
