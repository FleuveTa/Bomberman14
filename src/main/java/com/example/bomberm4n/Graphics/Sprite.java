package com.example.bomberm4n.Graphics;

public class Sprite {
    /** Các loại ảnh. */
    public static final String TILE = "Tile";
    public static final String NORMAL = "Normal";
    public static final String ANIM = "Animation";

    /** Cài đặt mặc định kích cỡ ảnh (Dành cho Tile) */
    public static final int SCALED_SIZE = 60;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    /** Dành cho ảnh Tile và Normal */
    private String path;
    private int cropX;
    private int cropY;
    private int w;
    private int h;
    private int scaleFactor;

    private int tileSize;
    public int[] pixels;

    private String type;
    SpriteSheet sheet;
}
