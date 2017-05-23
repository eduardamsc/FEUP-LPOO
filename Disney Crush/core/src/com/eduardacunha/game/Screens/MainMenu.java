package com.eduardacunha.game.Screens;

/**
 * Created by eduardacunha on 29/04/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.eduardacunha.game.DisneyCrush;
import com.eduardacunha.game.Scenes.Hud;

public class MainMenu implements Screen{
    private DisneyCrush game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    //Texture background;

    public MainMenu(DisneyCrush game){
        this.game = game;
        //background = new Texture("background.jpg");
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(DisneyCrush.V_WIDTH, DisneyCrush.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();*/

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
