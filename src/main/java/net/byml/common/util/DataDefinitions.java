package net.byml.common.util;

import java.util.ArrayList;
import java.util.List;

public class DataDefinitions {
	private short crtPosition;
	private List<DataDefinition> dataDefinitionList;

	public DataDefinitions() {
		crtPosition = -1;
		dataDefinitionList = new ArrayList<DataDefinition>();
	}

	public DataDefinitions add(DataDefinition dataDefinition) {
		if (dataDefinition.getPosition() < 0) {
			crtPosition++;
			dataDefinition.setPosition(crtPosition);
		} else {
			crtPosition = dataDefinition.getPosition();
		}
		dataDefinitionList.add(dataDefinition);
		return this;
	}

	public List<DataDefinition> getDataDefinitionList() {
		return dataDefinitionList;
	}
}
