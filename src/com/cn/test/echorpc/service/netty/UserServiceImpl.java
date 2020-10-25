package com.cn.test.echorpc.service.netty;

/**
 * @author czz
 * @version 1.0
 * @date 2020/10/25 10:45
 */
public class UserServiceImpl implements IUserService {
    @Override
    public String getUser(String id) {
        return "I am Service, Hi Client"+id+", nice to meet you :)";
    }
}
