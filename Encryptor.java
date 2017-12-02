import java.lang.reflect.Array;
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
		String inputString = "aaaaaaaaaaaaaaaaaaaaa"; //Will delete 
		int[] myKey = new int[100];
		//String inputString = "aaaabbb"; //For testing  

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

		//insecurely save key for proof of concept and then randomize order of other array
		//actualOrder=nodes; //doesn't work because java
		
		for(int i=0;i<nodes.size();i++){
			actualOrder.add(nodes.get(i));
			//System.out.println(actualOrder.get(i).getShift());
			myKey[i] = actualOrder.get(i).getShift();
		}
		
		
		//sort nodes to be random
		Collections.sort(nodes);

		//NOW DO TESTING/OUTPUT
		
		//System.out.println(nodes);
		//System.out.println(actualOrder);
		//System.out.println(nodes==actualOrder);
		//System.out.println("Testing Encrypt: ");
		//System.out.println(encrypt(inputString));
		String encrypted = encrypt(inputString.toLowerCase());
		
		Node tempNode; 
	    for(int i = 0; i<100; i++)
		{
			tempNode = nodes.get(i);
			for(int j = 0; j < 20; j++)
				tempNode.addEdge(nodes.get(randGenerator.nextInt(100)));
			
		}
		System.out.println(inputString);
		System.out.println("Encrypt: " + encrypted);
		System.out.println("Decrypt: " + decrypt(actualOrder.get(0), myKey, encrypted));
	}
	
	public static String encrypt(String inputString){
		char[] encryptString = new char[100];
		
		int[] shiftArray = new int[100]; // Holding shift values
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		//System.out.println(inputString.length());
		
		int numRandLetters = (100-inputString.length()); // finding out how many random chars to add to array 
		//System.out.println(numRandLetters);
		
		char[] StringAndExtraSpace = new char[100];
		
		// Filling the array with shifts
		for(int j = 0; j < 100; j++){
			
			if (j <= (inputString.length()-1)){
				StringAndExtraSpace[j] = inputString.charAt(j);
			}
			else{
				StringAndExtraSpace[j] = alphabet[randGenerator.nextInt(alphabet.length)]; //length of accepted alphabet
			}
			//System.out.print(StringAndExtraSpace[j]);
		}
		//System.out.println();
		
		for(int i = 0; i < 100; i++){
			//System.out.println(new String(alphabet).indexOf(StringAndExtraSpace[i]));
			int shift = actualOrder.get(i).getShift();
			encryptString[i] = alphabet[(new String(alphabet).indexOf(StringAndExtraSpace[i])+shift)%alphabet.length];
			shiftArray[i] = shift;
		}

		//System.out.println(encryptString);
		return (new String(encryptString));
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
	
	public static String decrypt(Node start, int[] key, String toBeEncrypted){
		//storage for the iterator
		Node temp;
		temp = start;
		char[] encrypted = new char[100];
		
		// Filling the array with shifts
		for(int j = 0; j < 99; j++){
			encrypted[j] = toBeEncrypted.charAt(j);
		}
					//System.out.print(StringAndExtraSpace[j]);

		
		//for the entire key, get the next node and encrypt 
		for(int i = 0; i<100; i++)
		{
			//shift the ith element by the node's shift value 
			encrypted[i] = shift(encrypted[i], actualOrder.get(i).getShift());
			
			//temp = temp.nextNode(key[i]);
		}
		String b = new String(encrypted);
		return b;
	}
	
	public static char shift(char tobeShifted, int shiftNum)
	{
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		//System.out.println(shiftNum);
		int desiredIndex = (new String(alphabet)).indexOf(tobeShifted)-shiftNum;
		if(desiredIndex <0)
		    desiredIndex = 0;

		return alphabet[desiredIndex]; 
	}
}


	