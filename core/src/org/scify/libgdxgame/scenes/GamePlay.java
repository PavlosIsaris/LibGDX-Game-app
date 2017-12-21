package org.scify.libgdxgame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.scify.libgdxgame.game.CloudsController;
import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.game.sprites.DynamicSprite;
import org.scify.libgdxgame.game.sprites.GameSprite;
import org.scify.libgdxgame.helpers.GameInfo;

public class GamePlay implements Screen, ContactListener {

    private GameMain game;
    private Sprite[] backgrounds;
    private float lastYPosition;
    private GameSprite player;
    private World world;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private CloudsController cloudsController;

    public GamePlay(GameMain game) {
        this.game = game;
        this.createWorld();
        cloudsController = new CloudsController(world);
        this.initCamera();
        this.createSprites();
    }

    private void initCamera() {
        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                mainCamera);

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM,
                GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        debugRenderer = new Box2DDebugRenderer();
    }

    private void createWorld() {
        // creating the world
        // the parameters of the vector define in which position the gravity will be applied
        // for example, here we define that the gravity will be applied only in the y axis
        // (second parameter)
        // the second parameter allows the bodies that this world contains to sleep,
        // in order for the game to not calculate the bodies' position all the time, when the bodies are not moving
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(this);
    }

    private void createSprites() {
        // position the player at the center and slightly upwards
        player = new DynamicSprite(world, "player_1", "Player/Player 1.png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f + 250);
        createRepeatableSprites();
    }

    private void createRepeatableSprites() {
        cloudsController.createClouds();
    }

    private void createBackgrounds() {
        backgrounds = new Sprite[3];

        for(int i = 0; i < backgrounds.length; i++) {
            backgrounds[i] = new Sprite(new Texture("Backgrounds/Game BG.png"));
            backgrounds[i].setPosition(0, -(i * backgrounds[i].getHeight()));
            lastYPosition = Math.abs(backgrounds[i].getY());
        }
    }

    void checkBackgroundsOutOfBounds() {
        for(Sprite background: backgrounds) {
            if(background.getY() - background.getHeight() / 2f - 5 > mainCamera.position.y) {
                float newPosition = background.getHeight() + lastYPosition;
                background.setPosition(0, -newPosition);
                lastYPosition = Math.abs(newPosition);
            }
        }
    }

    private void drawBackgrounds() {
        for (Sprite background : backgrounds) {
            game.getBatch().draw(background, background.getX(), background.getY());
        }
    }

    void update(float deltaTime) {
        moveCamera();
        checkBackgroundsOutOfBounds();
    }

    private void moveCamera() {
        mainCamera.position.y -= 1;
    }

    void inputHandler(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // apply a "left side" force to the player's body
            // last parameter allows "sleeping" bodies to be awoken in order to be moved
            player.getBody().applyForce(new Vector2(-5f, 0), player.getBody().getWorldCenter(), true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.getBody().applyForce(new Vector2(+5f, 0), player.getBody().getWorldCenter(), true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            System.out.println("UP");
            player.getBody().applyForce(new Vector2(0, -5f), player.getBody().getWorldCenter(), true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getBody().applyForce(new Vector2(0, +5f), player.getBody().getWorldCenter(), true);
        }
    }

    @Override
    public void show() {
        createBackgrounds();
    }

    @Override
    public void render(float delta) {
        update(delta);
        inputHandler(delta);

        player.updatePosition();

        // clear the screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        drawBackgrounds();
        drawSprites();
        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();
        // how many times to calculate physics in a second
        // delta time is the time between 2 frames
        // the second and third parameter defines how many calculations
        // will be done when 2 bodies collide
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    private void drawSprites() {
        // draw the player
        // set the Y axis relative to the player body
        game.getBatch().draw(player, player.getX(), player.getY() - player.getHeight() / 2f);
        cloudsController.drawClouds(game.getBatch());
    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void beginContact(Contact contact) {

        // first body must be the player
        Fixture firstBody;
        // second body is the other object that the player collided with
        Fixture secondBody;

        if(contact.getFixtureA().getUserData().equals("player_1")) {
            firstBody = contact.getFixtureA();
            secondBody = contact.getFixtureB();
        } else {
            firstBody = contact.getFixtureB();
            secondBody = contact.getFixtureA();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
