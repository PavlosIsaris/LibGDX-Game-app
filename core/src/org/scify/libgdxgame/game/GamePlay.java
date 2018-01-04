package org.scify.libgdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

import org.scify.libgdxgame.game.controllers.BackgroundsController;
import org.scify.libgdxgame.game.controllers.CloudsController;
import org.scify.libgdxgame.GameMain;
import org.scify.libgdxgame.game.controllers.CollectablesController;
import org.scify.libgdxgame.game.controllers.PlayerController;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.ui.PlayerGameInfo;

import java.awt.geom.Point2D;

public class GamePlay implements Screen, ContactListener {

    private GameMain game;


    private World world;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private BackgroundsController backgroundsController;
    private CloudsController cloudsController;
    private CollectablesController collectablesController;
    private PlayerController playerController;
    private PlayerGameInfo playerGameInfo;

    public GamePlay(GameMain game) {
        this.game = game;
        this.createWorld();
        collectablesController = new CollectablesController(world);
        cloudsController = new CloudsController(world, collectablesController);
        playerController = new PlayerController(world);
        backgroundsController = new BackgroundsController();
        playerGameInfo = new PlayerGameInfo(game);
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
        cloudsController.initializeClouds();
        Point2D.Float firstCloudPosition = cloudsController.getCoordsOfFirstCloud();
        playerController.createPlayer((float) firstCloudPosition.getX(), (float) firstCloudPosition.getY() + 100f);
    }
    void update(float deltaTime) {
        moveCamera();
        // todo refactor using observer pattern
        backgroundsController.updateBackgrounds(mainCamera.position.y);
        cloudsController.setCameraY(mainCamera.position.y);
        cloudsController.createAndArrangeNewClouds();
        playerController.updatePlayerPosition();
        collectablesController.removeOffScreenCollectables(mainCamera.position.y);
    }

    private void moveCamera() {
        mainCamera.position.y -= 1;
    }

    void inputHandler(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerController.setPlayerWalking(true);
            playerController.movePlayerLEFT();
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerController.setPlayerWalking(true);
            playerController.movePlayerRIGHT();
        } else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerController.setPlayerWalking(true);
            playerController.movePlayerUP();
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerController.setPlayerWalking(true);
            playerController.movePlayerDOWN();
        } else {
            playerController.setPlayerWalking(false);
        }
    }

    @Override
    public void show() {
        try {
            backgroundsController.createBackgrounds("Game BG.png", 3);
        } catch (Exception e) {
            e.printStackTrace();
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        inputHandler(delta);

        // clear the screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        backgroundsController.drawBackgrounds(game.getBatch());
        drawSprites();
        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        game.getBatch().setProjectionMatrix(playerGameInfo.getStage().getCamera().combined);
        playerGameInfo.getStage().draw();
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();
        // how many times to calculate physics in a second
        // delta time is the time between 2 frames
        // the second and third parameter defines how many calculations
        // will be done when 2 bodies collide
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    private void drawSprites() {
        playerController.drawPlayerIdle(game.getBatch());
        playerController.drawPlayerAnimation(game.getBatch());
        cloudsController.drawClouds(game.getBatch());
        collectablesController.drawCollectables(game.getBatch());
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
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
        world.dispose();
        backgroundsController.disposeBackgrounds();
        playerController.disposePlayer();
        cloudsController.disposeClouds();
        collectablesController.disposeCollectables();
        debugRenderer.dispose();
    }

    @Override
    public void beginContact(Contact contact) {

        // first body must be the player
        Fixture playerBody;
        // second body is the other object that the player collided with
        Fixture objectBody;

        if(contact.getFixtureA().getUserData().equals("player_1")) {
            playerBody = contact.getFixtureA();
            objectBody = contact.getFixtureB();
        } else if(contact.getFixtureB().getUserData().equals("player_1")) {
            playerBody = contact.getFixtureB();
            objectBody = contact.getFixtureA();
        } else {
            return;
        }

        if(objectBody.getUserData().equals("coin")) {
            objectBody.setUserData("Remove");
            collectablesController.removeCollidedCollectables();
        }

        if(objectBody.getUserData().equals("life")) {
            objectBody.setUserData("Remove");
            collectablesController.removeCollidedCollectables();
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
