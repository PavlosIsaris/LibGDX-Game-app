package org.scify.libgdxgame.scenes.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import org.scify.libgdxgame.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.game.GamePlay;
import org.scify.libgdxgame.scenes.highscores.HighScoreScene;
import org.scify.libgdxgame.ui.ButtonProps;
import org.scify.libgdxgame.ui.ButtonsControllerImpl;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MainMenuButtonsController extends ButtonsControllerImpl {

    public MainMenuButtonsController(GameMain game) {
        super(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void createButtons() {
        ArrayList<ButtonProps> buttonsProps = new ArrayList<ButtonProps>();
        buttonsProps.add(
                new ButtonProps("Main Menu/Start Game.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 80,
                                GameInfo.HEIGHT / 2f + 50), Align.center,
                        ButtonActions.START_GAME));
        buttonsProps.add(
                new ButtonProps("Main Menu/Highscore.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 60,
                                GameInfo.HEIGHT / 2f - 20), Align.center,
                        ButtonActions.HIGH_SCORE));
        buttonsProps.add(
                new ButtonProps("Main Menu/Options.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 40,
                                GameInfo.HEIGHT / 2f - 90), Align.center,
                        ButtonActions.OPTIONS));
        buttonsProps.add(
                new ButtonProps("Main Menu/Quit.png",
                        new Point2D.Float(GameInfo.WIDTH / 2f  - 20,
                                GameInfo.HEIGHT / 2f - 160), Align.center,
                        ButtonActions.QUIT));
        buttonsProps.add(
                new ButtonProps("Main Menu/Music On.png",
                        new Point2D.Float(GameInfo.WIDTH  - 35f,
                                35f), Align.center,
                        ButtonActions.TOGGLE_MUSIC));
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
                        case ButtonActions.START_GAME:
                            startGame();
                            break;
                        case ButtonActions.HIGH_SCORE:
                            highScores();
                            break;
                    }

                }
            });
        }
    }

    private void startGame() {
        game.setScreen(new GamePlay(game));
    }

    private void highScores() {
        game.setScreen(new HighScoreScene(game));
    }

    private static class ButtonActions {
        private static final int START_GAME = 1;
        private static final int HIGH_SCORE = 2;
        private static final int OPTIONS = 3;
        private static final int QUIT = 4;
        private static final int TOGGLE_MUSIC = 5;
    }

}
