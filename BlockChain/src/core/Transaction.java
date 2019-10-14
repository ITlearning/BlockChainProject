package core;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Transaction {
	
	String sender;
	String receiver;
	double amount;
	
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	public Transaction(String sender, String receiver, double amount) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
	
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date time = new Date();
		
		String nowTime = format.format(time);
		return nowTime;
	}
	
	public String getInformation() {
		return "[" + getDate() + "]" + sender + "이(가) " + receiver + "개의 코인을 보냈습니다.";
	}
}
