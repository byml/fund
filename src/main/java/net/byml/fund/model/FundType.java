package net.byml.fund.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import net.byml.common.util.DataDefinition;
import net.byml.common.util.DataDefinitions;
import net.byml.common.util.DataType;

@Entity
@Table(name = "fund_type")
@Data
public class FundType {
	@Id
	@GeneratedValue
	@Column(name = "fund_type_id")
	private Long id;
	private String code;
	private String name;

	public DataDefinitions t() {
		DataDefinitions dataDefinitions = new DataDefinitions();
		dataDefinitions.add(new DataDefinition("code", DataType.STRING));
		dataDefinitions.add(new DataDefinition("name", DataType.STRING));
		return dataDefinitions;
	}
}
