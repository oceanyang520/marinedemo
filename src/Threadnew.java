
import java.util.Vector;
import java.util.Date;
/**
 * �̲߳���ʵ��
 * @author ��ֹ����ˮ
 *
 */
public class Threadnew
{
 /**
  * 
  * @author ��ֹ����ˮ
  *
  */
class ThreadA extends Thread
{
 private Date runtime;
 public void run()
 {
  System.out.println("ThreadA begin.");
  this.runtime = new Date();
  System.out.println("ThreadA end.");
 }
 
 }
/**
 * 
 * @author ��ֹ����ˮ
 *
 */
class ThreadB implements Runnable
{
 private Date runtime;
 public void run()
 {
  System.out.println("ThreadB begin.");
  this.runtime = new Date();
  System.out.println("ThreadB end.");
 }
 }
/**
 * 
 *
 */
public void starta()
{
 Thread threada = new ThreadA();
 threada.start();
}
/**
 * 
 *
 */
public void startb()
{
 Runnable threadb = new ThreadB();
 Thread thread = new Thread(threadb);
 thread.start();
 }
/**
 * 
 * @param args
 */
public static void main(String[] args)
{
 Threadnew test = new Threadnew();
 test.starta();
 test.startb();
 
}
}