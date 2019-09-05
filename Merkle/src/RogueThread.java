public class RogueThread implements Runnable{
	
     public void run() {
    	 Util util = new Util();
			 
    	 while(true) { 
				util.sleepRandomTime("RogueThread");
				String sNewWord = MerkleManager.grabWord();
				if(sNewWord != null) {
					MerkleManager.strikes ++;
				    System.out.println("Rogue grabbed the word " + "  " + "Strikes  "  + MerkleManager.strikes);
				}
		 }
     }
}
