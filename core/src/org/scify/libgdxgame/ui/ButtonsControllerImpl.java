package org.scify.libgdxgame.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.scify.libgdxgame.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.scenes.mainmenu.MainMenu;
import org.scify.libgdxgame.scenes.interfaces.ButtonsController;

import java.util.ArrayList;


public abstract class ButtonsControllerImpl implements ButtonsController {

    protected Stage stage;
    protected Viewport viewport;
    protected GameMain game;
    protected ArrayList<ImageButton> buttons;
    private ImageButton backBtn;

    public ButtonsControllerImpl(GameMain game) {
        this.game = game;
        viewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        buttons = new ArrayList<ImageButton>();
    }

    protected void initBackButton() {
        backBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Options/Back.png"))));
        backBtn.setPosition(35, 15, Align.bottomLeft);
        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        stage.addActor(backBtn);
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

    public void drawButtons() {
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
    }

    public abstract void createButtons();
    public abstract void dispose();

}
