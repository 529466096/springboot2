package com.noodles.springbootdemos;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.noodles.api.vo.resp.BaseRespVo;
import com.noodles.json.utils.JsonUtils;

/**
 * @filename SpringBootTest
 * @description SpringBoot测试类
 * @author 巫威
 * @date 2021/2/8 10:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootTestDemo {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testGet() {
		Map<String, String> multiValueMap = new HashMap<>();
		multiValueMap.put("username", "noodles");
		BaseRespVo<String> result = testRestTemplate
				.getForObject("/test/getUser?username={username}", BaseRespVo.class, multiValueMap);

		System.out.println(JsonUtils.toJson(result));
		Assert.assertEquals(result.getCode(), "000000");
	}

	@Test
	public void testPost() throws Exception {
		MultiValueMap multiValueMap = new LinkedMultiValueMap();
		multiValueMap.add("username", "noodles");
		BaseRespVo<String> result = testRestTemplate.postForObject("/test/getUser", multiValueMap, BaseRespVo.class);
		System.out.println(JsonUtils.toJson(result));
		Assert.assertEquals(result.getCode(), "000000");
	}

	@Test
	public void testUpload() throws Exception {
		Resource resource = new FileSystemResource("d:/短信结果回调.txt");
		MultiValueMap multiValueMap = new LinkedMultiValueMap();
		multiValueMap.add("username", "noodles");
		multiValueMap.add("files", resource);
		BaseRespVo<String> result = testRestTemplate.postForObject("/test/upload", multiValueMap, BaseRespVo.class);
		System.out.println(JsonUtils.toJson(result));
		Assert.assertEquals(result.getCode(), "000000");
	}

}
