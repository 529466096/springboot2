package com.noodles.easypoi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.google.gson.reflect.TypeToken;
import com.noodles.api.exception.MicroserviceException;
import com.noodles.api.vo.resp.BaseRespVo;
import com.noodles.easypoi.bean.Member;
import com.noodles.easypoi.handler.MemberExcelDataHandler;
import com.noodles.json.utils.JsonUtils;
import com.noodles.response.utils.ResponseUtils;

/**
 * @filename EasyPoiController
 * @description 导出测试Controller
 * @author noodles
 * @date 2021/10/20 16:01
 */
@Controller
@Api(tags = "EasyPoiController", description = "EasyPoi导出测试")
@RequestMapping("easyPoi")
public class EasyPoiController {

	@ApiOperation(value = "导出会员列表Excel")
	@RequestMapping(value = "/exportMemberList", method = RequestMethod.GET)
	public void exportMemberList(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Member> memberList = JsonUtils.fromJsonList("json/members.json", new TypeToken<List<Member>>() {
		}.getType());

		ExportParams params = new ExportParams("会员列表", "会员列表", ExcelType.XSSF);

		//对导出结果进行自定义处理
		MemberExcelDataHandler handler = new MemberExcelDataHandler();
		handler.setNeedHandlerFields(new String[]{"昵称"});
		params.setDataHandler(handler);
		map.put(NormalExcelConstants.DATA_LIST, memberList);
		map.put(NormalExcelConstants.CLASS, Member.class);
		map.put(NormalExcelConstants.PARAMS, params);
		map.put(NormalExcelConstants.FILE_NAME, "memberList");
		PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
	}

	@ApiOperation("从Excel导入会员列表")
	@RequestMapping(value = "/importMemberList", method = RequestMethod.POST)
	@ResponseBody
	public BaseRespVo importMemberList(@RequestPart("file") MultipartFile file) {
		ImportParams params = new ImportParams();
		params.setTitleRows(1);
		params.setHeadRows(1);
		try {
			List<Member> list = ExcelImportUtil.importExcel(
					file.getInputStream(),
					Member.class, params);
			return ResponseUtils.responseSuccess(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.responseFail("导入失败！");
		}
	}
}
