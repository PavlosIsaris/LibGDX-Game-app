package org.scify.libgdxgame.game.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import org.scify.libgdxgame.game.sprites.StaticSprite;
import org.scify.libgdxgame.helpers.GameInfo;

public class CollectablesController {

    private World world;
    private Array<StaticSprite> collectables = new Array<StaticSprite>();

    public CollectablesController(World world) {
        this.world = world;
    }

    public void createCollectableAtPosition(float x, float y) {
        StaticSprite collectable = new StaticSprite(world, "coin", "Collectables/Coin.png", x, y);
        collectable.createBody(true);
        collectable.setCategoryBits(GameInfo.COLLECTABLE);
        collectables.add(collectable);
    }

    public void drawCollectables(SpriteBatch batch) {
        for(StaticSprite collectable : collectables) {
            batch.draw(collectable, collectable.getX() - collectable.getWidth() / 2f, collectable.getY() - collectable.getHeight() / 2f);
        }
    }

    public void removeCollidedCollectables() {
        int index = 0;
        for(StaticSprite collectable : collectables) {
            if(collectable.getFixture().getUserData() == "Remove") {
                collectable.setCategoryBits(GameInfo.DESTROYED);
                collectable.getTexture().dispose();
                collectables.removeIndex(index);
            }
            index++;
        }
    }

    public void disposeCollectables() {
        for(StaticSprite collectable : collectables) {
            collectable.getTexture().dispose();
        }
    }
}
