package org.scify.libgdxgame.ui;

import java.awt.geom.Point2D;

public class ButtonProps {
    private String imgPath;
    private Point2D.Float position;
    private int align;
    private int actionCode;

    public ButtonProps(String imgPath, Point2D.Float position, int align,  int actionCode) {
        this.imgPath = imgPath;
        this.position = position;
        this.align = align;
        this.actionCode = actionCode;
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

    public int getActionCode() {
        return actionCode;
    }
}
