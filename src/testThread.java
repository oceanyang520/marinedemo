
public class testThread extends Thread {
	
	public void run(){
		
		try {
			System.out.println("�����߳�");
			System.out.println("�߳̽���");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
