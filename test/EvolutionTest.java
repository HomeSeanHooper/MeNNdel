import genes.FloatGene;
import genes.Gene;
import genome.Genome;
import genes.GeneUniverse;

import java.util.*;


class EvolutionTest {

    GeneUniverse geneUniverse;

    public EvolutionTest() {
        Gene g1 = new FloatGene("1", 2,1,1,"int");
        Gene g2 = new FloatGene("2", 5.0f,1.0f,1.0f,"float");
        Gene g3 = new FloatGene("3", 200,100,10,"int");
        g1.randomize();
        g2.randomize();
        g3.randomize();
        geneUniverse = new GeneUniverse();
        geneUniverse.setGeneList(Arrays.asList(g1, g2, g3));
    }

    public Genome createGenome() {
        return new Genome(geneUniverse.getGeneList());
    }

    public void show(Genome genome) {
        System.out.println(genome.prettyPrint());
    }

    public void show(List<Genome> genomes) {
        for (Genome genome: genomes ) {
            show(genome);
        }
    }

    public void breed(Genome mom, Genome dad) {
        List<Genome> children = Genome.breed(mom, dad);
        for (Genome child: children) {
            System.out.println(child.getCommands());
        }
    }

    public Genome mutate(Genome genome) {
        Genome child = new Genome(genome);
        child.mutate(2);
        return child;
    }

    private List<Genome> initGenomes(int popSize){
        // create popSize genomes based on geneList
        List<Genome> genomes = new ArrayList<>();
        for (int i = 0; i < popSize; i++) {
            genomes.add(new Genome(geneUniverse));
        }
        return genomes;
    }

    public void popTest(){
        int popSize = 10;
        Population pop = new Population(popSize, 2, 1);
        // building an initial Population will randomize genes
        List<Genome> parents = initGenomes(popSize);
        dummyFitness(parents);

        // let this go on for some generations
        for (int i=0; i<3; i++) {
            List<Genome> children = pop.evolve(parents);
            System.out.println(String.format("\n----\nGeneration %d", i));
            System.out.println("Parents");
            Population.sortGenomes(parents);
            show(parents);
            System.out.println("Children");
            Population.sortGenomes(children);
            show(children);
            System.out.println("Elites");
            show(pop.getElites());

            parents = new ArrayList<>(children);

        }
        // collect new genomes
    }

    private void dummyFitness(List<Genome> genomes) {
        for (Genome genome: genomes) {
            dummyFitness(genome);
        }
    }

    private void dummyFitness(Genome genome) {
        // apply a dummy fitness score for testing.
        float fitness = genome.getGene("1").getValue()
                * genome.getGene("2").getValue() /
                genome.getGene("3").getValue();
        genome.setFitness(fitness);
    }


    public static void genomeDemo(String[] args) {
        EvolutionTest et = new EvolutionTest();
        Genome mom = et.createGenome();
        Genome dad = et.createGenome();

        System.out.println("Mom and dad");
        et.show(mom);
        et.show(dad);
        System.out.println("Breed with xoverrate 0.5");
        et.breed(mom, dad);
        System.out.println("Check mom and dad again");
        et.show(mom);
        et.show(dad);
        System.out.println("Clone and mutate a child");
        Genome child = et.mutate(mom);
        et.show(child);
    }

    public static void popDemo(String[] args) {
        EvolutionTest et = new EvolutionTest();
        et.popTest();
    }

    public static void main(String[] args) {
        popDemo(args);
    }

}