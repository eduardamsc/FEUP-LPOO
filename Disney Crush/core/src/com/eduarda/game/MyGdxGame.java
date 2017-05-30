package com.eduarda.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont title;
	private Texture img;
	private Sprite sprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("aa.png");
		sprite = new Sprite(img);
		sprite.rotate(90);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1); //paint screen white
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the screen with color above
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
