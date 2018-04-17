package genes;

import java.util.Random;

public class FloatGene implements Gene {

    Random rand = new Random();
    private String name;
    private float val;
    private float mutStep;
    private float upperLim;
    private float lowerLim;
    private String outputType = "float";

    public FloatGene(final String name, final float upperLim, final float lowerLim, final float mutStep, final String outputType) {
        this.name = name;
        this.mutStep = mutStep;
        this.upperLim = upperLim;
        this.lowerLim = lowerLim;
        this.outputType = outputType;
    }

    public FloatGene(){}

    public float getMutStep() {
        return mutStep;
    }

    public void setMutStep(float mutStep) {
        this.mutStep = mutStep;
    }

    public float getUpperLim() {
        return upperLim;
    }

    public void setUpperLim(float upperLim) {
        this.upperLim = upperLim;
    }

    public float getLowerLim() {
        return lowerLim;
    }

    public void setLowerLim(float lowerLim) {
        this.lowerLim = lowerLim;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public float getValue() {
        return val;
    }

    public void setValue(float val) {
        this.val = val;
    }

    public void randomize() {
        val = rand.nextFloat() * (upperLim - lowerLim) + lowerLim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void mutate() {
        double modifier = rand.nextDouble();
        int direction = (rand.nextDouble() > 0.5) ? 1 : -1;
        val += mutStep * modifier * direction;
        normalize();
    }

    public void normalize() {
        if (val > upperLim) {
            val = upperLim;
        }
        if (val < lowerLim) {
            val = lowerLim;
        }
    }

    public String getCommandItem() {

        if (outputType.equals("int")) {
            return String.format("-%s %d", getName(), Math.round(getValue()) );
        } // else default to float
        return String.format("-%s %f", getName(), getValue());
    }


}
