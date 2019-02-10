package org.interview.nextnum;


import java.util.Arrays;
import java.util.TreeMap;

public class RandomGen {
    // Values that may be returned by nextNum()
    private int[] randomNums;
    // Probability of the occurence of randomNums
    private float[] probabilities;
    // Random generator for a float value between 0 and 1
    private RandomGenerator randomGenerator;
    // TreeMap for storing interval possibilities
    private TreeMap<Float, Integer> randomMap;

    /**
     *
     * @param randomNums Values that may be returned by nextNum()
     * @param probabilities Probability of the occurence of randomNums
     */
    public RandomGen(int[] randomNums, float[] probabilities){
        this(randomNums, probabilities, new SimpleRandomGenerator());
    }

    /**
     *
     * @param randomNums Values that may be returned by nextNum()
     * @param probabilities Probability of the occurence of randomNums
     * @param randomGenerator Random Generator which implements nextValue() for returning a float between 0 and 1
     */
    public RandomGen(int[] randomNums, float[] probabilities, RandomGenerator randomGenerator){

        if(randomNums == null){
            throw new IllegalArgumentException("randomNums not allowed to be null");
        }

        if(probabilities == null){
            throw new IllegalArgumentException("probabilities not allowed to be null");
        }

        if(randomGenerator == null){
            throw new IllegalArgumentException("randomGenerator not allowed to be null");
        }

        if(randomNums.length != probabilities.length){
            throw new IllegalArgumentException("randomNums array length with " + randomNums + " isn't equal to probabilities array length with " + probabilities + " !");
        }

        float total = 0;

        for(float probability : probabilities){
            total += probability;
        }

        if(total != 1){
            throw new IllegalArgumentException("The sum of all probabilities should be 1 but is " + total);
        }

        this.randomNums = randomNums;
        this.probabilities = probabilities;
        this.randomGenerator = randomGenerator;

        // build the tree map for finding the random number in the interval
        // So we only need O(log n) for runtime
        randomMap = new TreeMap();
        float interval = 0;
        for(int i=0; i<probabilities.length; i++) {
            interval += probabilities[i];
            randomMap.put(interval, randomNums[i]);
        }
    }

    /**
     Returns one of the randomNums. When this method is called
     multiple times over a long period, it should return the
     numbers roughly with the initialized probabilities.
     */
    public int nextNum() {
        float randFloat = randomGenerator.nextFloat();
        int result = randomMap.ceilingEntry(randFloat).getValue();
        return result;
    }

    public String toString()
    {
        return "randomNums=" + Arrays.toString(randomNums) + "\nprobabilities=" + Arrays.toString(probabilities);
    }

//    public static void main(String[] args){
//        int[] randomNums =      {-1,    0,    1,     2,    3};
//        float[] probabilities = {0.01f, 0.3f, 0.58f, 0.1f, 0.01f};
//        //float probabilities = {0.01f, 0.31f,0.89f, 0.99f,1.00f};
//        RandomGen randomGen = new RandomGen(randomNums, probabilities);
//        for(int i=0; i < 50; i++){
//            randomGen.nextNum();
//        }
//    }
}