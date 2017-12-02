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
		String inputString = "HamiltonianEncryptionIsCoolQuestionMark"; //Will delete 
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
		}
		
		//sort nodes to be random
		Collections.sort(nodes);

		//NOW DO TESTING/OUTPUT
		
		System.out.println(nodes);
		System.out.println(actualOrder);
		System.out.println(nodes==actualOrder);
		System.out.print("Testing Encrypt: ");
		//System.out.println(encrypt(inputString));
		System.out.println(encrypt(inputString.toLowerCase()));
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
			encryptString[i] = alphabet[(i+shift)%alphabet.length];
			shiftArray[i] = shift;
		}

		//System.out.println(encryptString);
		return (new String(encryptString));
	}
	
	public static void decrypt(String inputString){
		
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