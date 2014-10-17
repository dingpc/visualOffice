package com.boe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRODUCT_SPEC")
public class ProductSpec implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "generate")
	@GenericGenerator(name = "generate", strategy = "native")   
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 40)
	private String layer;
	
	@Column(length = 20)
	private String maskcd;
	
	@Column(length = 20)
	private String dccd;
	
	@Column(length = 20)
	private String ficd;
	
	@Column(length = 20)
	private String material;
	
	@Column(length = 20)
	private String thick;
	
	@Column(length = 20)
	private String rs;
	
	@Column(length = 40)
	private String productId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getMaskcd() {
		return maskcd;
	}

	public void setMaskcd(String maskcd) {
		this.maskcd = maskcd;
	}

	public String getDccd() {
		return dccd;
	}

	public void setDccd(String dccd) {
		this.dccd = dccd;
	}

	public String getFicd() {
		return ficd;
	}

	public void setFicd(String ficd) {
		this.ficd = ficd;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getThick() {
		return thick;
	}

	public void setThick(String thick) {
		this.thick = thick;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
