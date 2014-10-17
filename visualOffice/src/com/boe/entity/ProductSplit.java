package com.boe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRODUCT_SPLIT")
public class ProductSplit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "generate")
	@GenericGenerator(name = "generate", strategy = "native")   
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 40)
	private String glassId;
	
	@Column(length = 20)
	private String splitCount;
	
	@Column(length = 20)
	private String splitDetail;
	
	@Column(length = 20)
	private String splitAim;
	
	@Column(length = 20)
	private String department1;
	
	@Column(length = 20)
	private String department2;
	
	@Column(length = 20)
	private String department3;
	
	@Column(length = 20)
	private String orderCode;
	
	@Column(length = 40)
	private String productId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGlassId() {
		return glassId;
	}

	public void setGlassId(String glassId) {
		this.glassId = glassId;
	}

	public String getSplitCount() {
		return splitCount;
	}

	public void setSplitCount(String splitCount) {
		this.splitCount = splitCount;
	}

	public String getSplitDetail() {
		return splitDetail;
	}

	public void setSplitDetail(String splitDetail) {
		this.splitDetail = splitDetail;
	}

	public String getSplitAim() {
		return splitAim;
	}

	public void setSplitAim(String splitAim) {
		this.splitAim = splitAim;
	}

	public String getDepartment1() {
		return department1;
	}

	public void setDepartment1(String department1) {
		this.department1 = department1;
	}

	public String getDepartment2() {
		return department2;
	}

	public void setDepartment2(String department2) {
		this.department2 = department2;
	}

	public String getDepartment3() {
		return department3;
	}

	public void setDepartment3(String department3) {
		this.department3 = department3;
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
