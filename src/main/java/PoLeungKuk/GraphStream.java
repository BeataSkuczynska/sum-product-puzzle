package PoLeungKuk;
/**
 * Created by joppeboekestijn on 11/06/2019.
 **/

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.Iterator;


class GraphStream {
    // Create a new Graph
    final Graph graph = new SingleGraph("Po Leung Kuk");

    GraphStream(ArrayList<State> currentStates, ArrayList<Relation> relationsX, ArrayList<Relation> relationsY, ArrayList<Relation> relationsZ) {
        // Initialize the graph with the current states and relations
        // Stylesheet to change appearance of nodes
        String styleSheet = "node {" +
                "	fill-mode: none;" +
                "   size: 5px;" +
                "   text-size: 12px;" +
                "}";
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(false);
        graph.setStrict(false);


        // Iteratively add nodes to the graph
        for (State currentState : currentStates) {
            graph.addNode(currentState.toString());
        }

        // Iteratively add edges to the graph
        addEdges(relationsX, "x");
        addEdges(relationsY, "y");
        addEdges(relationsZ, "z");

        // Iteratively add label to the graph
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
    }

    void updateGraph(ArrayList<State> currentStates, ArrayList<Relation> relationsX, ArrayList<Relation> relationsY, ArrayList<Relation> relationsZ) {
        // Remove all nodes and edges
        while (graph.getEdgeCount() > 0) {
            graph.removeEdge(0);
        }

        while (graph.getNodeCount() > 0) {
            graph.removeNode(0);
        }

        // Add new states to the graph
        currentStates.forEach((state) -> graph.addNode(state.toString()));

        // Add all relations as edges to the graph
        addEdges(relationsX, "x");
        addEdges(relationsY, "y");
        addEdges(relationsZ, "z");

        // Add labels to the nodes
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
    }

    Graph getGraph() {
        return this.graph;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    private void addEdges(ArrayList<Relation> relations, String person) {
        // Go through all the relations
        for (Relation relation : relations) {
            try {
                // Get the first and second state which make up the relation
                String first = relation.getFirst().toString();
                String second = relation.getSecond().toString();

                String fillColor;
                if (person.equals("x")) { // Color becomes blue
                    fillColor = "fill-color: rgb(0,0,255);";
                } else if (person.equals("y")) { // Color becomes red
                    fillColor = "fill-color: rgb(255,0,0);";
                } else { // Else color is green
                    fillColor = "fill-color: rgb(0,255,0);";
                }
                // Add edge to the graph with correct color for correct person
                graph.addEdge(first + second, first, second).addAttribute("ui.style", fillColor);
            } catch (Exception e) {
                // Do nothing
            }
        }
    }

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }
}

