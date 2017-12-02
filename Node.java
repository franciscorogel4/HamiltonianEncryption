import java.security.SecureRandom;
import java.util.ArrayList;

public class Node implements Comparable
{
    // instance variables - replace the example below with your own
    private ArrayList<Node> outgoingNodes;
    public int id;
    private int shift;
	public static SecureRandom randGenerator;

    /**
     * Constructor for objects of class Node
     */
    public Node(int newId, boolean isLast)
    {
        // initialize instance variables
        id=newId;
        outgoingNodes=new ArrayList<Node>();
        //shift=random number 0-25
        randGenerator=new SecureRandom();
        shift=randGenerator.nextInt(26); //length of accepted alphabet

        //will add initial edge from main class after next node is created (unless isLast=false)
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  n   a new Node to connect to
     */
    public void addEdge(Node n)
    {
        // put your code here
        outgoingNodes.add(n);
    }


    /**
     * An example of a method - replace this comment with your own
     * 
     * @return id  the ID of this node
     */
    public int getId(){
        return id;
    }
    
    public String toString() {
    	String s = "";
    	s += id+": "+shift;
    	return s;
    }

	@Override
	public int compareTo(Object arg0) {
		int compareId = ((Node)arg0).getId();
		return this.id - compareId;
		
	}
    
    /*public int compareTo(Node n){
    	return this.id-n.id;
    }*/
}