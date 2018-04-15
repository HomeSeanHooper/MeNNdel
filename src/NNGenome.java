import java.util.Random;

public class NNGenome {

    Random rand = new Random();

    // Mutable (mutatable) params

    // Model options
    public int rnnSize;
    public int numLayers;
    public float dropout;

    // Optimization options
    public float learningRate;
    public int gradClip;
    public int lrDecayEvery;
    public float lrDecayFactor;

    // Immutable ranges
    private static final int maxRNNSize = 256;
    private static final int minRNNSize = 8;
    private static final int minNumLayers = 1;
    private static final int maxNumLayers = 4;
    private static final float maxDropout = 1.0f;
    private static final float minDropout = 0.0f;
    private static final float maxLearningRate = 1e-2f;
    private static final float minLearningRate = 1e-3f;
    private static final int maxLRDecayEvery = 10;
    private static final int minLRDecayEvery = 2;
    private static final float maxLRDecayFactor = 1e-2f;
    private static final float minLRDecayRate = 1e-3f;

    // Immutable params
    public static final int maxEpochs = 1;
    public static final int checkPointEvery = 100;
    public static final String gpuBackend = "cuda";
    public static final int gpu = 0;


    private float randomFloat(float min, float max) {
        return rand.nextFloat() * (max - min) + min;
    }

    private int randomInt(int min, int max) {
        return rand.nextInt() * (max - min) + min;
    }

    private int randomRNNSize() {
        return randomInt(minRNNSize, maxRNNSize);
    }

    private int randomNumLayers() {
        return randomInt(minNumLayers, maxNumLayers);
    }

    private float randomDropout() {
        return randomFloat(minDropout, maxDropout);
    }

    private float randomLearningRate() {
        return randomFloat(minLearningRate, maxLearningRate);
    }

    private int randomLRDecayEvery() {
        return randomInt(minLRDecayEvery, maxLRDecayEvery);
    }

    private float randomLRDecayFactor() {
        return randomFloat(minLRDecayRate, maxLRDecayFactor);
    }

    public NNGenome() {
        this.rnnSize = randomRNNSize();
        this.numLayers = randomNumLayers();
        this.dropout = randomDropout();
        this.learningRate = randomLearningRate();
        this.lrDecayEvery = randomLRDecayEvery();
        this.lrDecayFactor = randomLRDecayFactor();
        // enough for now

    }
}
