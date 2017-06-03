package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by beatrizHm on 02/06/17.
 */

public class ScoreState extends State{
    private Texture highScore;
    private Texture back;

    private Label vilans;
    private Label princess;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);

    public ScoreState(GameStateManager game){
        super(game);

        //Gdx.gl.glClearColor(0.3f, 0, 1, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        highScore = new Texture("highScore.png");
        back = new Texture("back.png");

       /* Table table = new Table();
        table.top();

        //vilans
        vilans = new Label(String.format("%02d",game.getScore()),new Label.LabelStyle(LoadGraphics.getFont(), Color.BLACK));
        vilans.setFontScale(0.75f);
        vilans.setAlignment(Align.center);
        table.center();
        table.padTop(125);
        table.add(vilans).padLeft(200).padBottom(20);
        table.row();

        //princess
        princess = new Label(String.format("%02d",game.getScore()),new Label.LabelStyle(LoadGraphics.getFont(), Color.BLACK));
        princess.setFontScale(0.75f);
        princess.setAlignment(Align.center);
        table.center();
        table.padTop(170);
        table.add(princess).padLeft(200).padBottom(20);
        table.row();*/

    }

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

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
            sb.draw(highScore, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            sb.draw(back, 0, 0, 2*WIDTH, WIDTH);
        sb.end();
    }

    @Override
    public void dispose() {
        highScore.dispose();
        back.dispose();
    }
}
