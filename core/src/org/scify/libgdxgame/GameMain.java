package org.scify.libgdxgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import helpers.GameInfo;

public class GameMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Sprite playerImage;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("Game BG.png");
		playerImage = new Sprite(new Texture("Player 1.png"));

		playerImage.setPosition((GameInfo.WIDTH / 2) - playerImage.getWidth() / 2,
				(GameInfo.HEIGHT / 2) - playerImage.getHeight() / 2);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		// drawing the player at the center of the screen
		// in libgdx the 0,0 point is at the bottom left corner
		batch.draw(playerImage, playerImage.getX(), playerImage.getY());

		playerImage.setRotation(1);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
