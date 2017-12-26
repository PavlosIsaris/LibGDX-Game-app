package org.scify.libgdxgame.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BackgroundsController {

    private Sprite[] backgrounds;
    private float lastYPosition;

    public BackgroundsController() {

    }

    public void createBackgrounds() {
        backgrounds = new Sprite[3];

        for(int i = 0; i < backgrounds.length; i++) {
            backgrounds[i] = new Sprite(new Texture("Backgrounds/Game BG.png"));
            backgrounds[i].setPosition(0, -(i * backgrounds[i].getHeight()));
            lastYPosition = Math.abs(backgrounds[i].getY());
        }
    }

    public void drawBackgrounds(Batch batch) {
        for (Sprite background : backgrounds) {
            batch.draw(background, background.getX(), background.getY());
        }
    }

    public void updateBackgrounds(final float cameraYPos) {
        for(Sprite background: backgrounds) {
            if(background.getY() - background.getHeight() / 2f - 5 > cameraYPos) {
                float newPosition = background.getHeight() + lastYPosition;
                background.setPosition(0, -newPosition);
                lastYPosition = Math.abs(newPosition);
            }
        }
    }
}
