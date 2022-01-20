package com.noodles.easypoi.handler;

import org.apache.commons.lang3.StringUtils;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.noodles.easypoi.bean.Member;

/**
 * @filename MemberExcelDataHandler
 * @description
 * @author noodles
 * @date 2021/10/20 16:54
 */
public class MemberExcelDataHandler extends ExcelDataHandlerDefaultImpl<Member> {

	@Override
	public Object exportHandler(Member obj, String name, Object value) {
		if ("昵称".equals(name)) {
			String emptyValue = "暂未设置";
			if (value == null) {
				return super.exportHandler(obj, name, emptyValue);
			}
			if (value instanceof String && StringUtils.isBlank((String) value)) {
				return super.exportHandler(obj, name, emptyValue);
			}
		}
		return super.exportHandler(obj, name, value);
	}

	@Override
	public Object importHandler(Member obj, String name, Object value) {
		return super.importHandler(obj, name, value);
	}
}
