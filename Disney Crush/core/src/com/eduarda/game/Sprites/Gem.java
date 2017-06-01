package com.eduarda.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Random;

/**
 * Created by eduardacunha on 01/06/2017.
 */

public class Gem {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private int symbol;

    private Texture gem;

    public Gem(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        symbol = generateSymbol();
        gem = new Texture(chooseTexture());
    }

    public void update(float dt) {
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);

        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return gem;
    }

    public int generateSymbol() {
        Random r = new Random();
        int symbol = r.nextInt(4) + 0;
        char[] symbols = {0, 1, 2, 3, 4};

        return symbols[symbol];
    }

    public java.lang.String chooseTexture() {
        java.lang.String[] textures = {"p_cinderella.png", "p_littlemermaid.png", "p_moana.png", "p_tangles.png", "p_yasmin.png"};
        return textures[this.symbol];
    }
}
