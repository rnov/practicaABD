package model;

//import java.math.BigInteger;

public class UserKeys {
	//private BigInteger key;
	private long key;
	private long key2;
	private long key3;
	private String keyS;
	
	public UserKeys( long key) {//BigInteger
		super();
		this.key = key;
	}
	
	
	
	public UserKeys( long key,long key2,long key3) {
		super();
		this.key = key;
		this.key2=key2;
		this.key3=key3;
	}
	
	public UserKeys( long key,long key2) {
		super();
		this.key = key;
		this.key2=key2;
	}
	
	public UserKeys( long key,String keyS) {
		super();
		this.key = key;
		this.keyS=keyS;
	}
	public String getKeyS(){
		return keyS;
	}
	
	public long getKey3() {
		return key3;
	}
	
	public long getKey2() {
		return key2;
	}
	
	public long getKey() {//BigInteger
		return key;
	}

	public void setKey(long key) {//BigInteger
		this.key = key;
	}
	
}
