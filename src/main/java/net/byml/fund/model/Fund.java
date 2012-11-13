package net.byml.fund.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fund")
public class Fund implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -765883565260349635L;

	private Long id;
	private String code;
	private String fullName;
	private String shortName;
	private FundType fundType;

	private Boolean closed;
	private Boolean yieldAgreed;
	private Boolean floorTrade;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fund_id")
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

	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "short_name")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@ManyToOne
	@JoinColumn(name = "fund_type_id")
	public FundType getFundType() {
		return fundType;
	}

	public void setFundType(FundType fundType) {
		this.fundType = fundType;
	}

	@Column(name = "is_closed")
	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	@Column(name = "is_yield_agreed")
	public Boolean getYieldAgreed() {
		return yieldAgreed;
	}

	public void setYieldAgreed(Boolean yieldAgreed) {
		this.yieldAgreed = yieldAgreed;
	}

	@Column(name = "is_floor_trade")
	public Boolean getFloorTrade() {
		return floorTrade;
	}

	public void setFloorTrade(Boolean floorTrade) {
		this.floorTrade = floorTrade;
	}

}
