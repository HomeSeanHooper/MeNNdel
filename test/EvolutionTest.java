import genome.Genome;
import genes.GeneUniverse;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;


class EvolutionTest {

    GeneUniverse geneList;

    public EvolutionTest() {
        Yaml yaml = new Yaml();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("ex1.yaml");
        geneList = yaml.loadAs( stream, GeneUniverse.class );
    }


    public Genome createGenome() {
        return new Genome(geneList.getGeneList());
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
        demo(args);
    }

}