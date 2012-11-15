package net.byml.common.util;

import lombok.Data;

@Data
public class DataDefinition implements DataType {
	private short position;
	private String key;
	private int dataType;

	public DataDefinition(String key, int dataType) {
		super();
		this.position = -1;
		this.key = key;
		this.dataType = dataType;
	}

	public DataDefinition(short position, String key, int dataType) {
		super();
		this.position = position;
		this.key = key;
		this.dataType = dataType;
	}
}
