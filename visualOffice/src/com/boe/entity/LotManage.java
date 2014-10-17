package com.boe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LOT_MANAGE")
public class LotManage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "generate")
	@GenericGenerator(name = "generate", strategy = "native")   
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 20)
	private String stepId;
	
	@Column(length = 50)
	private String specialRequire;
	
	@Column(length = 1)
	private String glassExtra;
	
	@Column(length = 1)
	private String splitInfo;
	
	@Column(length = 20)
	private String addItem;
	
	@Column(length = 20)
	private String signName;
	
	@Column(length = 20, nullable = false)
	private String productId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getSpecialRequire() {
		return specialRequire;
	}

	public void setSpecialRequire(String specialRequire) {
		this.specialRequire = specialRequire;
	}

	public String getGlassExtra() {
		return glassExtra;
	}

	public void setGlassExtra(String glassExtra) {
		this.glassExtra = glassExtra;
	}

	public String getSplitInfo() {
		return splitInfo;
	}

	public void setSplitInfo(String splitInfo) {
		this.splitInfo = splitInfo;
	}

	public String getAddItem() {
		return addItem;
	}

	public void setAddItem(String addItem) {
		this.addItem = addItem;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
