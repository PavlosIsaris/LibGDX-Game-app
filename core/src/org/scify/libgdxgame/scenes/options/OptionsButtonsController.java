package org.scify.libgdxgame.scenes.options;

import com.badlogic.gdx.Gdx;

import org.scify.libgdxgame.GameMain;
import org.scify.libgdxgame.ui.ButtonsControllerImpl;

public class OptionsButtonsController extends ButtonsControllerImpl {

    public OptionsButtonsController(GameMain game) {
        super(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void createButtons() {
        initBackButton();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void setButtonListeners() {

    }
}
