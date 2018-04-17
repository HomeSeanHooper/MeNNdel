import genes.GeneUniverse;
import genome.Genome;

import java.util.*;

public class Population {

    public Population() {
        // read JSON config, set up geneList for genomes
    }

    // This is our population
    private List<Genome> genomes;

    // and the desired size
    private int popSize = 10;

    public void initGenomes(GeneUniverse geneList, int popSize){
        // create popSize genomes based on geneList
        for (int i = 0; i < popSize; i++) {
            genomes.add(new Genome(geneList));
        }
        this.popSize = popSize;

    }

    public void evolve(List<Genome> raw_genomes) {
        /**
         * Load back genomes, provide a fitness score (e.g. loss). This should be added
         * along with the Genome parameter values
         */

        genomes.clear();

        Collections.sort(raw_genomes, new FitnessComparator());

        // elitism. Select the N best genomes and do not mutate them.
        int elitism = 2; // goal is to read from config

        List<Genome> breeders = new ArrayList<>();
        for (int i=0; i<elitism; i++) {
            breeders.add(raw_genomes.remove(0));
        }

        // add children from the best elites
        List<Genome> children = Genome.breed(breeders.get(0), breeders.get(1));

        // now fill out the population with mutated genomes from the raw_genomes list
        genomes.addAll(breeders);
        genomes.addAll(children);

        int fillSize = popSize - genomes.size();
        for (int i=0; i<fillSize; i++) {
            Genome filler = raw_genomes.remove(0);
            filler.mutate();
            genomes.add(filler);
        }

        // should check we have popSize genomes

    }

}

class FitnessComparator implements Comparator<Genome> {
    public int compare(Genome a, Genome b)
    {
        return Math.round(b.getFitness() - a.getFitness());
    }
}
