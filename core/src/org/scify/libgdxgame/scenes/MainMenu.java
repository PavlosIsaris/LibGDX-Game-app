package org.scify.libgdxgame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.game.controllers.BackgroundsController;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.ui.MainMenuButtonsController;

public class MainMenu implements  Screen {

    private GameMain game;
    private OrthographicCamera camera;
    private Viewport  viewport;
    private BackgroundsController backgroundsController;
    private MainMenuButtonsController buttonsController;

    public MainMenu(GameMain game) {
        this.game = game;
        backgroundsController = new BackgroundsController();
        buttonsController = new MainMenuButtonsController(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);
        viewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);
        try {
            backgroundsController.createBackgrounds("Menu BG.png", 1);
        } catch (Exception e) {
            e.printStackTrace();
            dispose();
        }
        buttonsController.createButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        backgroundsController.drawBackgrounds(game.getBatch());
        game.getBatch().end();
        buttonsController.drawButtons();
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
        backgroundsController.disposeBackgrounds();
        buttonsController.dispose();
    }
}
