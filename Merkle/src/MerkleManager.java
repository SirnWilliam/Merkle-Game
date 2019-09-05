
public class MerkleManager {
	
	public static volatile String userEnteredWord;
	public static String userEnteredMerkleRoot;
	public static String merkleRoot = null;
	public static int strikes = 0;
	
	public void manage() {
		
		Util util = new Util();
		
		userEnteredMerkleRoot = util.promptUser("What is the expected Merkle root");
		Thread oThread1 = new Thread(new MerkleThread());
	    oThread1.start();
		Thread oThread2 = new Thread(new RogueThread());
		oThread2.start();
		Thread oThread3 = new Thread(new MonitorThread());
		oThread3.start();
		while(true) {
			userEnteredWord = util.promptUser("Enter the words: ");
		}
	}
	public static synchronized  String grabWord() {
		String temp;
		temp = userEnteredWord;
		userEnteredWord = null;
		return temp;
	}

}
