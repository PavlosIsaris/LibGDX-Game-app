package org.scify.libgdxgame.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.scify.libgdxgame.scenes.GamePlay;
import org.scify.libgdxgame.scenes.MainMenu;

public class GameMain extends Game {

	SpriteBatch batch;

	@Override
	public void create () {
        batch = new SpriteBatch();
        setScreen(new GamePlay(this));
	}

	@Override
	public void render () {
        super.render();
	}

	@Override
	public void dispose () {

	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
