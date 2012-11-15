package net.byml.fund.config;

import net.byml.common.util.DataDefinition;
import net.byml.common.util.DataDefinitions;
import net.byml.common.util.DataDefinitionsManager;
import net.byml.common.util.DataType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataDefinitionsConfig {
	@Bean
	public DataDefinitionsManager getDataDefinitionsManager() {
		DataDefinitionsManager manager = DataDefinitionsManager.getInstance();
		DataDefinitions dataDefinitions = new DataDefinitions();
		dataDefinitions.add(new DataDefinition("code", DataType.STRING));
		dataDefinitions.add(new DataDefinition("name", DataType.STRING));
		manager.registerDataDefinitions("fundType", dataDefinitions);
		return manager;
	}
}
