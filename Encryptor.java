import java.security.SecureRandom;
import java.util.ArrayList;

import java.security.SecureRandom;
import java.util.Random;

public class Encryptor {

	public ArrayList<int> ids;
	public ArrayList<Node> edges;

	public static void main(String[] args) {

		ids=new ArrayList<int>();
		edges=new ArrayList<Node>();

		Random randGenerator = new SecureRandom();
		Node lastNode;

		Node temp = new Node(uniqueRandomId(), false);
		lastNode=temp;
		edges.add(temp);

		for (int i = 0; i < 98; i++){
			//create new ID and check if it's taken already

			Node temp = new Node(uniqueRandomId(), false);
			edges.add(temp);
			lastNode.addEdge(temp);
			lastNode=temp;

		}

		Node temp = new Node(uniqueRandomId(), true);
		edges.add(temp);

		//NOW DO TESTING/OUTPUT
		System.out.println(edges);


	}

	public int uniqueRandomId(){
		newId=-1;
		while(!ids.contains(newId)){
			newId=randGenerator.nextInt(10000);
		}
		return newId;
	}

}