package com.eduarda.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduarda.game.States.GameStateManager;
import com.eduarda.game.States.MenuState;

public class DisneyCrushDemo extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 1600;

	public static final String title = "Disney Crush";
	private GameStateManager game;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		game = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		game.push(new MenuState(game));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.update(Gdx.graphics.getDeltaTime());
		game.render(batch);
	}
	
	@Override
	public void dispose () {
	}
}
