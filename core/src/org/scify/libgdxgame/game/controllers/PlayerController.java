package org.scify.libgdxgame.game.controllers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.scify.libgdxgame.game.sprites.DynamicSprite;
import org.scify.libgdxgame.helpers.GameInfo;

public class PlayerController {

    private World world;
    private DynamicSprite player;
    public PlayerController(World world) {
        this.world = world;
    }

    public void createPlayer() {
        this.player = new DynamicSprite(world, "player_1", "Player/Player 1.png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f + 250);
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
        player.getBody().applyForce(new Vector2(0, -5f), player.getBody().getWorldCenter(), true);
    }

    public void movePlayerDOWN() {
        player.getBody().applyForce(new Vector2(0, +5f), player.getBody().getWorldCenter(), true);
    }

    public void movePlayerLEFT() {
        // apply a "left side" force to the player's body
        // last parameter allows "sleeping" bodies to be awoken in order to be moved
        player.getBody().applyForce(new Vector2(-5f, 0), player.getBody().getWorldCenter(), true);
    }

    public void movePlayerRIGHT() {
        player.getBody().applyForce(new Vector2(+5f, 0), player.getBody().getWorldCenter(), true);
    }
}
