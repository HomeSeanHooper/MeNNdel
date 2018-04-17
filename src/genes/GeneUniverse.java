package genes;

import java.util.List;

public class GeneUniverse {
    /**
     * A universe of geneList. Read from JSON.
     * These are templates for parameter ranges and steps,
     * for instance:
     * "genes": [
     *     {
     *       "name": "rnn_size",
     *       "upperLim": "256",
     *       "lowerLim": "8",
     *       "mutStep": "10",
     *       "outputType": "int"
     *     },
     *     ...
     *
     *     means that a FloatGene should be created with these params.
     */

    List<Gene> geneList;

    public List<Gene> getGeneList() {
        return geneList;
    }

    public void setGeneList(List<Gene> geneList) {
        this.geneList = geneList;
    }
}
