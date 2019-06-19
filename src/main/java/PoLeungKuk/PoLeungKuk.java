package PoLeungKuk;

import java.util.ArrayList;
import java.util.Scanner;

public class PoLeungKuk {

    public static void main(String[] args) {
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
        System.out.println(states);
        System.out.println(states.size());
        System.out.println(relationsX);
        System.out.println(relationsY);
        System.out.println(relationsZ);


        /**Initialize Kripke models**/
        KripkeModel km = new KripkeModel(states, relationsX, relationsY, relationsZ);
        int agentThatKnows;

        agentThatKnows = 0; /** Xeno */
        knowYZdifferent(agentThatKnows, states, km);
        System.out.println("Xeno announces 'I know that Yvo and Zeno have different numbers'.");
        System.out.println("It is now common knowledge that those sets of numbers are possible:");
        System.out.println(km.getStatesList());
        System.out.println("");

        agentThatKnows = 1; /** Yvo */
        knowXYZdifferent(agentThatKnows, states, km);
        System.out.println("Yvo announces 'I already knew that all our numbers are different'.");
        System.out.println("It is now common knowledge that these pairs of numbers are possible:");
        System.out.println(km.getStatesList());
        System.out.println("");

        agentThatKnows = 2; /** Zeno */
        knows(agentThatKnows, states, km);
        System.out.println("Zeno announces 'Aha. Now I know all three numbers'.");
        System.out.println("It is now common knowledge that these pairs of numbers are possible:");
        System.out.println(km.getStatesList());
        System.out.println("");
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
