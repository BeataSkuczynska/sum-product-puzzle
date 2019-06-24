package PoLeungKuk; /**
 * Created by joppeboekestijn on 11/06/2019.
 */

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.Iterator;


public class GraphStream {
    public static void main(String args[]) {
        new GraphStream();
    }

    public GraphStream() {
        Graph graph = new SingleGraph("GraphStream Example");

        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        graph.addNode("A0" );
        graph.addNode("B0" );
        graph.addNode("C0" );
        graph.addNode("D0" );
        graph.addNode("A1" );
        graph.addNode("B1" );
        graph.addNode("C1" );
        graph.addNode("D1" );
        graph.addNode("A2" );
        graph.addNode("B2" );
        graph.addNode("C2" );
        graph.addNode("D2" );
        graph.addNode("A3" );
        graph.addNode("B3" );
        graph.addNode("C3" );
        graph.addNode("D3" );
        graph.addNode("A4" );
        graph.addNode("B4" );
        graph.addNode("C4" );
        graph.addNode("D4" );
        graph.addNode("A5" );
        graph.addNode("B5" );
        graph.addNode("Csdfsdfsdfsdfsdfsdfsdfsdf5" );
        graph.addNode("D5" );
        graph.addNode("A6" );
        graph.addNode("B6" );
        graph.addNode("C6" );
        graph.addNode("D6" );
        graph.addNode("A7" );
        graph.addNode("B7" );
        graph.addNode("C7" );
        graph.addNode("D7" );

        graph.addEdge("A1B1", "A1", "B1");
        graph.addEdge("B1C1", "B1", "C1");
        graph.addEdge("C2A1", "C2", "A1");
        graph.addEdge("D2C1", "D2", "C1");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("DC", "D", "C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("DC", "D", "C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("DC", "D", "C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("DC", "D", "C");

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

//        graph.display();
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    protected String styleSheet =
        "node {" +
                "	fill-color: red;" +
                "}" +
                "node.marked {" +
                "	fill-color: red;" +
                "}" +
        "node#A {" +
                "shadow-mode: gradient-horizontal;" +
                "shadow-color: blue;" +
                "}";

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }
}

