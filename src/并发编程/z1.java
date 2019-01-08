package 并发编程;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.ReentrantLock;

public class z1 {

	/**
	 * 并发
	 * 
	 * 
	 * 1.1上下文切换
	 * 	单核处理器支持的多线程，CPU根每个线程分配时间片，时间片是分配给线程的时间，因为时间片很短，所以cpu通过不停地切换线程执行，让我们感觉是多个线程
	 * 	同时执行，时间片一般是及时毫秒
	 * 	CPU通过时间片分配算法来执行任务，当前任务执行一个时间片后，会切换到下一个任务，切换之前会保存上一个任务的状态，下回切到这个任务时候，可以加载
	 * 	这个任务的状态，所以任务从保存到在加载的过程就是一次上下文切换
	 * 
	 * 	上下文切换会影响多线程的执行效率
	 * 
	 * 
	 * 
	 * 1.1.3 如何减少上下文切换
	 * 		无锁并发编程，cas，使用最少线程和使用协程
	 * 		无视并发编程，多线程在竞争锁的时候，会引起上下文切换，再多线程处理数据时，可以用一些办法避免使用锁，如将数据idhash 取模，分段，不同线程
	 * 		处理不同的数据。
	 * 		协程：在单线程实现多任务调度。并在单线程维持多个任务的切换
	 * 
	 * 1.2死锁
	 * 	避免死锁的方法：
	 * 	避免一个线程同时获取多个锁
	 * 	避免一个线程在锁内同时占用多个资源，尽量保证一个锁只占用一个资源
	 * 	尝试使用定时锁，使用tryLock（timeout）来代替使用内部锁机制
	 * 	对于数据库锁，枷锁和解锁必须在同一个数据库连接中，。否则会出现解锁失败的情况
	 * 	
	 * 1.3资源限制的挑战
	 * 	如何在资源限制的情况下让程序执行的更快呢，根据不同的资源限制调整程序的并发度，比如下载文件程序依赖两个资源，带宽和硬盘的读写速度，有数据库操作时候
	 * 	涉及数据库连接数，如果sql语句执行的非常快，而线程书友比数据库连接大很多的时候 则某些线程会被阻塞，等待数据库连接
	 * 
	 * 
	 * 3.volatile修饰的共享变量在进行写操作时，在生成汇编指令时会有Lock前缀
	 * 			lock前缀再多核处理器下只做两件事：
	 * 				将当前缓存行的数据回写到内存中
	 * 				这个协会内存的操作会使其他cpu里缓存了该内存地址的数据无效
	 * 4.在LinkedTransferQueue使用volatile变量的时候，用一种追加字节的方式优化队列的入队出队，一个对象4字节
	 * 	在追加15个变量 追加到64字节，因为一个缓存行不够64会被填充，队列的头尾节点就会在同一个缓存行，这样每次更新头，或者尾，就都要锁住
	 * 	
	 * 	是否volatile修饰的变量都要追加呢？、
	 * 	不是的
	 * 		两种情况：缓存行非64字节宽的，不是需要频繁写的
	 * 5.synchronized
	 * 	对于欧通方法的锁，是当前实例对象
	 * 	对于静态方法，是当前类的Class对象
	 * 	对于同步代码块：是括号中的同步对象
	 * 		jvm基于Monitor对象和来实现	方法同步和带买块同步  代码块是monitorenter和monitorexit指令实现的，当时方法是另一种方法实现的
	 * 		但是 方法也同样可以用这个指令实现
	 * 
	 * 6.为了减少获取锁和释放锁的性能。从而引入了偏向锁，和轻量级锁。锁的4种状态
	 * 				无锁状态，偏向锁状态，轻量级锁状态，重量级锁状态
	 * 		只能生不能降
	 * 	
	 * 7.CAS三大问题
	 * 	ABA问题
	 * 	循环时间长开销大
	 * 	只能保证一个变量的原子操作
	 * 
	 * 8.线程之间的通信方式有两种：
	 * 	共享内存和消息传递
	 * 	
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] dumpAllThreads = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : dumpAllThreads) {
			System.out.println(threadInfo.getThreadId() + "*****" + threadInfo.getThreadName());
		}
	}
}
