import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Encryptor {

	public static void main(String[] args) {


		Random randGenerator = new Random();

		Node lastNode;

		ArrayList IDs = new ArrayList();

		for (int i = 0; i < 100; i++){
			Node temp = new Node(randGenerator.nextInt(10000000), false);
			lastNode = temp; 

		}

	}

}