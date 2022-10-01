package com.example.bomberm4n.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import com.example.bomberm4n.Graphics.Sprite;

public abstract class Entity {
    /** Tọa độ của thực thể. */
    protected int x;
    protected int y;
    protected Image img;

    /** Constructor */
    public Entity( int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Khởi tạo */
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    /** render ảnh */
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    /** Hàm update cho object */
    public abstract void update();
}
