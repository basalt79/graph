/*
 * (C) Copyright 2019-2020, Dimitrios Michail and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */
package at.aigner.graph.jgrapht;

import org.apache.commons.text.StringEscapeUtils;
import org.jgrapht.Graph;
import org.jgrapht.nio.*;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * Can be replaced, once the version 1.5.1 arrived with IDENTITY Attribute
 * @param <V>
 * @param <E>
 */
public class CustomJSONExporter<V, E> extends BaseExporter<V, E> implements GraphExporter<V, E> {
  private static final String CREATOR = "Aigner Custom JSON Exporter";
  private static final String VERSION = "1";

  public CustomJSONExporter() {
    this(new IntegerIdProvider<>());
  }

  public CustomJSONExporter(Function<V, String> vertexIdProvider) {
    super(vertexIdProvider);
  }

  @Override
  public void exportGraph(Graph<V, E> g, Writer writer) {
    PrintWriter out = new PrintWriter(writer);
    out.print('{');

    /*
     * Version
     */
    out.print(quoted("creator"));
    out.print(':');
    out.print(quoted(CREATOR));

    out.print(',');
    out.print(quoted("version"));
    out.print(':');
    out.print(quoted(VERSION));

    /*
     * Vertices
     */
    out.print(',');
    out.print(quoted("nodes"));
    out.print(':');
    out.print('[');
    boolean printComma = false;
    for (V v : g.vertexSet()) {
      if (!printComma) {
        printComma = true;
      } else {
        out.print(',');
      }
      exportVertex(out, g, v);
    }
    out.print("]");

    /*
     * Edges
     */
    out.print(',');
    out.print(quoted("edges"));
    out.print(':');
    out.print('[');
    printComma = false;
    for (E e : g.edgeSet()) {
      if (!printComma) {
        printComma = true;
      } else {
        out.print(',');
      }
      exportEdge(out, g, e);
    }
    out.print("]");

    out.print('}');

    out.flush();
  }

  private void exportVertex(PrintWriter out, Graph<V, E> g, V v) {
    String vertexId = vertexIdProvider.apply(v);

    out.print('{');
    out.print(quoted("id"));
    out.print(':');
    out.print(quoted(vertexId));
    exportVertexAttributes(out, g, v);
    out.print('}');
  }

  private void exportEdge(PrintWriter out, Graph<V, E> g, E e) {
    V source = g.getEdgeSource(e);
    String sourceId = vertexIdProvider.apply(source);
    V target = g.getEdgeTarget(e);
    String targetId = vertexIdProvider.apply(target);

    out.print('{');

    edgeIdProvider.ifPresent(p -> {
      String edgeId = p.apply(e);
      if (edgeId != null) {
        out.print(quoted("id"));
        out.print(':');
        out.print(quoted(edgeId));
        out.print(',');
      }
    });

    out.print(quoted("source"));
    out.print(':');
    out.print(quoted(sourceId));
    out.print(',');
    out.print(quoted("target"));
    out.print(':');
    out.print(quoted(targetId));

    exportEdgeAttributes(out, g, e);

    out.print('}');
  }

  private void exportVertexAttributes(PrintWriter out, Graph<V, E> g, V v) {
    if (vertexAttributeProvider.isEmpty()) {
      return;
    }
    vertexAttributeProvider
      .get().apply(v).entrySet().stream().filter(e -> !e.getKey().equals("id"))
      .forEach(entry -> {
        out.print(",");
        out.print(quoted(entry.getKey()));
        out.print(":");
        outputValue(out, entry.getValue());
      });
  }

  private void exportEdgeAttributes(PrintWriter out, Graph<V, E> g, E e) {
    if (edgeAttributeProvider.isEmpty()) {
      return;
    }
    Set<String> forbidden = new HashSet<>(Arrays.asList("id", "source", "target"));
    edgeAttributeProvider
      .get().apply(e).entrySet().stream().filter(entry -> !forbidden.contains(entry.getKey()))
      .forEach(entry -> {
        out.print(",");
        out.print(quoted(entry.getKey()));
        out.print(":");
        outputValue(out, entry.getValue());
      });
  }

  private void outputValue(PrintWriter out, Attribute value) {
    AttributeType type = value.getType();
    switch (type) {
      case BOOLEAN -> out.print(Boolean.parseBoolean(value.getValue()) ? "true" : "false");
      case INT -> out.print(Integer.parseInt(value.getValue()));
      case LONG -> out.print(Long.parseLong(value.getValue()));
      case FLOAT -> {
        float floatValue = Float.parseFloat(value.getValue());
        if (!Float.isFinite(floatValue)) {
          throw new IllegalArgumentException("Infinity and NaN not allowed in JSON");
        }
        out.print(floatValue);
      }
      case DOUBLE -> {
        double doubleValue = Double.parseDouble(value.getValue());
        if (!Double.isFinite(doubleValue)) {
          throw new IllegalArgumentException("Infinity and NaN not allowed in JSON");
        }
        out.print(doubleValue);
      }
      case IDENTIFIER -> out.print(value.toString());
      default -> out.print(quoted(value.toString()));
    }
  }

  private String quoted(final String s) {
    return "\"" + StringEscapeUtils.escapeJson(s) + "\"";
  }

}
