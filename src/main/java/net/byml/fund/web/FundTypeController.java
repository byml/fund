package net.byml.fund.web;

import java.util.HashMap;
import java.util.Map;

import net.byml.fund.model.FundType;
import net.byml.fund.service.FundTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fundType")
public class FundTypeController {
	@Autowired
	FundTypeService service;

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	public FundType find(@PathVariable("code") String code, Model model) {
		FundType entity = service.findByCode(code);
		return entity;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(FundType entity) throws Exception {
		// spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		try {
			service.save(entity);
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map;
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<FundType> listAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/queryList")
	@ResponseBody
	public Map<String, Object> queryList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", service.findAll());
		map.put("total", 100);
		return map;
	}
}