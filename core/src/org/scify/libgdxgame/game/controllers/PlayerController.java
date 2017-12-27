package org.scify.libgdxgame.game.controllers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import org.scify.libgdxgame.game.sprites.DynamicSprite;

public class PlayerController {

    private World world;
    private DynamicSprite player;
    private static final float PLAYER_MOVING_VELOCITY = 2f;
    public PlayerController(World world) {
        this.world = world;
    }

    public void createPlayer(float x, float y) {
        this.player = new DynamicSprite(world, "player_1", "Player/Player 1.png", x, y);
    }

    public void drawPlayer(Batch batch) {
        // draw the player
        // set the Y axis relative to the player body
        batch.draw(player, player.getX(), player.getY() - player.getHeight() / 2f);
    }

    public void updatePlayerPosition() {
        player.updatePosition();
    }

    public void movePlayerUP() {
        player.moveSpriteOnYAxis(-PLAYER_MOVING_VELOCITY);
    }

    public void movePlayerDOWN() {
        player.moveSpriteOnYAxis(PLAYER_MOVING_VELOCITY);
    }

    public void movePlayerLEFT() {
        player.moveSpriteOnXAxis(-PLAYER_MOVING_VELOCITY);
    }

    public void movePlayerRIGHT() {
        player.moveSpriteOnXAxis(PLAYER_MOVING_VELOCITY);
    }
}
