import java.util.List;

public interface Population {
    /**
     * Population interface
     */

    public NNGenome recombine(NNGenome a, NNGenome b);

    public NNGenome mutate(NNGenome a);

    public List<NNGenome> select(List<NNGenome> population);


}
