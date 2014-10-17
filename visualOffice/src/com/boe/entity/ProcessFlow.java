package com.boe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PROCESSFLOW")
public class ProcessFlow implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "generate")
	@GenericGenerator(name = "generate", strategy = "native")   
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 50)
	private String stepId;
	
	@Column(length = 50)
	private String equipName;
	
	@Column(length = 50)
	private String rcp;
	
	@Column(length = 50)
	private String processSpec;
	
	@Column(length = 50)
	private String orderCode;
	
	@Column(length = 50)
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

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getRcp() {
		return rcp;
	}

	public void setRcp(String rcp) {
		this.rcp = rcp;
	}

	public String getProcessSpec() {
		return processSpec;
	}

	public void setProcessSpec(String processSpec) {
		this.processSpec = processSpec;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
