import genome.Genome;
import genes.GeneUniverse;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.yaml.snakeyaml.Yaml;


class EvolutionTest {


    public Genome createGenome() {
        GeneUniverse geneUniverse = new GeneUniverse("experiment.yaml");
        return new Genome(geneUniverse.getGeneHashMap());
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

    public Genome testGeneUniverse() {
        GeneUniverse gu = new GeneUniverse("experiment.yaml");
        Genome test = new Genome(gu.getGeneHashMap());
        return test;
    }

    public static void demo(String[] args) {
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

    public static void main(String[] args) {
        EvolutionTest et = new EvolutionTest();
        Genome test = et.testGeneUniverse();
        et.show(test);
    }

}