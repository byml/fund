package net.byml.fund.web;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.byml.common.util.DataDefinitions;
import net.byml.common.util.DataDefinitionsManager;
import net.byml.common.util.ImportExcelHelper;
import net.byml.fund.model.FundType;
import net.byml.fund.service.FundTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			InputStream is = null;
			try {
				is = file.getInputStream();
				DataDefinitions dataDefinitions = DataDefinitionsManager
						.getInstance().getDataDefinitions("fundType");
				List<Map<String, Object>> mapList = ImportExcelHelper
						.getInstance().toMapList(is, dataDefinitions);
				service.saveByMapList(mapList);
				System.out.println(mapList);
				// store the bytes somewhere
			} finally {
				if (is != null) {
					is.close();
				}
			}
			return "fundTYpeList.html";
		} else {
			return "redirect:queryList";
		}
	}

	@RequestMapping(value = "/download")
	public ModelAndView download() throws Exception {
		Map<String, Object> map = queryList();
		map.put("name", "fundType");
		return new ModelAndView("downloadFundType", map);
	}
}