

//Author Lucy Vass
class Node {
	String name; //declaring the instance variables
	int degree;
	public Node() { //default constructor
		this.name = "Empty"; //Initialising instance variables
		this.degree = 0;
	}

	public Node(String gene) { //constructor
		this.name = gene; //Initialising instance variables
		this.degree = 0;
	}
	public static void printNode(Node x) { //method to print node name
		System.out.println(x.name);
	}
	public static String getNodeName(Node x) { //method to print node name
		return x.name;
	}
	public static void printNodeDegree(Node x) { //method to print node degree
		System.out.println(x.degree);
	}
	public static void editDegree(Node x, int change) { // method to edit degree
		x.degree = x.degree + change;
	}

	@Override //overriding equals method to prevent duplicate nodes being added to the LinkedHashSet containing the networks nodes
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		final Node other = (Node) obj;
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
		        return false;
		    }
		if (this.degree != other.degree) {
	        return false;
		}
		return true;
	}
	
	@Override //overriding the hashCode method so it matches the equals method and gives a unique hash code based on the name of the node
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
		return hash;
	}
	
	public static void main (String[] args){ //main program
		//empty
	}

}
