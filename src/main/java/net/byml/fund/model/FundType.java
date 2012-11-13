package net.byml.fund.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;


@Entity
@Table(name = "fund_type")
public class FundType {
	private Long id;
	private String code;
	private String name;

	@Id
	@GeneratedValue
	@Column(name="fund_type_id")	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() { 
		return ReflectionToStringBuilder.toString(this);		
	}

	
}
