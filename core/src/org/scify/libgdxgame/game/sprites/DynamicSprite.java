package org.scify.libgdxgame.game.sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicSprite extends GameSprite {

    public DynamicSprite(World world, String imgName, float x, float y) {
        super(world, imgName, x, y);
        createBody(BodyDef.BodyType.DynamicBody);
    }

}
