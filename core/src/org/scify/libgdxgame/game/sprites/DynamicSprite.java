package org.scify.libgdxgame.game.sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicSprite extends GameSprite {

    public DynamicSprite(World world, String id, String imgName, float x, float y) {
        super(world, id, imgName, x, y);
        setBodyType(BodyDef.BodyType.DynamicBody);
    }

    public void moveSpriteOnXAxis(float x) {
        body.setLinearVelocity(x, body.getLinearVelocity().y);
    }

    public void moveSpriteOnYAxis(float y) {
        body.setLinearVelocity( body.getLinearVelocity().x, y);
    }

}
