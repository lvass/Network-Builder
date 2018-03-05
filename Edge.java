

//Author: Lucy Vass
public class Edge {
	Node from; //declaring the instance variables
	Node to;
	
	public Edge(Node a, Node b) { //constructor
		this.from = a; //Initialising instance variables
		this.to = b;
		if (a.equals(b)== true) { //looking for self interaction
			System.out.print("equal");
			Node.editDegree(a, 1); //Adding to node only once
		}
		else {
			Node.editDegree(a, 1); //Adding to the degrees of both nodes
			Node.editDegree(b, 1);
		}
	}

	
	public static void printEdge(Edge x) { //method to print edge description
		System.out.print("Edge connecting ");
		Node.printNode(x.from);
		System.out.print(" and ");
		Node.printNode(x.to);
	}
	public static void main (String[] args){ 
		//empty
	}
}
