package net.byml.common.util;

import java.util.HashMap;
import java.util.Map;

public class DataDefinitionsManager {
	private static DataDefinitionsManager instance = new DataDefinitionsManager();
	private Map<String, DataDefinitions> dataDefinitionsMap = new HashMap<String, DataDefinitions>();

	private DataDefinitionsManager() {

	}

	public static DataDefinitionsManager getInstance() {
		return instance;
	}

	public DataDefinitions getDataDefinitions(String name) {
		return dataDefinitionsMap.get(name);
	}

	public void registerDataDefinitions(String name,
			DataDefinitions dataDefinitions) {
		dataDefinitionsMap.put(name, dataDefinitions);
	}
}
