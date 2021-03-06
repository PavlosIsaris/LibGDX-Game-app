package org.scify.libgdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.scify.libgdxgame.scenes.mainmenu.MainMenu;

public class GameMain extends Game {

	SpriteBatch batch;

	@Override
	public void create () {
        batch = new SpriteBatch();
        setScreen(new MainMenu(this));
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
