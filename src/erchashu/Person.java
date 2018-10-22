package erchashu;

public class Person {
	
	int iData;
	double fData;
	public int getiData() {
		return iData;
	}
	public void setiData(int iData) {
		this.iData = iData;
	}
	public double getfData() {
		return fData;
	}
	public void setfData(double fData) {
		this.fData = fData;
	}
	@Override
	public String toString() {
		return "Person [iData=" + iData + ", fData=" + fData + "]";
	}
	
	

}
