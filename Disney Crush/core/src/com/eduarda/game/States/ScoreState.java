package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
//@@ -10,6 +13,7 @@ import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.eduarda.game.DisneyCrushDemo;

/**
 * Created by beatrizHm on 02/06/17.
 */

/**
 * The Class ScoreState.
 */
public class ScoreState extends State{
    private Texture background;
    private Texture button;
    private Image back;
    private Stage stage;
    private Viewport vp;

    private String villains;
    private String princesses;

    /**
     * Constructor for ScoreState.
     *
     * @param game GameStateManager.
     */
    public ScoreState(GameStateManager game){
        super(game);

        vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        vp.apply();

        stage = new Stage(vp);

        float hButton = (float) ((((float) 1f)*Gdx.graphics.getHeight())/8);
        float wButton = (float) ((((float) 4/3)*Gdx.graphics.getWidth())/8);

        float xButton = 0.5f; //Gdx.graphics.getWidth();
        float yButton = 0.5f; //Gdx.graphics.getHeight();

        background = new Texture("scoreBackground.png");
        button = new Texture("back.png");
        back = new Image(button);
        back.setHeight(hButton);
        back.setWidth(wButton);
        back.setPosition(xButton, yButton);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(back);

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setMenuState();
            }
        });
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
        BitmapFont b = new BitmapFont(Gdx.files.internal("font.fnt"));
        b.setColor(1, 1, 1, 1);
        b.getData().scale(1);

        //villains
        villains = String.valueOf(PlayState.getScore());
        
        //princesses
        princesses = String.valueOf(PlayState.getScore());
        
        sb.begin();
            sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            float factor = (float) ((6.7f-(villains.length() - 1)*0.25f)/10.0f);
            b.draw(sb, villains, (float) factor*Gdx.graphics.getWidth(), (float) 7/12*Gdx.graphics.getHeight());
            b.draw(sb, princesses, (float) factor*Gdx.graphics.getWidth(), (float) 6.10/12*Gdx.graphics.getHeight());
        sb.end();
        stage.draw();
    }

    /**
     * Disposes of all images, textures, fonts...
     */
    @Override
    public void dispose() {
    }
}
