package genome;

import com.sun.istack.internal.NotNull;
import genes.Gene;
import genes.GeneUniverse;

import java.util.*;


import java.util.stream.Collectors;

public class Genome{

    private HashMap<String, Gene> genes;

    private float fitness = 0.0f; // initial fitness is 0

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public HashMap<String, Gene> getGenes() {
        return genes;
    }

    public Genome(GeneUniverse genes) {
        this.genes = new HashMap<>();
        for (Gene gene: genes.getGeneList()) {
            gene.randomize();
            this.genes.put(gene.getName(), gene);
        }
    }

    public Genome(List<Gene> genes) {
        this.genes = new HashMap<>();
        for (Gene gene: genes) {
            this.genes.put(gene.getName(), gene);
        }
    }

    public Genome(HashMap<String, Gene> genes) {
        this.genes = new HashMap<>();
        this.genes.putAll(genes);
    }

    public Genome(Genome parent) {
        /**
         * clones a parent
         */
        this(parent.getGenes());
        this.setFitness(parent.getFitness());
    }


    public ArrayList<String> getItems() {
        return new ArrayList<>(genes.keySet());
    }

    public Gene getGene(String name) {
        return genes.get(name);
    }

    public void mutate(int nMutations) {
        /**
         * randomly choose features to mutate. May select the same one twice.
         */
        Random rand = new Random();
        ArrayList<String> geneNames = new ArrayList<>(genes.keySet());

        for (int i = 0 ; i < nMutations; i++) {
            String item = geneNames.get(rand.nextInt(geneNames.size()));
            genes.get(item).mutate();
        }
    }

    public void mutate() {
        mutate(1);
    }

    public void addGene(@NotNull Gene gene) {
        genes.put(gene.getName(), gene);
    }

    public static List<Genome> breed(@NotNull Genome dad, @NotNull Genome mom, double xoverRate) {
        /**
         * Recombines two genomes, creates two children
         *
         */
        Random rand = new Random();
        Genome child1 = new Genome(dad);
        Genome child2 = new Genome(mom);

        for (String item : child1.getItems()) {
            if (rand.nextDouble() > xoverRate) {
                Gene temp = child1.getGene(item);
                child1.addGene(child2.getGene(item));
                child2.addGene(temp);
            }
        }

        return Arrays.asList(child1, child2);
    }

    public static List<Genome> breed(@NotNull Genome dad, @NotNull Genome mom) {
        return breed(dad, mom, 0.5);
    }

    public String getCommands() {
        return genes.entrySet().stream().map(x -> x.getValue().getCommandItem() ).collect(Collectors.joining(" "));
    }

}


