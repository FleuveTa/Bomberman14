package com.example.bomberm4n.Entities.Auto;

public class random extends AI{
    @Override
    public int getDirection() {
        return rand(1,4);
    }

    public static int rand(int min, int max) {
        try {
            int range = max - min + 1;
            return min + random.nextInt(range);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
