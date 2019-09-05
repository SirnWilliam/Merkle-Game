public class MonitorThread implements Runnable{
	
	public void run() {
		Util util = new Util();
		while(true) {
			if(MerkleManager.merkleRoot != null) {
				if(MerkleManager.merkleRoot.equals(MerkleManager.userEnteredMerkleRoot)) {
		          System.out.println("YOU WIN " + MerkleManager.merkleRoot);
				  System.exit(0);
				}
				else if(!MerkleManager.merkleRoot.equals(MerkleManager.userEnteredMerkleRoot)) {
					System.out.println("You Lost");
					System.exit(0);
				}
			}else if(MerkleManager.strikes == 3) {
				System.out.println("3 Strikes: You Lost!");
				System.exit(0);
			}
			util.sleep(1);
			
		}	
	}

}
