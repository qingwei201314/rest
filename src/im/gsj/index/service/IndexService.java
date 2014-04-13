package im.gsj.index.service;

import im.gsj.user.dto.ShopInfo;
import im.gsj.user.dto.User;
import im.gsj.user.mapper.UserMapper;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class IndexService {
	@Resource
	private UserMapper userMapper;
	@Resource
	private Index2Service index2Service;

	private static ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 2, 0L,
			TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public User getUser(final String userId) {
		List<ShopInfo> shopInfoList = userMapper.query("8a100d6543cc6a260143cc6f1b5b0000");

		for (ShopInfo shopInfo : shopInfoList) {
			System.out.println("这是第一个service:" + shopInfo.getName());
		}

		for (int i = 0; i < 10; i++) {
			Thread thread1 = new Thread(new Runnable() {
				public void run() {
					System.out.println("这是第1个service:");
					User user = index2Service.getUser(userId);
					System.out.println("第1 个service执行完成");
				}
			});
			
			System.out.println("poolSize:" + exec.getPoolSize() + ", maximumPoolSize:" + exec.getMaximumPoolSize() + ", activeSize:" + exec.getActiveCount());
			if(exec.getActiveCount() >= exec.getMaximumPoolSize()){
				System.out.println("不执行线程：" + i);
			}
			else{
				exec.execute(thread1);
			}
		}

		return null;
	}
}
