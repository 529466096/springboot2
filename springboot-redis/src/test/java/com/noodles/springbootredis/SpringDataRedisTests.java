package com.noodles.springbootredis;


import com.noodles.logback.MySlf4j;
import com.noodles.springbootredis.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.ScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRedisTests {

	/**
	 * Redis其它类型所对应的操作方式:
	 *   opsForValue：对应 String（字符串）
	 *   opsForZSet：对应 ZSet（有序集合）
	 *   opsForHash：对应 Hash（哈希）
	 *   opsForList：对应 List（列表）
	 *   opsForSet：对应 Set（集合）
	 *   opsForGeo：对应 GEO（地理位置）
	 * */

	private static final Logger log = LoggerFactory.getLogger(SpringDataRedisTests.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;


	@Test
	public void testKeyScan1(){

	}

	@Test
	public void testKeyScan () throws Exception{

		Long start = System.currentTimeMillis();

		String realKey = "{\"applId\":*_*";
		try {
			redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
				Set<String> binaryKeys = new HashSet<>();
				Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(realKey).count(100).build());
				while (cursor.hasNext()) {
					String key = new String(cursor.next());

					Long expire = stringRedisTemplate.getExpire(key);

					System.out.println("key=" + key + ",expire=" + expire);

					//MySlf4j.textInfo("key={0},expire={1}", key, expire);
				}
				return binaryKeys;
			});

		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("耗时(秒)：" + (System.currentTimeMillis() - start) / 1000);
	}

	@Test
	public void setKey(){
		stringRedisTemplate.opsForValue().set("k1", "v1test");
		redisTemplate.opsForValue().set("testUser", new User("u1", "pass"));
		System.out.println("done");
	}

	@Test
	public void getKey(){
		final String k1 = stringRedisTemplate.opsForValue().get("foo");
		System.out.println(k1);
		User user = (User) redisTemplate.opsForValue().get("testUser");
		System.out.println(user.getPassword());
	}

	@Test
	public void get() {
		// 测试线程安全
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		IntStream.range(0, 1000).forEach(i ->
				executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
		);

	}
}