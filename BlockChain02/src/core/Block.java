//블록 클래스를 생성해 하나의 
//블록이 가지는 특성을 정의 해보는 코드

package core;

import java.util.ArrayList;

import util.Util;

public class Block {
	
	private int blockID;
	private String previousBlockHash;
	private int nonce;
	private ArrayList<Transaction> transactionList;
	
	public int getBlockID() {
		return blockID;
	}
	
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}
	
	public void setPrevoiusBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}
	
	public int getNonce() {
		return nonce;
	}
	
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	
	public String getTransaction() {
		String transactionInformations = "";
		
		for(int i = 0; i < transactionList.size(); i++) {
			transactionInformations += transactionList.get(i).getInformation();
		}
		
		return transactionInformations;
 	}
	
	public void addTransaction(Transaction transaction) {
		transactionList.add(transaction);
	}
	
	public Block(int blockID, String previousBlockHash, int nonce, ArrayList<Transaction> transactionList) {
		this.blockID = blockID;
		this.previousBlockHash = previousBlockHash;
		this.nonce = nonce;
		this.transactionList = transactionList;
	}

	public String getBlockHash() {
		return Util.getHash(nonce + getTransaction() + previousBlockHash);
	}
	
	public void showInformation() {
		System.out.println("---------------------------");
		System.out.println("블록 번호 : " + getBlockID());
		System.out.println("이전 해시 : " + getPreviousBlockHash());
		System.out.println("채굴 변수 값 : " + getNonce());
		System.out.println("블록 데이터 : ");
		for(int i = 0; i < transactionList.size(); i++) System.out.println(transactionList.get(i).getInformation());
		System.out.println("블록 해시 : " + getBlockHash());
		System.out.println("---------------------------");
	}
	
	public void mine() {
		while(true) {
			if(getBlockHash().substring(0, 4).equals("0000")) {
				System.out.println(blockID + "번째 블록의 채굴에 성공했습니다.");
				break;
			}
			nonce++;
		}
	}
}


