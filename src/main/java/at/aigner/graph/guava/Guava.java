package at.aigner.graph.guava;

import at.aigner.graph.structure.Node;
import at.aigner.graph.structure.Relationship;
import com.google.common.graph.Graph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.Traverser;
import com.google.common.graph.ValueGraphBuilder;

import static at.aigner.graph.Nodes.*;

public class Guava {

  public static void run() {
    MutableValueGraph<Node, Relationship> graph = ValueGraphBuilder.directed().build();

    graph.addNode(NODE_A);
    graph.addNode(NODE_B.setMissing(true));
    graph.addNode(NODE_C);
    graph.addNode(NODE_D);
    graph.addNode(NODE_E);
    graph.addNode(NODE_F);
    graph.addNode(NODE_G);
    graph.addNode(NODE_H);
    graph.addNode(NODE_I);
    graph.addNode(NODE_J);
    graph.addNode(NODE_K);
    graph.addNode(NODE_L);
    graph.addNode(NODE_M);
    graph.addNode(NODE_N);
    graph.addNode(NODE_O);
    graph.addNode(NODE_P);
    graph.addNode(NODE_Q);
    graph.addNode(NODE_R);
    graph.addNode(NODE_S);
    graph.addNode(NODE_T);
    graph.addNode(NODE_U);
    graph.addNode(NODE_V);
    graph.addNode(NODE_W);
    graph.addNode(NODE_X);
    graph.addNode(NODE_Y);
    graph.addNode(NODE_Z);
    graph.addNode(NATS_A);
    graph.addNode(DATABASE_A);

    graph.putEdgeValue(NODE_A, NODE_B, new Relationship());
    graph.putEdgeValue(NODE_A, DATABASE_A, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_C, new Relationship());
    graph.putEdgeValue(NODE_B, NODE_D, new Relationship());
    graph.putEdgeValue(NODE_B, NODE_E, new Relationship());
    graph.putEdgeValue(NODE_B, NODE_F, new Relationship());
    graph.putEdgeValue(NODE_B, NODE_G, new Relationship());
    graph.putEdgeValue(NODE_B, NODE_H, new Relationship());
    graph.putEdgeValue(NODE_B, NODE_I, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_J, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_K, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_L, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_M, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_N, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_O, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_P, new Relationship());
    graph.putEdgeValue(NODE_A, NODE_Q, new Relationship());

    graph.putEdgeValue(NODE_E, NODE_R, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_S, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_T, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_U, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_V, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_W, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_X, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_Y, new Relationship());
    graph.putEdgeValue(NODE_E, NODE_Z, new Relationship());
    graph.putEdgeValue(NATS_A, NODE_D, new Relationship());
    graph.putEdgeValue(NATS_A, NODE_B, new Relationship());

    graph.incidentEdges(NODE_A).forEach(e -> {
      System.out.println(e.source().getName() + "->" + e.target().getName());
    });


    graph.successors(NODE_A).forEach(n -> {
      System.out.println("successors for NODE_A " + n.getName());
    });
    graph.successors(NODE_R).forEach(n -> {
      System.out.println("successors for NODE_R " + n.getName());
    });

    Traverser.forGraph(graph).depthFirstPostOrder(NODE_A).forEach(n -> {
      System.out.println(n.getName());
    });

  }
}
