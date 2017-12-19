package org.scify.libgdxgame.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Sprite{

    private World world;
    private Body body;

    public Player(World world, String name, float x, float y) {
        super(new Texture(name));
        this.world = world;
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
        createBody();
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();

        // a static body type is not affected by gravity or any other force
        // a kinematic body is not affected by gravity but it is affected by other forces
        // a dynamic body is affected by gravity and any other force
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX(), getY());

        body = world.createBody(bodyDef);

        // create an invisible "box" around the player in order to react with the other world objects
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);

        // create a fixture to assign the body to the shape
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        // define the mass (density) for the fixture
        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    /**
     * Updates the player position according to it's body
     */
    public void updatePlayer() {
        this.setPosition(body.getPosition().x, body.getPosition().y);
    }

}
