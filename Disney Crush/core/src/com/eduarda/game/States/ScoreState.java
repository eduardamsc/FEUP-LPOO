package com.eduarda.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by beatrizHm on 02/06/17.
 */

/**
 * The Class ScoreState.
 */
public class ScoreState extends State{
    private Texture background;
    private Texture back;

    private String villains;
    private String princesses;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);

    /**
     * Constructor for ScoreState.
     *
     * @param game GameStateManager.
     */
    public ScoreState(GameStateManager game){
        super(game);

        background = new Texture("scoreBackground.png");
        back = new Texture("back.png");
    }

    /**
     * Handles consequences of user input.
     */
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
            sb.draw(back, 0, 0, 2*WIDTH, WIDTH);
        float factor = (float) ((6.7 - (villains.length() - 1) * 0.25) / 10.0);
        b.draw(sb, villains, (float) factor*Gdx.graphics.getWidth(), (float) 7/12*Gdx.graphics.getHeight());
        b.draw(sb, princesses, (float) factor*Gdx.graphics.getWidth(), (float) 6.10/12*Gdx.graphics.getHeight());
        sb.end();
    }

    /**
     * Disposes of all images, textures, fonts...
     */
    @Override
    public void dispose() {
        background.dispose();
        back.dispose();
    }
}
