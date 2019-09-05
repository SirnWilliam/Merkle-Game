import java.util.ArrayList;

public class MerkleThread implements Runnable{

	public static volatile ArrayList<String>lstWord;
    private int iMerkleTreeInputs = 4;
	
	public void run() {
		Util util = new Util();
		ArrayList<String> lstWord = new ArrayList<>();
	while(true) {
		util.sleepRandomTime("MerkleThread");
		String sNewWord = MerkleManager.grabWord();
		if(sNewWord != null) {
			System.out.println(sNewWord);
		    lstWord.add(sNewWord);
		    if(lstWord.size() == iMerkleTreeInputs) {
				MerkleManager.merkleRoot = util.getMerkleRoot(lstWord);
			}
		}
	}
	}
}
