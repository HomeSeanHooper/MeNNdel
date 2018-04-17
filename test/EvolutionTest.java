import genes.FloatGene;
import genes.Gene;
import genome.Genome;
import genes.GeneUniverse;

import java.util.Arrays;
import java.util.List;


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
        System.out.println(genome.getCommands());
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


    public void popTest(){
        Population pop = new Population(10);
        // building an initial Population will randomize genes
        pop.initGenomes(geneUniverse);

        pop.randomFitness();
        pop.evolve();
        System.out.println(pop.showGenomes());
        System.out.println(pop.showTasks());
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