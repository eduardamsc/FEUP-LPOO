package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.eduarda.game.Social.Facebook;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by eduardacunha on 31/05/2017.
 */

/**
 * The Class MenuState.
 */
public class MenuState extends State {
    private Texture background;
    private Image play;
    private Image score;
    private Image help;
    private Stage stage;
    private Viewport vp;
    private Texture button;
    private Facebook fb;

    /**
     * Constructor for MenuState.
     *
     * @param game GameStateManager.
     */
    public MenuState(GameStateManager game) {
        super(game);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        vp.apply();

        stage = new Stage(vp);

        float xButton = Gdx.graphics.getWidth()*1/4;

        float wButton = Gdx.graphics.getWidth()*1/2;
        float hButton = Gdx.graphics.getHeight()*5/40;


        float buttonPlay = ((float) 3/5*Gdx.graphics.getHeight());
        float buttonScore = ((float) 2/5*Gdx.graphics.getHeight());
        float buttonHelp = ((float) 1/5*Gdx.graphics.getHeight());

        background = new Texture("background.png");
        button = new Texture("play.png");
        play = new Image(button);
        play.setWidth(wButton);
        play.setHeight(hButton);
        play.setPosition(xButton, buttonPlay);

        button = new Texture("score.png");
        score = new Image(button);
        score.setWidth(wButton);
        score.setHeight(hButton);
        score.setPosition(xButton, buttonScore);

        button = new Texture("help.png");
        help = new Image(button);
        help.setWidth(wButton);
        help.setHeight(hButton);
        help.setPosition(xButton, buttonHelp);
        //fb = new Facebook();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(play);
        stage.addActor(score);
        stage.addActor(help);

        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setPlayState();
            }
        });

        score.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setScoreState();
            }
        });

        help.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setHelpState();
            }
        });

        //Gdx.input.setInputProcessor(stage);
    }

    public void setPlayState(){
        game.set(new PlayState(game));
    }

    public void setScoreState(){
        game.set(new ScoreState(game));
    }

    public void setHelpState(){
        game.set(new HelpState(game));
    }

    /**
     * Handles consequences of user input.
     */
    @Override
    public void handleInput() {
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
        sb.end();
        stage.draw();
    }

    /**
     * Disposes of all images, textures, fonts...
     */
    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }
}
