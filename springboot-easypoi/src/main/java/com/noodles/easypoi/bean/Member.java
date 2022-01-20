package com.noodles.easypoi.bean;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @filename Member
 * @description 购物会员
 * @author noodles
 * @date 2021/10/20 15:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Member {

	@Excel(name = "ID", width = 10)
	private Long id;

	@Excel(name = "用户名", width = 20, needMerge = true)
	private String username;

	private String password;

	@Excel(name = "昵称", width = 20, needMerge = true)
	private String nickname;

	@Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
	private Date birthday;

	@Excel(name = "手机号", width = 20, needMerge = true, desensitizationRule = "3_4")
	private String phone;

	private String icon;

	@Excel(name = "性别", width = 10, replace = { "男_0", "女_1" })
	private Integer gender;
}
