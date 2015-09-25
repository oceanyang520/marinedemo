package test.java;

import java.util.Vector;
import java.util.Date;
/**
 * 线程测试实例
 * @author 静止的流水
 *
 */
public class Threadnew
{
 /**
  * 
  * @author 静止的流水
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
 * @author 静止的流水
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