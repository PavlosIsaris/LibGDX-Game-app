package org.scify.libgdxgame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.game.player.Player;
import org.scify.libgdxgame.helpers.GameInfo;

public class MainMenu implements Screen{

    private GameMain game;
    private Texture bg;
    private Player player;
    private World world;
    public MainMenu(GameMain game) {
        this.game = game;
        bg = new Texture("Game BG.png");

        // creating the world
        // the parameters of the vector define in which position the gravity will be applied
        // for example, here we define that the gravity will be applied only in the y axis
        // (second parameter)
        // the second parameter allows the bodies that this world contains to sleep,
        // in order for the game to not calculate the bodies' position all the time, when the bodies are not moving
        world = new World(new Vector2(0, -9.8f), true);

        player = new Player(world, "Player 1.png", GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        player.updatePlayer();

        // clear the screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg,0,0);
        game.getBatch().draw(player, player.getX(), player.getY());
        game.getBatch().end();

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
