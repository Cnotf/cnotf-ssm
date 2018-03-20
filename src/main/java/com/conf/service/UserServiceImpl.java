package com.conf.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conf.dao.UserMapper;
import com.conf.model.User;

@Service
public class UserServiceImpl implements IUserService{

	@Resource
	private UserMapper usermapper;
	
	// 注入Redis工具类
	@Resource
    private RedisPool redisUtils;
	
	public User getUser (Integer id) {
		return usermapper.selectByPrimaryKey(id);
	}
	
	public String queryByRedis(Integer id) {
        // 1.从缓存中命中
        try {
            String redisJson = redisUtils.get(id+"");
            if (redisJson != null) {
                return redisJson+"===redis";
            }
        } catch (Exception e) {
           // e.printStackTrace();
        }

        // 2.如果没用命中，执行原有逻辑，从数据库中获取数据
        // 未实现，从数据库中获取数据
        User user = usermapper.selectByPrimaryKey(id);

        // 3.将查询出来的结果加入缓存
        try {
            this.redisUtils.set(id+"", user.getAge()+"");
            this.redisUtils.expire(id+"", 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.getAge()+"===mysql";
    }
}
