package at.aigner.graph.jgrapht;

import at.aigner.graph.structure.Node;
import at.aigner.graph.structure.Relationship;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;

import java.io.StringWriter;
import java.util.HashMap;

import static at.aigner.graph.Nodes.*;

public class JGraphT {

  private static final String DEPENDS_ON = "DEPENDS_ON";

  public static void run() {
    var graph = new DefaultDirectedGraph<Node, Relationship>(Relationship.class);

    // add the vertices
    graph.addVertex(NODE_A);
    graph.addVertex(NODE_B.setMissing(true));
    graph.addVertex(NODE_C);
    graph.addVertex(NODE_D);
    graph.addVertex(NODE_E);
    graph.addVertex(NODE_F);
    graph.addVertex(NODE_G);
    graph.addVertex(NODE_H);
    graph.addVertex(NODE_I);
    graph.addVertex(NODE_J);
    graph.addVertex(NODE_K);
    graph.addVertex(NODE_L);
    graph.addVertex(NODE_M);
    graph.addVertex(NODE_N);
    graph.addVertex(NODE_O);
    graph.addVertex(NODE_P);
    graph.addVertex(NODE_Q);
    graph.addVertex(NODE_R);
    graph.addVertex(NODE_S);
    graph.addVertex(NODE_T);
    graph.addVertex(NODE_U);
    graph.addVertex(NODE_V);
    graph.addVertex(NODE_W);
    graph.addVertex(NODE_X);
    graph.addVertex(NODE_Y);
    graph.addVertex(NODE_Z);
    graph.addVertex(NATS_A);
    graph.addVertex(DATABASE_A);

    // add edges to create linking structure
    graph.addEdge(NODE_A, NODE_B, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, DATABASE_A, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_C, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_B, NODE_D, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_B, NODE_E, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_B, NODE_F, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_B, NODE_G, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_B, NODE_H, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_B, NODE_I, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_J, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_K, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_L, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_M, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_N, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_O, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_P, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_A, NODE_Q, new Relationship().setName(DEPENDS_ON));

    graph.addEdge(NODE_E, NODE_R, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_S, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_T, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_U, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_V, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_W, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_X, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_Y, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NODE_E, NODE_Z, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NATS_A, NODE_D, new Relationship().setName(DEPENDS_ON));
    graph.addEdge(NATS_A, NODE_B, new Relationship().setName(DEPENDS_ON));

    exportGraph(graph);
  }

  private static void exportGraph(Graph<Node, Relationship> graph) {
    var exporter = new CustomJSONExporter<Node, Relationship>(Node::getName);
    exporter.setVertexAttributeProvider(node -> {
      var attr = new HashMap<String, Attribute>();
      attr.put("name", DefaultAttribute.createAttribute(node.getName()));
      attr.put("type", DefaultAttribute.createAttribute(node.getType().name()));
      attr.put("missing", DefaultAttribute.createAttribute(node.isMissing()));
      attr.put("instances", new DefaultAttribute<>(node.getInstances(), AttributeType.IDENTIFIER));
      return attr;
    });

    var writer = new StringWriter();
    exporter.exportGraph(graph, writer);
    var gson = new GsonBuilder().setPrettyPrinting().create();
    JsonElement jsonElement = new JsonParser().parse(writer.toString());
    System.out.println(gson.toJson(jsonElement));
  }

}
