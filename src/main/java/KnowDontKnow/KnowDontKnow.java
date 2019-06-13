package KnowDontKnow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class KnowDontKnow {
	
	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the upper bound for the numbers: ");
		int length = reader.nextInt();
		reader.close();
		System.out.println("");
		
		ArrayList<State> states = new ArrayList<State>();
	
		/**Initialize states**/
		for(int i=1; i<=length; i++) {
			for(int j=1; j<=i; j++) {
				states.add(new State(i,j));
			}
		}
		
		ArrayList<Relation> relationsA = new ArrayList<Relation>();
		ArrayList<Relation> relationsB = new ArrayList<Relation>();
		
		Iterator<State> iter = states.iterator();
		
		/**Initialize relations**/
		while(iter.hasNext()) {
			State s1 = iter.next();
			Iterator<State> iter2 = states.iterator();
			
			while(iter2.hasNext()) {
				State s2 = iter2.next();
				if (s1.getSum() == s2.getSum()) {
					relationsA.add(new Relation(s1, s2));
				}
				if (s1.getSumSquares() == s2.getSumSquares()) {
					relationsB.add(new Relation(s1, s2));
				}
			}
			
		}
		
		/**Initialize Kripke model**/
		KripkeModel km = new KripkeModel(states, relationsA, relationsB);
		int agent;
		
		/** First public announcement is by B:
		 *  "I can't tell what the two numbers are"
		 *  Translated to Epistemic logic: ~K_B(x&y)
		 */
		
		agent = 1; /** B */
		dontKnow(agent, states, km);
		System.out.println("Agent B announces 'I can't tell what the two numbers are'.");
		System.out.println("It is now common knowledge that these pairs of numbers are possible:");
		System.out.println(states);
		System.out.println("");
		
		/** Second public announcement is by A:
		 *  "I can't tell what the two numbers are"
		 *  Translated to Epistemic logic: ~K_A(x&y) etc.
		 */
		
		agent = 0; /** A */
		dontKnow(agent, states, km);
		System.out.println("Agent A announces 'I can't tell what the two numbers are'.");
		System.out.println("It is now common knowledge that these pairs of numbers are possible:");
		System.out.println(states);
		System.out.println("");

		agent = 1; /** B */
		dontKnow(agent, states, km);
		System.out.println("Agent B announces 'I can't tell what the two numbers are'.");
		System.out.println("It is now common knowledge that these pairs of numbers are possible:");
		System.out.println(states);
		System.out.println("");

		agent = 0; /** A */
		dontKnow(agent, states, km);
		System.out.println("Agent A announces 'I can't tell what the two numbers are'.");
		System.out.println("It is now common knowledge that these pairs of numbers are possible:");
		System.out.println(states);
		System.out.println("");

		agent = 1; /** B */
		dontKnow(agent, states, km);
		System.out.println("Agent B announces 'I can't tell what the two numbers are'.");
		System.out.println("It is now common knowledge that these pairs of numbers are possible:");
		System.out.println(states);
		System.out.println("");

		agent = 0; /** A */
		dontKnow(agent, states, km);
		System.out.println("Agent A announces 'I can't tell what the two numbers are'.");
		System.out.println("It is now common knowledge that these pairs of numbers are possible:");
		System.out.println(states);
		System.out.println("");
		
		/** The final public announcement is by B:
		 *  "Now I can tell what the two numbers are"
		 *  Translated to Epistemic logic: K_B(x&y)
		 */
		
		agent = 1; /** B */
		know(agent, states, km);
		System.out.println("Agent B announces 'now I can tell what the two numbers are'.");
		System.out.println("It is now common knowledge that these pairs of numbers are possible:");
		System.out.println(states);
		System.out.println("");
		
		Iterator<State> result = states.iterator();
		
		if(result.hasNext()) {
			State trueState = result.next();
			int a = trueState.getNumA();
			int b = trueState.getNumB();
			System.out.print("The two numbers are (" + a + "," + b +")");
			
			while(result.hasNext()) {
				trueState = result.next();
				a = trueState.getNumA();
				b = trueState.getNumB();
				System.out.print(" or (" + a + "," + b + ")");
			}
		} else {
			System.out.println("There are no solutions.");
		}
	}
	
	public static void dontKnow(int agent, ArrayList<State> states, KripkeModel model) {
		Iterator<State> annIterator = states.iterator();
		ArrayList<Formula> annFormula = new ArrayList<Formula>();
		
		while(annIterator.hasNext()) {
			State s = annIterator.next();
			Formula ant = new Propositional(s);
			Formula con = new Not(new Knowledge(agent, ant));
			Formula f = new Implies(ant,con);
			annFormula.add(f);
		}
		
		Formula publicAnnouncement = new And(annFormula);
		model.pAnnouncement(publicAnnouncement);
	}
	
	public static void know(int agent, ArrayList<State> states, KripkeModel model) {
		Iterator<State> annIterator = states.iterator();
		ArrayList<Formula> annFormula = new ArrayList<Formula>();
		
		while(annIterator.hasNext()) {
			State s = annIterator.next();
			Formula ant = new Propositional(s);
			Formula con = new Knowledge(agent, ant);
			Formula f = new Implies(ant,con);
			annFormula.add(f);
		}
		
		Formula publicAnnouncement = new And(annFormula);
		model.pAnnouncement(publicAnnouncement);
	}
}
