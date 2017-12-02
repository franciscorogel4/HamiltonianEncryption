import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Encryptor {

	public static ArrayList<Integer> ids;
	public static ArrayList<Node> nodes;
	public static ArrayList<Node> actualOrder;
	public static SecureRandom randGenerator;

	public static void main(String[] args) {

		ids=new ArrayList<Integer>();
		nodes=new ArrayList<Node>();
		actualOrder=new ArrayList<Node>();

		randGenerator = new SecureRandom();
		Node lastNode;

		Node temp = new Node(uniqueRandomId(), false);
		lastNode=temp;
		nodes.add(temp);

		for (int i = 0; i < 98; i++){
			//create new ID and check if it's taken already

			temp = new Node(uniqueRandomId(), false);
			nodes.add(temp);
			lastNode.addEdge(temp);
			lastNode=temp;

		}

		temp = new Node(uniqueRandomId(), true);
		nodes.add(temp);
		
		Collections.sort(nodes);
		
		//insecurely save key and randomize order of other array
		actualOrder=nodes;
		
		

		//NOW DO TESTING/OUTPUT
		for(Node id: nodes)
		{
			System.out.println(id);
			
		}

	}

	public static int uniqueRandomId() {
		int newId;
		if(nodes.isEmpty()){
			newId= randGenerator.nextInt(10000);
		}else{
			newId= randGenerator.nextInt(10000);
			while(ids.contains((Integer) newId)){
				newId= randGenerator.nextInt(10000);
			}
		}
		return newId;
	}
}