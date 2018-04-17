package genes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.yaml.snakeyaml.Yaml;

public class GeneUniverse {
    /**
     * A universe of genes. Add more when needed.
     */

    HashMap<String, List> geneTypes;

    public GeneUniverse(final String yaml_name) {
        Yaml yaml = new Yaml();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(yaml_name);
        geneTypes = (HashMap<String, List>) yaml.load(stream);
    }

    public HashMap<String, Gene> getGeneHashMap() {
        HashMap<String, Gene> geneHashMap = new HashMap<>();

        List<HashMap<String, Object>> geneList = (ArrayList<HashMap<String, Object>> ) geneTypes.get("genes");
        for (HashMap<String, Object> geneMap: geneList) {
            System.out.println(geneMap);
            Float step = Float.parseFloat((String) geneMap.get("step"));
            System.out.println(geneMap.get("step"));
            //Gene gene = parseGene(geneMap);
            //geneHashMap.put(gene.getName(), gene);
        }

        return geneHashMap;
    }

    private Gene parseGene(HashMap<String, String> item) {
        Gene gene = null;
        System.out.println(item);
        System.out.println(item.get("step").toString());

         gene = new FloatGene(item.get("name"),
                Float.parseFloat(item.get("step")),
                Float.parseFloat(item.get("max")),
                Float.parseFloat(item.get("min")),
                 item.get("type"));

        return gene;
    }


}
