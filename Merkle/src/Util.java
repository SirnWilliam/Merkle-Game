import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Util {
	
	String enteredText;
	
	/*public String getMerkleRoot(ArrayList<String>lstWord) {
		
		MerkleNode oNode0 = new MerkleNode();
        MerkleNode oNode1 = new MerkleNode();
        MerkleNode oNode2 = new MerkleNode();
        MerkleNode oNode3 = new MerkleNode();
        MerkleNode oNode4 = new MerkleNode();
        MerkleNode oNode5 = new MerkleNode();
        MerkleNode oNode6 = new MerkleNode();
        
        oNode0.sHash = generateHash(lstWord.get(0));
        oNode1.sHash = generateHash(lstWord.get(1));
        oNode2.sHash = generateHash(lstWord.get(2));
        oNode3.sHash = generateHash(lstWord.get(3));
        populateMerkleNode(oNode4, oNode0, oNode1);
        populateMerkleNode(oNode5, oNode2, oNode3);
        populateMerkleNode(oNode6, oNode4, oNode5);
        
        return oNode6.sHash;
		
	}*/
	public String getMerkleRoot(ArrayList<String> lstWord) {
		int oddindex =1,
			eveninedx=0 ;
		MerkleNode[] mNodes = new MerkleNode[(2 * lstWord.size())-1];
		for(int i=0;i <lstWord.size();i++) {
			mNodes[i].sHash = generateHash(lstWord.get(i));
		}
		for(int i=0;i<=lstWord.size()/2;i++) {
			populateMerkleNode(mNodes[lstWord.size() + i],mNodes[eveninedx], mNodes[oddindex]);
			oddindex = oddindex +2;
			eveninedx = eveninedx +2;
		}
		populateMerkleNode(mNodes[(2 * lstWord.size())-2], mNodes[(2 * lstWord.size())-4], mNodes[(2 * lstWord.size())-3]);
		
		return mNodes[(2 * lstWord.size())-2].sHash;
		
	}
	
	private  void populateMerkleNode(MerkleNode oNode, MerkleNode oLeftNode, MerkleNode oRightNode){

        oNode.sHash = generateHash(oLeftNode.sHash + oRightNode.sHash);
        oNode.oLeft = oLeftNode;
        oNode.oRight = oRightNode;
    }
	
	public synchronized String generateHash(String sOriginal){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] btEncodedhash = digest.digest(
                    sOriginal.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < btEncodedhash.length; i++) {
                sb.append(Integer.toString((btEncodedhash[i] & 0xff) + 0x100,
                        16).substring(1));
            }
            return sb.toString();
        }
        catch(Exception ex){
            System.out.println("Error generating hash: " + ex.getMessage());
            return null;
        }
    }
	
	public String promptUser(String sQuestion){

        JOptionPane oQuestion  = new JOptionPane();
        String sAnswer = oQuestion.showInputDialog(sQuestion);
        return sAnswer;
}

	
	public void sleepRandomTime(String threadName) {
		
		int num;
		SecureRandom ranNum = new SecureRandom();
		num = ranNum.nextInt(5)+3;
		System.out.println(threadName + " " +num);
		sleep(num);
		
		
	}
	
	public void sleep(int iseconds){
        try {
            Thread.sleep(iseconds * 1000);
        }
        catch (Exception ex){
            //do nothing
        }


    }
	

}
