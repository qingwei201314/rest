package im.gsj.index.service;

import im.gsj.user.dto.User;
import im.gsj.user.mapper.UserMapper;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class Index2Service {
	@Resource
	private UserMapper userMapper;

	public User getUser(String userId) {
		try {
			Thread.currentThread();
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		User user= userMapper.getUser(userId);
		return user;
		
	}
}
