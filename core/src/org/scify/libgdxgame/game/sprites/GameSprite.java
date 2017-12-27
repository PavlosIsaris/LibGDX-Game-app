package org.scify.libgdxgame.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.scify.libgdxgame.game.interfaces.Drawable;
import org.scify.libgdxgame.helpers.GameInfo;

public abstract class GameSprite extends Sprite implements Drawable {

    protected World world;
    protected Body body;
    protected String spriteId;
    protected BodyDef.BodyType bodyType;

    public GameSprite(World world, String spriteId, String imgPath, float x, float y) {
        super(new Texture(imgPath));
        this.world = world;
        this.spriteId = spriteId;
        if(x != 0 && y != 0)
            setPosition(x - getWidth() / 2f, y - getHeight() / 2f);
    }

    public String getSpriteId() {
        return spriteId;
    }

    protected void createBody(BodyDef.BodyType bodyType) {
        BodyDef bodyDef = new BodyDef();

        // a static body type is not affected by gravity or any other force
        // a kinematic body is not affected by gravity but it is affected by other forces
        // a dynamic body is affected by gravity and any other force
        bodyDef.type = bodyType;
        this.bodyType = bodyType;
        // using the custom pixel per meter ratio
        bodyDef.position.set((getX() - 45) / GameInfo.PPM,
                getY() / GameInfo.PPM);

        body = world.createBody(bodyDef);

        // create an invisible "box" around the player in order to react with the other world objects
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2 ) / GameInfo.PPM,
                (getHeight() / 2 ) / GameInfo.PPM);

        // create a fixture to assign the body to the shape
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        // define the mass (density) for the fixture
        fixtureDef.density = 1f;
        // make fixture not sliding on other surfaces
        fixtureDef.friction = 2f;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(spriteId);
        // free memory of shape
        shape.dispose();
    }

    public void setBodyType(BodyDef.BodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public void setSpritePosition(float x, float y) {
        setPosition(x, y);
        createBody(bodyType);
    }

    @Override
    public void updatePosition() {
        this.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

    public Body getBody() {
        return body;
    }
}
