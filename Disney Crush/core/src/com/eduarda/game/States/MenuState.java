package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduarda.game.Social.Facebook;

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

    private Facebook fb;

    public MenuState(GameStateManager game) {
        super(game);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background = new Texture("background.png");
        play = new Texture("play.png");
        score = new Texture("score.png");
        help = new Texture("help.png");
        //fb = new Facebook();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX()>buttonX && Gdx.input.getX()<(buttonX*2))
            {
                float realY = Gdx.graphics.getHeight() - Gdx.input.getY();

                if (realY>buttonPlay && realY<(buttonPlay+buttonHeight)) {
                    game.set(new PlayState(game));
                    //fb.logIn();
                    dispose();
                }

                if (realY>buttonScore && realY<(buttonScore+buttonHeight)) {
                    game.set(new ScoreState(game));
                    dispose();
                }

                if (realY>buttonHelp && realY<(buttonHelp+buttonHeight)) {
                    game.set(new HelpState(game));
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
