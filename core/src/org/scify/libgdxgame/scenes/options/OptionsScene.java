package org.scify.libgdxgame.scenes.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import org.scify.libgdxgame.GameMain;
import org.scify.libgdxgame.scenes.GameScene;

public class OptionsScene extends GameScene implements Screen {

    public OptionsScene(GameMain game) {
        super(game, new OptionsButtonsController(game));
    }

    @Override
    public void show() {
        initCamera();
        initResources("Options BG.png");
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
        disposeResources();
    }
}
