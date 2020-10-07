package at.aigner.graph.structure;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String name;
    private Type type;
    private boolean missing;
    private Status status;
    private List<Instance> instances = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Node setType(Type type) {
        this.type = type;
        return this;
    }

    public boolean isMissing() {
        return missing;
    }

    public Node setMissing(boolean missing) {
        this.missing = missing;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Node setStatus(Status status) {
        this.status = status;
        return this;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public Node setInstances(List<Instance> instances) {
        this.instances = instances;
        return this;
    }

}
