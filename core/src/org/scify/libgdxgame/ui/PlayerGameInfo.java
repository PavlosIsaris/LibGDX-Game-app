package org.scify.libgdxgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.scify.libgdxgame.GameMain;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.scenes.mainmenu.MainMenu;


public class PlayerGameInfo {

    private GameMain game;
    private Stage stage;
    private Viewport viewport;

    private WidgetGroup pausePanel;
    private Image coinImg, lifeImg, scoreImg, pausePanelImg;
    private Label coinLabel, lifeLabel, scoreLabel;
    private ImageButton pauseBtn, resumeBtn, quitBtn;

    public PlayerGameInfo(GameMain game) {
        this.game = game;
        viewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        createLabels();
        createImages();
        createBtnAndAddListener();
        createLifeAndScoreInfoTable();
        createPausePanel();
        hidePausePanel();
    }

    private void createLifeAndScoreInfoTable() {
        Table lifeAndCoinTable = new Table();
        lifeAndCoinTable.top().left();
        lifeAndCoinTable.setFillParent(true);

        // TODO refactor into new Grid Class
        lifeAndCoinTable.add(lifeImg).padLeft(10).padTop(10);
        lifeAndCoinTable.add(lifeLabel).padLeft(10).padTop(10);
        lifeAndCoinTable.row();

        lifeAndCoinTable.add(coinImg).padLeft(10).padTop(10);
        lifeAndCoinTable.add(coinLabel).padLeft(10).padTop(10);


        Table scoreTable = new Table();
        scoreTable.top().right();
        scoreTable.setFillParent(true);
        scoreTable.add(scoreImg).padRight(20).padTop(15);
        scoreTable.row();
        scoreTable.add(scoreLabel).padRight(20).padTop(15);

        stage.addActor(lifeAndCoinTable);
        stage.addActor(scoreTable);
    }

    public Stage getStage() {
        return stage;
    }

    private void createLabels() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/blow.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        // 40 pixels
        parameter.size = 40;
        BitmapFont font = generator.generateFont(parameter);
        coinLabel = new Label("x0", new Label.LabelStyle(font, Color.WHITE));
        lifeLabel = new Label("x2", new Label.LabelStyle(font, Color.WHITE));
        scoreLabel = new Label("100", new Label.LabelStyle(font, Color.WHITE));
    }

    private void createImages() {
        coinImg = new Image(new Texture("Collectables/Coin.png"));
        lifeImg = new Image(new Texture("Collectables/Life.png"));
        scoreImg = new Image(new Texture("Buttons/Gameplay/Score.png"));
    }

    private void createBtnAndAddListener() {
        pauseBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Gameplay/Pause.png"))));
        pauseBtn.setPosition(470, 17, Align.bottomRight);
        pauseBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // pause the game
                // show the pause panel
                showPausePanel();
            }
        });
        stage.addActor(pauseBtn);
    }

    private void createPausePanel() {
        pausePanel = new WidgetGroup();
        pausePanelImg = new Image(new Texture("Pause/Pause Panel.png"));
        resumeBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Pause/Resume.png"))));
        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Pause/Quit 2.png"))));

        pausePanelImg.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, Align.center);
        resumeBtn.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f + 50f, Align.center);
        quitBtn.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 80f, Align.center);

        resumeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hidePausePanel();
            }
        });

        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        pausePanel.addActor(pausePanelImg);
        pausePanel.addActor(resumeBtn);
        pausePanel.addActor(quitBtn);

        stage.addActor(pausePanel);
    }

    private void hidePausePanel() {
        pausePanel.setVisible(false);
    }

    private void showPausePanel() {
        pausePanel.setVisible(true);
    }

}
