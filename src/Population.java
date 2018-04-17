import genes.GeneUniverse;
import genome.Genome;

import java.util.*;
import java.util.Random;

public class Population {

    public Population(int popSize) {
        // read JSON config, set up geneList for genomes
        genomes = new ArrayList<>();
        tasks = new ArrayList<>();
        elites = new ArrayList<>();
        this.popSize = popSize;
    }

    // This is our population
    private List<Genome> genomes;

    // Keep elites alive
    private List<Genome> elites;

    // We also need a task queue
    private List<Genome> tasks;

    // and the desired size
    private int popSize = 10;

    public void initGenomes(GeneUniverse geneList){
        // create popSize genomes based on geneList
        for (int i = 0; i < popSize; i++) {
            genomes.add(new Genome(geneList));
        }
    }


    private String show(List<Genome> genomeArrayList) {
        StringBuilder sb = new StringBuilder("Fitness\n");
        for (Genome genome: genomeArrayList ) {
            sb.append(String.format("%.2f\n", genome.getFitness()));
        }
        return sb.toString();
    }

    public String showGenomes() {
        return show(genomes);
    }

    public String showTasks() {
        return show(tasks);
    }


    public void evolve() {
        /**
         * Load back genomes, provide a fitness score (e.g. loss). This should be added
         * along with the Genome parameter values
         */

        // genomes should be full. Should check. Likewise, tasks should be empty
        tasks.clear();

        Collections.sort(genomes, new FitnessComparator());

        // elitism. Select the N best genomes and do not mutate them.
        int elitism = 2; // goal is to read from config

        List<Genome> breeders = new ArrayList<>();
        for (int i=0; i<elitism; i++) {
            breeders.add(genomes.remove(0));
        }

        // add children from the best elites
        List<Genome> children = Genome.breed(breeders.get(0), breeders.get(1));

        // add these to the tasks list
        tasks.addAll(breeders);
        tasks.addAll(children);

        int fillSize = popSize - tasks.size();
        for (int i=0; i<fillSize; i++) {
            Genome filler = genomes.remove(0);
            filler.mutate();
            tasks.add(filler);
        }
        // remove remaining genomes and sort the tasks
        genomes.clear();
        Collections.sort(tasks, new FitnessComparator());

        // now, tasks should be full and genomes empty
    }

    public void randomFitness() {
        /**
         * Mostly for testing
         */
        Random rand = new Random();
        for (Genome genome: genomes) {
            genome.setFitness(rand.nextFloat());

        }
    }

    public void run() {
        // step 1, init population
        //initGenomes();
        int maxGenerations = 10; // config this
        for (int i=0; i<maxGenerations; i++) {
            //while (tasks.isEmpty())
        }
    }

}

class FitnessComparator implements Comparator<Genome> {
    public int compare(Genome a, Genome b)
    {
        return (b.getFitness() - a.getFitness()) > 0 ? 1: -1;
    }
}
