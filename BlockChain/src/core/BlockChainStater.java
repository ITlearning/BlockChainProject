
package core;

import java.util.ArrayList;
import util.Util;

public class BlockChainStater {
	
	public static void main(String[] args) {
		//블록 번호가 '1', 정답값이 '0', 블록이 갖고 있는 데이터가 '데이터'
		Block block1 = new Block(1, null, 0 ,"데이터");
		block1.mine();
		block1.getInformation();
		
		Block block2 = new Block(2, block1.getBlockHash(), 0, "변조된 데이터");
		block2.mine();
		block2.getInformation();
		
		Block block3 = new Block(3, block2.getBlockHash(), 0, "데이터");
		block3.mine();
		block3.getInformation();
		
		Block block4 = new Block(4, block3.getBlockHash(), 0, "데이터");
		block4.mine();
		block4.getInformation();
	}
}
