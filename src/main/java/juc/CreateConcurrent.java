package juc;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author: Lee
 * @Description: 如何创建线程
 * @DateTime: 2026/7/17 上午9:44
 **/
public class CreateConcurrent {
    //通过自动装配的方式来调用某一个实现 String通过Spring的加载Bean名称
    private Map<String ,Runnable> runnableMap;


    public static void main(String[] args) {
        //这个是主线程
//        Thread t = new tThread();
//        Thread thread = new Thread(new tThread());


        System.out.println("main run s-");
        //开启了一个新的线程
        new Thread(()->{
            System.out.println("thread run s-");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("thread run e-");

        }).start();

        new Thread(()->{
            System.out.println("thread run2 s-");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("thread run2 e-");

        }).run();
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("main run e-");

    }
}

class tThread extends Thread{

}

//解耦
class methodClass implements Runnable{

    @Override
    public void run() {
        //做二次调用
        System.out.println("实现run方法");
    }


}

class methodClass2 implements Callable{


    @Override
    public Object call() throws Exception {
        return null;
    }
}
