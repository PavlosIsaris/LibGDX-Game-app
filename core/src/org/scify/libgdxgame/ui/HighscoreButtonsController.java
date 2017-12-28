package org.scify.libgdxgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class HighscoreButtonsController extends ButtonsControllerImpl {

    public HighscoreButtonsController(GameMain game) {
        super(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void createButtons() {
        ArrayList<ButtonProps> buttonsProps = new ArrayList<ButtonProps>();
//        buttonsProps.add(
//                new ButtonProps("Main Menu/Start Game.png",
//                        new Point2D.Float(GameInfo.WIDTH / 2f  - 80,
//                                GameInfo.HEIGHT / 2f + 50), Align.center,
//                        0));
        setButtonListeners();
    }

    @Override
    public void drawButtons() {
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void setButtonListeners() {
        for(ImageButton button : buttons) {
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ImageButton pressedBtn = (ImageButton) actor;
                    int actionCode = Integer.parseInt(actor.getName());
//                    switch (actionCode) {
//                        case MainMenuButtonsController.ButtonActions.START_GAME:
//                            startGame();
//                            break;
//                    }

                }
            });
        }
    }
}
