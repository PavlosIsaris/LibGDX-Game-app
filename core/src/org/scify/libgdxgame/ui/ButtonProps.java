package org.scify.libgdxgame.ui;

import java.awt.geom.Point2D;

public class ButtonProps {
    private String imgPath;
    private Point2D.Float position;
    private int align;

    public ButtonProps(String imgPath, Point2D.Float position, int align) {
        this.imgPath = imgPath;
        this.position = position;
        this.align = align;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Point2D.Float getPosition() {
        return position;
    }

    public int getAlign() {
        return align;
    }
}
