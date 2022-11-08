package com.example.bomberm4n.Entities.Auto;

public class random extends AI{
    @Override
    public int getDirection() {
        return 1 + random.nextInt(4);
    }
}
