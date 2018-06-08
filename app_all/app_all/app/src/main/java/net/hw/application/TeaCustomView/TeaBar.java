package net.hw.application.TeaCustomView;

/**
 * Created by 17828121783 on 2018/5/19.
 */

public class TeaBar {
    private int strokeWidth;
    private int startX;
    private int startY;
    private int stopY;
    private int stopX;
    private int color;
    private int alpha;

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setStopX(int stopX) {
        this.stopX = stopX;
    }

    public int getStopX() {
        return stopX;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setStopY(int stopY) {
        this.stopY = stopY;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStopY() {
        return stopY;
    }

    public int getColor() {
        return color;
    }
}
