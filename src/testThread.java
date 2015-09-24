
public class testThread extends Thread {
    
    public void run(){
        
        try {
            System.out.println("启动线程");
            System.out.println("线程结束");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
