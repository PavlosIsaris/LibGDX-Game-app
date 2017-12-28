package org.scify.libgdxgame.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.game.controllers.BackgroundsController;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.ui.ButtonsControllerImpl;

public abstract class GameScene {

    protected GameMain game;
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected BackgroundsController backgroundsController;
    protected ButtonsControllerImpl buttonsController;

    public GameScene(GameMain game, ButtonsControllerImpl buttonsController) {
        this.game = game;
        backgroundsController = new BackgroundsController();
        this.buttonsController = buttonsController;
    }

    protected void initResources(String bgImgPath) {
        try {
            backgroundsController.createBackgrounds(bgImgPath, 1);
        } catch (Exception e) {
            e.printStackTrace();
            disposeResources();
        }
        buttonsController.createButtons();
    }

    protected void initCamera() {
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);
        viewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);
    }

    protected void disposeResources() {
        backgroundsController.disposeBackgrounds();
        buttonsController.dispose();
    }
}
