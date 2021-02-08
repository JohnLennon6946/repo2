package com;

public class Test01 {
    public static void main(String[] args) {
        //获取CPU的核数（native方法是调用本地底层C++，java开启线程需要调用native方法，也就是说，java不能自己开启线程，java无法操作硬件）
        //并发：CPU单核，多任务多线程快速交替执行
        //并行：CPU多核，多任务同时进行
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
