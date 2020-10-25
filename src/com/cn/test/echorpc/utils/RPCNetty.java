package com.cn.test.echorpc.utils;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/22 21:27
 * 通讯实体
 */
public class RPCNetty {
    //类名
    private String clzName;
    //类
    private Class clz;
    //方法名
    private String methodName;
    //参数类型
    private Class<?>[] paramTypes;
    //参数
    private Object[] arguments;

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }

    public Class getClz() {
        return clz;
    }

    public void setClz(Class clz) {
        this.clz = clz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
