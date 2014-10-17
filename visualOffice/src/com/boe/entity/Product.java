package com.boe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "generate")
	@GenericGenerator(name = "generate", strategy = "native")   
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 20)
	private String productId;
	
	@Column(length = 20)
	private String productName;
	
	@Column(length = 20)
	private String enAim;
	
	@Column(length = 20)
	private Long productCount;
	
	@Column(length = 20)
	private Long lotCount;
	
	@Column(length = 20)
	private String productPlan;
	
	@Column(length = 20)
	private String productStatus;
	
	@Column(length = 20)
	private String todayPlan;
	
	@Column(length = 20)
	private String productManager;
	
	@Column(length = 20)
	private String productLeader;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getEnAim() {
		return enAim;
	}

	public void setEnAim(String enAim) {
		this.enAim = enAim;
	}

	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	public String getProductPlan() {
		return productPlan;
	}

	public void setProductPlan(String productPlan) {
		this.productPlan = productPlan;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getTodayPlan() {
		return todayPlan;
	}

	public void setTodayPlan(String todayPlan) {
		this.todayPlan = todayPlan;
	}

	public String getProductManager() {
		return productManager;
	}

	public void setProductManager(String productManager) {
		this.productManager = productManager;
	}

	public String getProductLeader() {
		return productLeader;
	}

	public void setProductLeader(String productLeader) {
		this.productLeader = productLeader;
	}

	public Long getLotCount() {
		return lotCount;
	}

	public void setLotCount(Long lotCount) {
		this.lotCount = lotCount;
	}
	
	
}
