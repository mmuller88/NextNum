package org.interview.nextnum;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RandomGenTest
{
    @Test
    void testInvalidArgumentsForRandomGenClass()
    {
        int[] randomNums = {-1, 0, 1, 2, 3};
        int[] randomNumsWrongLength = {-1, 0, 1, 2, 3, 4, 5};
        float[] probabilities = {0.01f, 0.3f, 0.58f, 0.1f, 0.01f};
        float[] probabilitiesWrongLength = {0.01f, 0.3f, 0.58f};
        float[] probabilitiesWrongPossibilities = {0.01f, 0.3f, 0.58f, 0.1f, 0.03f};

        assertThrows(IllegalArgumentException.class, ()-> new RandomGen(null, probabilities));
        assertThrows(IllegalArgumentException.class, ()-> new RandomGen(randomNums, null));
        assertThrows(IllegalArgumentException.class, ()-> new RandomGen(null, null));
        assertThrows(IllegalArgumentException.class, ()-> new RandomGen(randomNumsWrongLength, probabilities));
        assertThrows(IllegalArgumentException.class, ()-> new RandomGen(randomNums, probabilitiesWrongLength));
        assertThrows(IllegalArgumentException.class, ()-> new RandomGen(randomNumsWrongLength, probabilitiesWrongLength));

        // sum of probabilities isn't 1
        assertThrows(IllegalArgumentException.class, ()-> new RandomGen(randomNums, probabilitiesWrongPossibilities));
    }

    @Test
    void testNextNum()
    {
        int[] randomNums =      {-1,    0,    1,     2,    3};
        float[] probabilities = {0.01f, 0.3f, 0.58f, 0.1f, 0.01f};
        //      intervals     = {0.01f, 0.31f,0.89f, 0.99f,1.00f};
        RandomGenerator mockRandomGenerator = mock(RandomGenerator.class);
        // mock float number generator
        when(mockRandomGenerator.nextFloat()).thenReturn(0.60779625f, 0.9318236f, 0.92072386f, 0.83241814f, 0.881017f,
                0.3097881f, 0.7781256f, 0.29991102f, 0.25514543f, 0.3972559f, 0.0042955f, 0.9999f);
        RandomGen randomGen = new RandomGen(randomNums, probabilities, mockRandomGenerator);

        // map for counting the results
        Map resultMap = new HashMap<Integer, Integer>();
        for(int i=0; i < 12; i++){
            int nextNum = randomGen.nextNum();
            if(resultMap.get(nextNum) == null){
                resultMap.put(nextNum, 1);
            } else {
                resultMap.put(nextNum, (Integer) resultMap.get(nextNum) + 1);
            }
        }

        assertEquals(3, resultMap.get(0));
        assertEquals(5, resultMap.get(1));
        assertEquals(2, resultMap.get(2));
        assertEquals(1, resultMap.get(-1));
        assertEquals(1, resultMap.get(3));
    }
}