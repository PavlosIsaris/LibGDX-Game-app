package org.scify.libgdxgame.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import org.scify.libgdxgame.game.sprites.DynamicSprite;
import org.scify.libgdxgame.helpers.GameInfo;

public class PlayerController {

    private World world;
    private DynamicSprite player;
    private static final float PLAYER_MOVING_VELOCITY = 2f;
    private TextureAtlas playerAtlas;
    private Animation animation;
    // animation switch frames time
    private float elapsedTime;
    private boolean isPlayerWalking;

    public PlayerController(World world) {
        this.world = world;
    }

    public void createPlayer(float x, float y) {
        this.player = new DynamicSprite(world, "player_1", "Player/Player 1.png", x, y);
        this.player.createBody(false);
        this.player.setCategoryBits(GameInfo.PLAYER);
        this.player.setMaskBits(GameInfo.DEFAULT | GameInfo.COLLECTABLE);
        playerAtlas = new TextureAtlas("Player Animation/Player Animation.atlas");
        // 10 frames per second
        animation = new Animation(1/10f, playerAtlas.getRegions());
    }

    public void drawPlayerIdle(SpriteBatch batch) {
        if(!isPlayerWalking) {
            // draw the player
            // set the Y axis relative to the player body
            batch.draw(player, player.getX(), player.getY() - player.getHeight() / 2f);
        }
    }

    public void drawPlayerAnimation(SpriteBatch batch) {
        if(isPlayerWalking) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            Array<TextureAtlas.AtlasRegion> frames = playerAtlas.getRegions();

            // loop over the player atlas regions and flip them
            // in case the player is walking towards the left direction
            for(TextureRegion frame : frames) {
                // if the player is walking towards the left direction
                if(player.getBody().getLinearVelocity().x < 0 && !frame.isFlipX())
                    frame.flip(true, false);
                else if(player.getBody().getLinearVelocity().x > 0 && frame.isFlipX())
                    frame.flip(true, false);
            }

            elapsedTime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), player.getX(), player.getY() - player.getHeight() / 2f);
        }
    }

    public void updatePlayerPosition() {
        player.updatePosition();
    }

    public void movePlayerUP() {
        player.moveSpriteOnYAxis(PLAYER_MOVING_VELOCITY);
    }

    public void movePlayerDOWN() {
        player.moveSpriteOnYAxis(-PLAYER_MOVING_VELOCITY);
    }

    public void movePlayerLEFT() {
        player.moveSpriteOnXAxis(-PLAYER_MOVING_VELOCITY);
    }

    public void movePlayerRIGHT() {
        player.moveSpriteOnXAxis(PLAYER_MOVING_VELOCITY);
    }

    public void setPlayerWalking(boolean playerWalking) {
        isPlayerWalking = playerWalking;
    }

    public void disposePlayer() {
        player.getTexture().dispose();
    }
}
