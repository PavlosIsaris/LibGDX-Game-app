package org.scify.libgdxgame.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.ui.interfaces.ButtonsController;

import java.util.ArrayList;


public abstract class ButtonsControllerImpl implements ButtonsController {

    protected Stage stage;
    protected Viewport viewport;
    protected GameMain game;
    protected ArrayList<ImageButton> buttons;

    public ButtonsControllerImpl(GameMain game) {
        this.game = game;
        viewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        buttons = new ArrayList<ImageButton>();
    }

    protected void createAndPositionButtons(ArrayList<ButtonProps> buttonsProps) {
        for(ButtonProps buttonProps : buttonsProps) {
            ImageButton button = new ImageButton(new SpriteDrawable(new Sprite( new Texture("Buttons/" + buttonProps.getImgPath()))));
            button.setPosition((float) buttonProps.getPosition().getX(), (float) buttonProps.getPosition().getY(), buttonProps.getAlign());
            button.setName(String.valueOf(buttonProps.getActionCode()));
            stage.addActor(button);
            buttons.add(button);
        }
    }

    public abstract void createButtons();
    public abstract void drawButtons();
    public abstract void dispose();

}
