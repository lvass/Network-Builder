


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;
 
//Author: Lucy Vass

public class Network {
	LinkedHashSet<Node> nodes; //declaring the instance variables
	LinkedHashSet<Edge> edges;
	ArrayList<String> dupList;
	String networkName;
	
	public Network() { //no-argument constructor, makes a completely empty network so users can build from scratch
		this.nodes = new LinkedHashSet<Node>(); //Initialising instance variables
		this.edges = new LinkedHashSet<Edge>();
		this.dupList = new ArrayList<String>();
		this.networkName = "custom_network";
	}
	
	public Network(String file) throws IOException {  //constructor, makes a custom network from a supplied text file of interactions
		this.nodes = new LinkedHashSet<Node>(); //Initialising instance variables
		this.edges = new LinkedHashSet<Edge>();
		this.dupList = new ArrayList<String>();
		String name = file.replace(".txt", "");
		this.networkName = name;
		ArrayList<String> edgeList = new ArrayList<String>(); //Creating a list to help add edges
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(file))) {
				String line = null;
				int counter = 0;
				while((line = reader.readLine()) != null) {
					int tvsFile = line.indexOf("\t"); //reads tab separated variables only, checks there is a tab in every line
					if (tvsFile == -1){
						JOptionPane.showMessageDialog(null, "Please use a file where all the node names are seperated by tabs and there is only 1 interaction on each line", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					String[] elements = line.split("\t"); 
					this.nodes.add(new Node(elements[0])); //creating nodes, the HashSet prevents duplicate additions
					this.nodes.add(new Node(elements[1]));
					edgeList.add(elements[0]);
					edgeList.add(elements[1]);
					counter = counter + 2;
				  }
			reader.close();
			}
		for (int x = 0; x < edgeList.size()-1; x= x+2){
			Boolean duplicate = false;
			String a = edgeList.get(x);
			String b = edgeList.get(x+1);
			String forward = a + b;
			String backward = b + a;
			this.dupList.add("Empty");
			for (int i = 0; i < this.dupList.size(); i++){ //checking for reverse duplicated edges - eg. A-B and B-A
				if (this.dupList.get(i).equals(forward) || this.dupList.get(i).equals(backward)){
					duplicate = true;
				}
			}
			if (duplicate == false){
				this.edges.add(new Edge((accessNode(this, a)), (accessNode(this, b)))); //creating a unique edge
				this.dupList.add(forward); //adding to duplicate list
			}
		}
	}
	public static Node findNode(Set set, int element){ //method for retrieving nodes from the LinkedHashSet, for internal use
        Iterator itr = set.iterator();
        int counter = 0;
        Node x = null;
        while(itr.hasNext()){
            if (counter == element){
            	x = (Node) itr.next();
            	break;
            	}
            itr.next();
            counter++;
        }
        return x;
    }
	
	public static Edge findEdge(Set set, int element){ //method for retrieving edges from the LinkedHashSet, for internal use
        Iterator itr = set.iterator();
        int counter = 0;
        Edge x = null;
        while(itr.hasNext()){
            if (counter == element){
            	x = (Edge) itr.next();
            	break;
            	}
            itr.next();
            counter++;
        }
        return x;
    }
	
	public static Node accessNode(Network network, String nodeName){ //method for finding a node by its name,  for internal use
		int i = 0;
		Node myNode = null;
		while (i < network.nodes.size()){
			String myName = findNode(network.nodes, i).name;
			if (myName.compareTo(nodeName)==0){
				myNode = findNode(network.nodes, i);
				break;
			}
			else{
				i++;
			}
		}
		if (myNode == null){
			System.out.println("Can't find node");
		}
		return myNode;
	}
	
	public static void printNetwork(Network network) { //method for printing out the network - for development use
		System.out.print("Network Name: ");
		System.out.println(network.networkName);
		Iterator<Node> itr = network.nodes.iterator();
		Iterator<Edge> itr2 = network.edges.iterator();
        while(itr.hasNext()) {
        	Node a = (Node) itr.next();
        	Node.printNode(a);
		}
        while(itr2.hasNext()) {
			Edge.printEdge((Edge) itr2.next());
			}
        if (network.nodes.size() == 0){
        	System.out.println("No nodes or edges in this network");
        }
	}

	public static int nodeDegree(Network network, String nodeName){ //method for returning the degree of a node - for use in GUI
		int myDegree = -1;
		if (accessNode(network, nodeName) == null){
			return 0;
		}
		myDegree = accessNode(network, nodeName).degree;
		return myDegree;
	}
	public static double averageDegree(Network network){ //method for returning the average degree of the network - for use in GUI
		int i = 0;
		int counter = 0;
		int total = 0;
		while (i < network.nodes.size()){
			total = total + findNode(network.nodes, i).degree;
			counter++;
			i++;
		}
		double t = total;
		double c = counter;
		double average = t/c;
		return average;
	}
	public static String degreeDistrubution(Network network) throws IOException{ //method for returning a histogram of the degree distribution - for use in GUI
		String filename = network.networkName + "_degreedist.txt";
		PrintWriter outputFile = new PrintWriter(new FileWriter(filename, true)); //IOException is handled by the GUI using a pop-up
    	outputFile.printf("%1s \t %-1s%n", "Degree", "Frequency");
    	int l = network.nodes.size();
    	int[] degreeNumbers = new int[l];
		int[] frequency = new int[l];
    	for (int i = 0; i < l; i++){
    		degreeNumbers[i] = i+1;
    		frequency[i] = 0;
    	}
		int max = 0;
		for (int i = 0; i < l; i++){
			
			int myDegree = findNode(network.nodes, i).degree;
			frequency[myDegree] = frequency[myDegree]+1;
			if (frequency[myDegree] > max){
				max = frequency[myDegree];
			}
		}
		for (int i = 1; i < max+1; i++){
			if (findMaxNode(network).degree > i-1){
			outputFile.printf("%-3d \t %-3d%n", degreeNumbers[i-1], frequency[i]); //formatted as a TSV file
			}
		}
		outputFile.close();
		return ("Full degree distribution saved to " + filename);
		}
	
	public static String addInteraction(Network network, String node1, String node2){ //method for manually adding an interaction to a network from the GUI input
		 boolean duplicate = false;
		 String message = "";
		 String[] interactions = {node1, node2};
		 for (int i = 0; i < 2; i++){
			 Iterator<Node> itr = network.nodes.iterator();
			 while(itr.hasNext()) {
		         if (interactions[i].equals(((Node) itr.next()).name)){
		        	 duplicate = true;
		         }
			 }
			 if (duplicate == false){
				 network.nodes.add(new Node(interactions[i]));
			 }
			 duplicate = false;
		 }
		 
		 duplicate = false;
		 String forward = node1 + node2;
		 String backward = node1 + node2;
		 for (int i = 0; i < network.dupList.size(); i++){
					if (network.dupList.get(i).equals(forward) || network.dupList.get(i).equals(backward)){
						duplicate = true;
						message = "This interaction already exists";
					}
		 }
		 if (duplicate == false){
			network.edges.add(new Edge((accessNode(network, node1)), (accessNode(network, node2))));
			network.dupList.add(forward);
			message = ("Added interaction between " + node1 + " and " + node2);
		 }
		 return message;
		 
	}
	public static Node findMaxNode(Network network){ //method for finding the node with the maximum degree, for internal use
		int x = 0;
		Node hub = null;
		for (int i = 0; i < network.nodes.size(); i++){
			int myDegree = (findNode(network.nodes, i)).degree;
			if (myDegree > x){
				x = myDegree;
				hub = findNode(network.nodes, i);
			}
		}
		return hub;
		
	}
	public static String findHubs(Network network){ //method for finding the hubs, for GUI use
		Node hub = findMaxNode(network);
		String message = "Hubs of the network are: \n";
		ArrayList<Node> hubs = new ArrayList<Node>();
		hubs.add(hub);
		for (int i = 0; i < network.nodes.size(); i++){
			if (((findNode(network.nodes, i)).degree == hub.degree) && (findNode(network.nodes, i) != hub)){
				hubs.add((findNode(network.nodes, i)));
			}
		}
		for (int i = 0; i < hubs.size(); i++){
			message = message + ("\t" + (hubs.get(i).name)+ " with a degree of " + (hubs.get(0).degree) + "\n");
		}
		return message;
	}
	
	public static String saveNetwork(Network network) throws IOException{ // method for saving the built network as a list on interactions
		String filename = network.networkName + "_interactions.txt";
		String message = "";
		PrintWriter outputFile = new PrintWriter(new FileWriter(filename, true));
		Iterator<Edge> itr = network.edges.iterator();
        while(itr.hasNext()) {
        	Edge x = (Edge) itr.next();
        	Node a = x.from;
			Node b = x.to;
			outputFile.printf(a.name);
			outputFile.printf("\t");
			outputFile.printf(b.name);
			outputFile.printf("\n");
		}
        outputFile.close();
        if (network.edges.size() == 0){
        	message = "No interactions in this network";
        }
        message = "Network saved to " + filename;
        return message;
	}
	public static void main(String[] args) throws IOException {
		//Code below is for full testing outside of the GUI. Test file 'genes.txt' is included in .zip
		Network myNetwork = new Network("genes.txt");
		printNetwork(myNetwork);
		addInteraction(myNetwork, "X", "X");
		printNetwork(myNetwork);
		System.out.println(findHubs(myNetwork));
		System.out.println(saveNetwork(myNetwork));
		Node.printNode(findMaxNode(myNetwork));
		System.out.println(degreeDistrubution(myNetwork));
		System.out.println(averageDegree(myNetwork));
		System.out.println(accessNode(myNetwork, "A"));
		System.out.println(myNetwork.nodes.size());
		System.out.println(myNetwork.edges.size());
	}
}

	