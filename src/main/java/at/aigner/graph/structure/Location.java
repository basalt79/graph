package at.aigner.graph.structure;

public class Location {

    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public Location setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public Location setPort(Integer port) {
        this.port = port;
        return this;
    }

}
