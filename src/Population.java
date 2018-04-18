import genome.Genome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Population {

    // Keep elites alive
    private List<Genome> elites;

    // evolution params
    private int popSize;
    private int elitism;
    private int nMutations;

    public Population(int popSize, int elitism, int nMutations) {
        this.popSize = popSize;
        this.elitism = elitism;
        this.nMutations = nMutations;
        elites = new ArrayList<>();
    }

    public List<Genome> getElites() {
        return elites;
    }

    public static void sortGenomes(List<Genome> genomes) {
        genomes.sort(new FitnessComparator());
    }

    public List<Genome> evolve(List<Genome> parentGeneration) {
        /**
         * This is the main method. It takes a population of genomes (any size is fine),
         * breeds, evolves and mutates. The elites are retained between runs,
         * so they will always contribute to the next generation.
         *
         * This method returns a list of genomes of popSize
         */

        // Prepare the next generation
        List<Genome> childGeneration = new ArrayList<>();

        // Append elites (if any) to the parents
        parentGeneration.addAll(elites);

        // Sort the parents by fitness
        parentGeneration.sort(new FitnessComparator());

        // Keep the best as elites
        elites.clear();
        elites.addAll(parentGeneration.subList(0, elitism));
        parentGeneration.removeAll(parentGeneration.subList(0, elitism));

        // Mutate genomes
        for (Genome genome : parentGeneration) {
            genome.mutate(nMutations);
        }

        // Add children from the elites
        for (Genome dad : elites) {
            for (Genome mom : elites) {
                if (dad != mom) {
                    childGeneration.addAll(Genome.breed(dad, mom));
                }
            }

        }

        // Allow some random genomes breed as well
        Collections.shuffle(parentGeneration);
        childGeneration.addAll(Genome.breed(parentGeneration.get(0), parentGeneration.get(1)));

        // and now just fill out the population with parent genomes
        Collections.shuffle(parentGeneration);
        childGeneration.addAll(parentGeneration);

        // and return a child generation of popSize
        return childGeneration.subList(0, popSize);

    }

}


class FitnessComparator implements Comparator<Genome> {
    public int compare(Genome a, Genome b) {
        return (b.getFitness() - a.getFitness()) > 0 ? 1 : -1;
    }
}
