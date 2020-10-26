package com.cn.test.fib;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-16 17:20:00
 * @description : 斐波那契数列处理
 */
public class FibRequestHandle implements FibRequest{
    private static final long[] a;
    static {
        a = new long[1024];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i < 1024; i++) {
            a[i] = a[i-1] + a[i-2];
        }
    }

    @Override
    public long handle(int n){
        return a[n];
    }
}
