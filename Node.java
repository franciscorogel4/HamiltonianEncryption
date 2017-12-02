public class Node
{
    // instance variables - replace the example below with your own
    private ArrayList<Node> outgoingNodes;
    private int id;
    private int shift;

    /**
     * Constructor for objects of class MyClass
     */
    public Node(int newId, boolean isLast)
    {
        // initialise instance variables
        id=newId;
        outgoingNodes=new ArrayList<Node>();
        //shift=random number 0-25
        //will add initial edge from main class after next node is created (unless isLast=false)
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  n   a new Node to connect to
     */
    public void newEdge(Node n)
    {
        // put your code here
        outgoingNodes.add(n);
    }
}