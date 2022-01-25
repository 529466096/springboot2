package com.noodles.springbootdemos.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.noodles.api.vo.resp.BaseRespVo;
import com.noodles.response.utils.ResponseUtils;
import com.noodles.security.utils.Base64Utils;
import com.noodles.springbootdemos.bean.AgreeProtocolInfo;
import com.noodles.springbootdemos.dao.AgreeProtocolInfoDao;

/**
 * 单元测试响应Controller
 * @filename TestController
 * @author 巫威
 * @date 2022/1/24 14:26
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private AgreeProtocolInfoDao agreeProtocolInfoDao;

	@RequestMapping("/getUser")
	public BaseRespVo<String> getUser(@RequestParam("username") String username) {
		System.out.println("ddddd:" + username);
		return ResponseUtils.responseSuccess(username);
	}

	@RequestMapping("/upload")
	public BaseRespVo<String> upload(@RequestParam("username") String username,
			@RequestParam(name = "files") MultipartFile files) {
		System.out.println("username:" + username + ",file size = " + files.getSize());
		try {
			String str = new String(files.getBytes());
			String base64 = Base64Utils.encode(files.getBytes());
			System.out.println(str);
			System.out.println(base64);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseUtils.responseSuccess(username);
	}

}
