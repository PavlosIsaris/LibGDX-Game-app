package org.scify.libgdxgame.scenes.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import org.scify.libgdxgame.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.ui.ButtonProps;
import org.scify.libgdxgame.ui.ButtonsControllerImpl;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class OptionsButtonsController extends ButtonsControllerImpl {

    public OptionsButtonsController(GameMain game) {
        super(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void createButtons() {
        ArrayList<ButtonProps> buttonsProps = new ArrayList<ButtonProps>();
        initBackButton();
        buttonsProps.add(
                new ButtonProps("Options/Easy.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 40,
                                GameInfo.HEIGHT / 2f + 50), Align.center,
                        ButtonActions.EASY));
        buttonsProps.add(
                new ButtonProps("Options/Medium.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 20,
                                GameInfo.HEIGHT / 2f - 20), Align.center,
                        ButtonActions.MEDIUM));
        buttonsProps.add(
                new ButtonProps("Options/Hard.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f ,
                                GameInfo.HEIGHT / 2f - 90), Align.center,
                        ButtonActions.HARD));
        createAndPositionButtons(buttonsProps);
        setButtonListeners();
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
                    int actionCode = Integer.parseInt(actor.getName());
                    switch (actionCode) {
                        case ButtonActions.EASY:

                            break;
                        case ButtonActions.MEDIUM:
                            break;
                        case ButtonActions.HARD:
                            break;
                    }

                }
            });
        }
    }

    private static class ButtonActions {
        private static final int EASY = 1;
        private static final int MEDIUM = 2;
        private static final int HARD = 3;
    }
}
