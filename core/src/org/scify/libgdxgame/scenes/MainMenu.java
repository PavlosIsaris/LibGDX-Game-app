package org.scify.libgdxgame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.game.sprites.DynamicSprite;
import org.scify.libgdxgame.game.sprites.StaticSprite;
import org.scify.libgdxgame.game.sprites.GameSprite;
import org.scify.libgdxgame.helpers.GameInfo;

public class MainMenu implements Screen{

    private GameMain game;
    private Texture bg;
    private GameSprite player;
    private GameSprite cloud;
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    public MainMenu(GameMain game) {
        this.game = game;

        this.initCamera();
        bg = new Texture("Game BG.png");

        // creating the world
        // the parameters of the vector define in which position the gravity will be applied
        // for example, here we define that the gravity will be applied only in the y axis
        // (second parameter)
        // the second parameter allows the bodies that this world contains to sleep,
        // in order for the game to not calculate the bodies' position all the time, when the bodies are not moving
        world = new World(new Vector2(0, -9.8f), true);

        // box2D (the physics engine) initially uses a 1 / 1 as pixel / meter ratio
        // so if an image is 80x80 pixels, it translates to 80x80 meters in the physics engine
        // the solution for that is to create a custom pixel to meter ratio in @see GameInfo class

        // position the player at the center and slightly upwards
        player = new DynamicSprite(world, "Player 1.png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f + 250);
        cloud = new StaticSprite(world, "Cloud 1.png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f);
    }

    private void initCamera() {
        camera = new OrthographicCamera();
        // using the custom pixel per meter ratio
        camera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        // set the camera positioned at the center of the screen
        camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    void inputHandler(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // apply a "left side" force to the player's body
            // last parameter allows "sleeping" bodies to be awoken in order to be moved
            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0), player.getBody().getWorldCenter(), true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.getBody().applyLinearImpulse(new Vector2(+0.1f, 0), player.getBody().getWorldCenter(), true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.getBody().applyLinearImpulse(new Vector2(0, -0.1f), player.getBody().getWorldCenter(), true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getBody().applyLinearImpulse(new Vector2(0, +0.1f), player.getBody().getWorldCenter(), true);
        }
    }

    @Override
    public void render(float delta) {

        inputHandler(delta);

        player.updatePosition();
        cloud.updatePosition();
        // clear the screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg,0,0);
        // draw the player
        // set the Y axis relative to the player body
        game.getBatch().draw(player, player.getX(), player.getY() - player.getHeight() / 2f);
        game.getBatch().draw(cloud, cloud.getX() - cloud.getWidth() / 2f + 40, cloud.getY() - cloud.getHeight() / 2f);
        game.getBatch().end();

        debugRenderer.render(world, camera.combined);

        // how many times to calculate physics in a second
        // delta time is the time between 2 frames
        // the second and third parameter defines how many calculations
        // will be done when 2 bodies collide
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
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
}
