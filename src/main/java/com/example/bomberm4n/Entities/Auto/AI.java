package com.example.bomberm4n.Entities.Auto;

import java.util.Random;

public abstract class AI {
    protected static Random random = new Random();

    public abstract int getDirection();
}
