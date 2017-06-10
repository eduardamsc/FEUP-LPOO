package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by eduardacunha on 02/06/2017.
 */

/**
 * The Class HelpState.
 */
public class HelpState extends State {
    private Texture background;
    private Texture button;
    private Image backButton;
    private Stage stage;
    private Viewport vp;

    /**
     * Constructor for HelpState.
     *
     * @param game GameStateManager.
     */
    protected HelpState(GameStateManager game) {
        super(game);

        vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(vp);

        float hButton = (float) ((((float) 1f)*Gdx.graphics.getHeight())/8);
        float wButton = (float) ((((float) 4/3)*Gdx.graphics.getWidth())/8);

        float xButton = 0.5f;
        float yButton = 0.5f;

        background = new Texture("helpBackground.png");

        button = new Texture("back.png");
        backButton = new Image(button);
        backButton = new Image(button);
        backButton.setHeight(hButton);
        backButton.setWidth(wButton);
        backButton.setPosition(xButton, yButton);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(backButton);

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setMenuState();
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    public void setMenuState(){
        game.set(new MenuState(game));
    }

    /**
     * Handles consequences of user input.
     */
    @Override
    protected void handleInput() {
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
