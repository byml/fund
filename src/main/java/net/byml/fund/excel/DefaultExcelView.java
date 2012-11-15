package net.byml.fund.excel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.byml.common.util.DataDefinition;
import net.byml.common.util.DataDefinitions;
import net.byml.common.util.DataDefinitionsManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class DefaultExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HSSFSheet sheet;
		HSSFCell cell;
		String name = (String) model.get("name");
		sheet = workbook.createSheet(name);
		List<?> valueList = (List<?>) model.get("rows");
		DataDefinitions dataDefinitions = DataDefinitionsManager.getInstance()
				.getDataDefinitions(name);
		for (Iterator<DataDefinition> it = dataDefinitions
				.getDataDefinitionList().iterator(); it.hasNext();) {
			DataDefinition dataDefinition = it.next();
			cell = getCell(sheet, 0, dataDefinition.getPosition());
			setText(cell, dataDefinition.getKey());
		}
		for (int i = 1; i <= valueList.size(); i++) {
			Object value = valueList.get(i - 1);
			for (Iterator<DataDefinition> it = dataDefinitions
					.getDataDefinitionList().iterator(); it.hasNext();) {
				DataDefinition dataDefinition = it.next();
				Object propertyValue = PropertyUtils.getProperty(value,
						dataDefinition.getKey());
				if (propertyValue != null) {
					cell = getCell(sheet, i, dataDefinition.getPosition());
					setText(cell, propertyValue.toString());
				}
			}
		}
	}
}
