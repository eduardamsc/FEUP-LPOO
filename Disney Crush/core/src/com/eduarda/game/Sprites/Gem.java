package com.eduarda.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Random;

/**
 * Created by eduardacunha on 01/06/2017.
 */

public class Gem {
    private static final float GRAVITY = -((((float) 3/4) * Gdx.graphics.getHeight())/14);
    private Vector3 position;
    private Vector3 velocity;
    private int symbol;

    private Rectangle bounds;

    public float WIDTH = (float) ((((float) 8/10) * Gdx.graphics.getWidth())/8);

    private Texture gem;

    public Gem(float x, float y) {
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0, 0);
        this.symbol = generateSymbol();
        this.gem = new Texture(chooseTexture());

        this.bounds = new Rectangle(position.x, position.y, WIDTH, WIDTH);
    }

    public void update(float dt) {
        this.velocity.add(0, GRAVITY, 0);
        this.velocity.scl(dt);
        this.position.add(0, this.velocity.y, 0);
        if (this.position.y < 3*WIDTH) this.position.y += 3*WIDTH;

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return gem;
    }

    public Rectangle getBounds() { return bounds; }

    public int generateSymbol() {
        Random r = new Random();
        int symbol = r.nextInt(5) + 0;
        char[] symbols = {0, 1, 2, 3, 4};

        return symbols[symbol];
    }

    public java.lang.String chooseTexture() {
        java.lang.String[] textures = {"p_ariel.png", "p_belle.png", "p_cinderella.png", "p_snowWhite.png", "p_rapunzel.png"};
        return textures[this.symbol];
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }
}
