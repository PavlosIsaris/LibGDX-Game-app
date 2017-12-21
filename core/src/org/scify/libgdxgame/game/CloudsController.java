package org.scify.libgdxgame.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import org.scify.libgdxgame.game.sprites.StaticSprite;
import org.scify.libgdxgame.helpers.GameInfo;
import org.scify.libgdxgame.helpers.Utils;

public class CloudsController {

    private World world;
    private Array<StaticSprite> clouds = new Array<StaticSprite>();
    private static final float DISTANCE_BETWEEN_CLOUDS = 250f;
    // these variables are helpers for randomizing the position of the clouds in the X axis
    private float minX, maxX;

    public CloudsController(World world) {
        this.world = world;
        minX = GameInfo.WIDTH / 2f - 110;
        maxX = GameInfo.WIDTH / 2f + 110;
    }

    public void createClouds() {
        for(int i = 0; i < 2; i ++) {
            clouds.add(new StaticSprite(world, "dark_cloud_" + i, "Clouds/Dark Cloud.png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f));
        }

        int index = 1;
        for(int i = 0; i < 6; i ++) {
            clouds.add(new StaticSprite(world, "cloud_" + i, "Clouds/Cloud " + index + ".png", GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f));
            index++;
            if(index == 4)
                index = 1;
        }

        clouds.shuffle();
        positionClouds();
    }

    private void positionClouds() {
        // first cloud must not be a "dark cloud"
        while (clouds.get(0).getSpriteId().startsWith("dark_cloud_")) {
            clouds.shuffle();
        }

        float positionY = GameInfo.WIDTH / 2f;
        int controlX = 0;
        for(StaticSprite cloud : clouds) {
            float tempX = 0;

            // if controlX is 0, position at right side
            // else, to the left side
            if(controlX == 0) {
                tempX = Utils.randomBetweenNumbers(maxX - 60, maxX);
                // on next iteration, position to the left
                controlX = 1;
            } else if (controlX == 1) {
                tempX = Utils.randomBetweenNumbers(minX + 60, minX);
                // on next iteration, position to the right
                controlX = 0;
            }

            cloud.setSpritePosition(tempX, positionY);
            positionY -= DISTANCE_BETWEEN_CLOUDS;
        }
    }

    public void drawClouds(SpriteBatch batch) {
        for(StaticSprite cloud : clouds) {
            batch.draw(cloud, cloud.getX() - cloud.getWidth() / 2f, cloud.getY() - cloud.getHeight() / 2f);
        }
    }
}
