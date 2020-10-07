package at.aigner.graph.structure;

import org.jgrapht.graph.DefaultEdge;

public class Relationship extends DefaultEdge {

    private String name;


    public String getName() {
        return name;
    }

    public Relationship setName(String name) {
        this.name = name;
        return this;
    }

}
