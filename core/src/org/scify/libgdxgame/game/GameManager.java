package org.scify.libgdxgame.game;

public class GameManager {
    private static GameManager instance = new GameManager();

    public boolean gameStartedFromMainMenu, isPaused = true;
    public int lifeScore, coinScore, score;

    public static GameManager getInstance() { return instance; }
}
