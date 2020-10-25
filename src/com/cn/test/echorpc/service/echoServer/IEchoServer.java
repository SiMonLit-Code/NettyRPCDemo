package com.cn.test.echorpc.service.echoServer;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-10-22 12:02:00
 * @description :
 */
public interface IEchoServer {
    /**
     * 开始服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

    /**
     * 注册服务
     */
    void register(Class serverInterface, Class impl);

    /**
     * 判断当前服务是否运行
     * @return
     */
    boolean isRunning();

    /**
     * 获取当前使用的端口
     * @return
     */
    int getPort();
}
