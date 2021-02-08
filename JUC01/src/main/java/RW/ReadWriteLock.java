package RW;

import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        //开启两个线程
        for (int i = 1; i <=5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

//自定义缓存资源类
class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();
    private java.util.concurrent.locks.ReadWriteLock lock= new ReentrantReadWriteLock();
    //存，写
    public void put(String key,Object value){
        //写锁，一次只能一个线程写
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }
    //取，读
    public void get(String key){
        //读锁，所有人可以同时读
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object o=map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

    }
}
