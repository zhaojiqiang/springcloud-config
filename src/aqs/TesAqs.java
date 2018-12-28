package aqs;

public class TesAqs {
	
	
	public static void main(String[] args) {
		
		
		for (int i = 0; i < 1; i++) {
			Yhdk yhdk = new Yhdk();
			Thread thread = new Thread(yhdk);
			thread.setName("threasd-"+i);
			
			try {
				thread.start();
				Thread.sleep(1000);
				yhdk.setFF(false);
				thread.interrupt();
	            System.out.println("taskG stop...");  
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	static class Yhdk implements  Runnable  {
		
		private volatile boolean pp = true;
		public void run() {
			try {
				woc();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void setFF(boolean b) {
			pp= false;
		}

		private void woc() throws InterruptedException {
	        System.out.println("TaskG.class start...");  
			for (int i = 0 ;i < 1000000000; i++) {
				if(i % 1000 == 0 && pp){
	                System.out.println("TaskF loop " + i / 1000);  
				}
				Thread.currentThread().sleep(2000);
			}
	        System.out.println("TaskG.class end...");  
		}
	}
	

}
