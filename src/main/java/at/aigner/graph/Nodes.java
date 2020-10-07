package at.aigner.graph;

import at.aigner.graph.structure.*;

public class Nodes {

  public static final Node NODE_A = createNode("A", 1);
  public static final Node NODE_B = createNode("B", 1).setMissing(true);
  public static final Node NODE_C = createNode("C", 2);
  public static final Node NODE_D = createNode("D", 4);
  public static final Node NODE_E = createNode("E", 4);
  public static final Node NODE_F = createNode("F", 4);
  public static final Node NODE_G = createNode("G", 4);
  public static final Node NODE_H = createNode("H", 4);
  public static final Node NODE_I = createNode("I", 4);
  public static final Node NODE_J = createNode("J", 4);
  public static final Node NODE_K = createNode("K", 4);
  public static final Node NODE_L = createNode("L", 4);
  public static final Node NODE_M = createNode("M", 4);
  public static final Node NODE_N = createNode("N", 4);
  public static final Node NODE_O = createNode("O", 4);
  public static final Node NODE_P = createNode("P", 4);
  public static final Node NODE_Q = createNode("Q", 4);
  public static final Node NODE_R = createNode("R", 4);
  public static final Node NODE_S = createNode("S", 4);
  public static final Node NODE_T = createNode("T", 4);
  public static final Node NODE_U = createNode("U", 4);
  public static final Node NODE_V = createNode("V", 4);
  public static final Node NODE_W = createNode("W", 4);
  public static final Node NODE_X = createNode("X", 4);
  public static final Node NODE_Y = createNode("Y", 4);
  public static final Node NODE_Z = createNode("Z", 4);
  public static final Node DATABASE_A = createNode("DB_A", 0).setType(Type.DATABASE);
  public static final Node NATS_A = createNode("natsA", 0).setType(Type.NATS);

  private static Node createNode(String name, int instanceCount) {
    var node = new Node()
      .setName(name)
      .setType(Type.SERVICE_CLIENT)
      .setStatus(Status.UP)
      .setMissing(false);

    for (int i = 0; i < instanceCount; i++) {
      node.getInstances().add(new Instance()
        .setLocation(new Location().setHost("localhost").setPort(10000 + i))
        .setVersion(new Version()
          .setBuildVersion("1.2-SNAPSHOT")
          .setBuildTime("24.09.2020 @ 14:14:19 UTC")
          .setCommitId("2ee43ae")
          .setRepositoryUrl("https://github.com/jgrapht/jgrapht.git")
        )
      );
    }
    return node;
  }

}
