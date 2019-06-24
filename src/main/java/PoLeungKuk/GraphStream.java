package PoLeungKuk; /**
 * Created by joppeboekestijn on 11/06/2019.
 */

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.ArrayList;
import java.util.Iterator;


public class GraphStream {
    // Create a new Graph
    Graph graph = new SingleGraph("Po Leung Kuk");

    protected GraphStream(ArrayList<State> currentStates, ArrayList<Relation> relationsX, ArrayList<Relation> relationsY, ArrayList<Relation> relationsZ) {
        // Initialize the graph with the current states and relations
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(false);
        graph.setStrict(false);


        // Iteratively add nodes to the graph
        Iterator<State> stateIterator = currentStates.iterator();
        while (stateIterator.hasNext()) {
            graph.addNode(stateIterator.next().toString());
        }

        // Iteratively add edges to the graph
        addEdges(currentStates, relationsX, "x");
        addEdges(currentStates, relationsY, "y");
        addEdges(currentStates, relationsZ, "z");

        // Iteratively add label to the graph
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
    }

    public void updateGraph(ArrayList<State> currentStates, ArrayList<Relation> relationsX, ArrayList<Relation> relationsY, ArrayList<Relation> relationsZ) {
        // Remove all nodes and edges
        while(graph.getEdgeCount() > 0) {
            graph.removeEdge(0);
        }

        while(graph.getNodeCount() > 0) {
            graph.removeNode(0);
        }

        // Add new states to the graph
        currentStates.forEach((state) -> graph.addNode(state.toString()));

        // Add all relations as edges to the graph
        addEdges(currentStates, relationsX, "x");
        addEdges(currentStates, relationsY, "y");
        addEdges(currentStates, relationsZ, "z");

        // Add labels to the nodes
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
    }

    public Graph getGraph(){
        return this.graph;
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    // Stylesheet to change appearance of nodes
    protected String styleSheet =
        "node {" +
                "	fill-mode: none;" +
                "   size: 5px;" +
                "   text-size: 12px;" +
                "}";


    public void addEdges(ArrayList<State> currentStates, ArrayList<Relation> relations, String person) {
        // Go through all the relations
        Iterator<Relation> relationIterator = relations.iterator();
        while (relationIterator.hasNext()) {
            try {
                // Get the first and second state which make up the relation
                Relation tuple = relationIterator.next();
                String first = tuple.getFirst().toString();
                String second = tuple.getSecond().toString();

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
            } catch (Exception e){
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

