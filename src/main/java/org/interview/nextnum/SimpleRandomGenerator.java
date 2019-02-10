package org.interview.nextnum;

import java.util.Random;

public class SimpleRandomGenerator implements RandomGenerator
{
    public float nextFloat()
    {
        return new Random().nextFloat();
    }
}
