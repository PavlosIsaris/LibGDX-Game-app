package org.scify.libgdxgame.helpers;

public class GameInfo {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    // box2D (the physics engine) initially uses a 1 / 1 as pixel / meter ratio
    // so if an image is 80x80 pixels, it translates to 80x80 meters in the physics engine
    // the solution for that is to create a custom pixel to meter ratio
    // defining a pixel per meter value that will be used in the physics engine
    // 100 pixels will be equal to 1 meter.
    public static final int PPM = 100;
    public static final short DEFAULT  = 1;
    public static final short PLAYER = 2;
    public static final short COLLECTABLE = 4;
    public static final short DESTROYED = 6;
}
