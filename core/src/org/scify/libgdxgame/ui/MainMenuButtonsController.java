package org.scify.libgdxgame.ui;

import com.badlogic.gdx.utils.Align;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MainMenuButtonsController extends ButtonsController {

    public MainMenuButtonsController(GameMain game) {
        super(game);
    }

    @Override
    public void createButtons() {
        ArrayList<ButtonProps> buttonsProps = new ArrayList<ButtonProps>();
        buttonsProps.add(
                new ButtonProps("Main Menu/Start Game.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 80,
                                GameInfo.HEIGHT / 2f + 50), Align.center));
        buttonsProps.add(
                new ButtonProps("Main Menu/Highscore.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 60,
                                GameInfo.HEIGHT / 2f - 20), Align.center));
        buttonsProps.add(
                new ButtonProps("Main Menu/Options.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 40,
                                GameInfo.HEIGHT / 2f - 90), Align.center));
        buttonsProps.add(
                new ButtonProps("Main Menu/Quit.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 20,
                                GameInfo.HEIGHT / 2f - 160), Align.center));
        buttonsProps.add(
                new ButtonProps("Main Menu/Music On.png",
                        new Point2D.Float(GameInfo.WIDTH  - 35f,
                                35f), Align.center));
        createAndPositionButtons(buttonsProps);
    }

    @Override
    public void drawButtons() {
        game.getBatch().setProjectionMatrix(getStage().getCamera().combined);
        getStage().draw();
    }

    @Override
    public void dispose() {
        getStage().dispose();
    }

}
