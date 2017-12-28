package org.scify.libgdxgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import org.scify.libgdxgame.game.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class HighScoreButtonsController extends ButtonsControllerImpl {

    private Label scoreLabel;
    private Label coinLabel;

    public HighScoreButtonsController(GameMain game) {
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
        createAndPositionButtons(buttonsProps);
        initBackButton();
        setButtonListeners();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/blow.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        // 40 pixels
        parameter.size = 40;
        BitmapFont scoreFont = generator.generateFont(parameter);
        BitmapFont coinFont = generator.generateFont(parameter);

        scoreLabel = new Label("100", new Label.LabelStyle(scoreFont, Color.WHITE));
        coinLabel = new Label("100", new Label.LabelStyle(coinFont, Color.WHITE));
        scoreLabel.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 115);
        coinLabel.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 215);
        stage.addActor(scoreLabel);
        stage.addActor(coinLabel);
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
