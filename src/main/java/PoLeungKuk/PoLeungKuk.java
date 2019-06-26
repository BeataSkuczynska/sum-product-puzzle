package PoLeungKuk;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;

public class PoLeungKuk {
    static int action = 0;

    public static void main(String[] args) {
        /*Initialize Kripke model*/
        KripkeModel km = Init();
        System.out.println("Press 'a' to open advanced display");

        // Use Kripke model to create a new graph
        GraphStream graph = new GraphStream(km.getStatesList(), km.getRelationsX(), km.getRelationsY(), km.getRelationsZ());
        Graph initGraph = graph.getGraph();

        // Create a new JFrame in which we add the graph
        JFrame myJFrame = new JFrame();
        Viewer viewer = new Viewer(initGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        myJFrame.setPreferredSize(new Dimension(800, 900));
        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(600, 800));
        myJFrame.setLayout(new FlowLayout());
        myJFrame.setTitle("Po Leung Kuk Riddle");
        myJFrame.add(view, FlowLayout.LEFT);

        // Create JPanel within the JFrame on the right hand side
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add JPanel to the bottom of the JFrame
        JPanel bottomText = new JPanel(new FlowLayout((FlowLayout.LEFT)));

        // Add buttons to JFrame
        JButton nextButton = new JButton("Next announcement");

        // Add labels to JFrame
        JLabel numberStates = new JLabel("Number of states: " + Integer.toString(km.getStatesList().size()));
        JLabel announcement = new JLabel("");
        JLabel legendX = new JLabel("Xavier: Blue");
        JLabel legendY = new JLabel("Yvo: Red");
        JLabel legendZ = new JLabel("Zeno: Green");


        // Add button listener to open advanced graph
        myJFrame.setFocusable(true);
        myJFrame.requestFocusInWindow();
        myJFrame.addKeyListener( new KeyListener() {

            @Override
            public void keyTyped( KeyEvent evt ) {
            }

            @Override
            public void keyPressed( KeyEvent evt ) {
                int onlyOnce = 0;
                if(evt.getKeyChar() == 'a' && onlyOnce == 0) {
                    graph.graph.display();
                    onlyOnce += 1;
                }
            }

            @Override
            public void keyReleased( KeyEvent evt ) {
            }
        } );


        nextButton.addActionListener(new ActionListener() {
            // If next button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if (action == 0) {
                    // Print the first announcement, update graph and kripke model
                    int agentThatKnows = 0; /* Xeno */
                    knowYZdifferent(agentThatKnows, km.getStatesList(), km);
                    graph.updateGraph(km.getStatesList(), km.getRelationsX(), km.getRelationsY(), km.getRelationsZ());
                    numberStates.setText("Number of states: " + Integer.toString(km.getStatesList().size()));
                    announcement.setText("Xavier announces 'I know that Yvo and Zeno have different numbers'.");
                    action += 1;
                } else if (action == 1) {
                    // Print the second announcement, update graph and kripke model
                    int agentThatKnows = 1; /* Yvo */
                    knowXYZdifferent(agentThatKnows, km.getStatesList(), km);
                    graph.updateGraph(km.getStatesList(), km.getRelationsX(), km.getRelationsY(), km.getRelationsZ());
                    numberStates.setText("Number of states: " + Integer.toString(km.getStatesList().size()));
                    announcement.setText("Yvo announces 'I already knew that all our numbers are different'.");

                    action += 1;
                } else if (action == 2) {
                    // Print the third announcement, update graph and kripke model
                    int agentThatKnows = 2; /* Zeno */
                    knows(agentThatKnows, km.getStatesList(), km);
                    graph.updateGraph(km.getStatesList(), km.getRelationsX(), km.getRelationsY(), km.getRelationsZ());
                    numberStates.setText("Number of states: " + Integer.toString(km.getStatesList().size()));
                    announcement.setText("Zeno announces 'Aha. Now I know all three numbers'.");

                    action += 1;
                } else if (action == 3) {
                    // Do nothing
                }
            }
        });

        // Add buttons and text to JFrame and JPanels
        panel.add(numberStates, BorderLayout.EAST);
        panel.add(nextButton, BorderLayout.EAST);
        panel.add(legendX, BorderLayout.EAST);
        panel.add(legendY, BorderLayout.EAST);
        panel.add(legendZ, BorderLayout.EAST);
        bottomText.add(announcement, BorderLayout.SOUTH);
        myJFrame.getContentPane().add(panel, BorderLayout.NORTH);
        myJFrame.getContentPane().add(bottomText, BorderLayout.SOUTH);

        // Show JFrame
        myJFrame.pack();
        myJFrame.setVisible(true);
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewer.enableAutoLayout();
    }


    private static KripkeModel Init() {
        // Initalize KripkeModel
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the upper bound for the sum of numbers: ");
        int length = reader.nextInt();
        reader.close();
        System.out.println("");
        ArrayList<State> states = new ArrayList<State>();

        /**Initialize states**/
        for(int x=1; x<=length-2; x++) {
            for(int y=1; y<=length-x-1; y++) {
                int z = length - x - y;
                states.add(new State(x,y,z));
            }
        }

        ArrayList<Relation> relationsX = new ArrayList<Relation>();
        ArrayList<Relation> relationsY = new ArrayList<Relation>();
        ArrayList<Relation> relationsZ = new ArrayList<Relation>();

        /**Initialize relations**/
        states.forEach(state1 -> {
            states.subList(states.indexOf(state1)+1,states.size()).forEach(state2 -> {
                if(state1.getNumX() == state2.getNumX()){
                    relationsX.add(new Relation(state1, state2));
                }
                if(state1.getNumY() == state2.getNumY()){
                    relationsY.add(new Relation(state1, state2));
                }
                if(state1.getNumZ() == state2.getNumZ()){
                    relationsZ.add(new Relation(state1, state2));
                }
            });
        });
        System.out.println("Number of states: " + states.size() + "\n");
        KripkeModel km = new KripkeModel(states, relationsX, relationsY, relationsZ);

        return km;
    }

    private static void knows(int agentThatKnows, ArrayList<State> states, KripkeModel model) {
        ArrayList<Formula> annFormula = new ArrayList<Formula>();

        states.forEach(state -> {
            Propositional prop = new Propositional(state);
            Formula knows = new Knowledge(agentThatKnows, prop);
            Formula f = new Implies(prop,knows);
            annFormula.add(f);
        });

        Formula publicAnnouncement = new And(annFormula);
        ArrayList<State> toDelete = model.pAnnouncement(publicAnnouncement);
        toDelete.forEach(model::removeState);

    }

    private static void knowXYZdifferent(int agentThatKnows, ArrayList<State> states, KripkeModel model) {
        ArrayList<Formula> annFormula = new ArrayList<Formula>();

        states.forEach(state -> {
            Propositional prop = new Propositional(state);
            Formula YZdiff = new Difference(agentThatKnows, prop);
            Formula knows = new Knowledge(agentThatKnows, YZdiff);
            Formula f = new Implies(prop,knows);
            annFormula.add(f);
        });

        Formula publicAnnouncement = new And(annFormula);
        ArrayList<State> toDelete = model.pAnnouncement(publicAnnouncement);
        model.compareAndRemove(toDelete);
    }

    public static void knowYZdifferent(int agentThatKnows, ArrayList<State> states, KripkeModel model){
        ArrayList<Formula> annFormula = new ArrayList<Formula>();

        states.forEach(state -> {
            Propositional prop = new Propositional(state);
            Formula YZdiff = new Difference(agentThatKnows, prop);
            Formula knows = new Knowledge(agentThatKnows, YZdiff);
            Formula f = new Implies(prop,knows);
            annFormula.add(f);
        });

        Formula publicAnnouncement = new And(annFormula);
        ArrayList<State> toDelete = model.pAnnouncement(publicAnnouncement);
        toDelete.forEach(model::removeFromCurrentStates);
    }
}
