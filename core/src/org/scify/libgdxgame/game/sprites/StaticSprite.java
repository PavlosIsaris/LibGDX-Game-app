package org.scify.libgdxgame.game.sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;


public class StaticSprite extends GameSprite {

    public StaticSprite(World world, String id, String imgName, float x, float y) {
        super(world, id, imgName, x, y);
        setBodyType(BodyDef.BodyType.StaticBody);
    }
}
