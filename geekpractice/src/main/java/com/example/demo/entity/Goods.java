package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "goods")
public class Goods {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "mekers_id", nullable = false)
	private Integer makersId;
	
	@Column(name = "small_category_id", nullable = false)
	private Integer smallCategoryId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "cost_price", nullable = false)
	private String costPrice;
	
	@Column(name = "retail_price", nullable = false)
	private String retailPrice;
	
	@Column(name = "sales_price", nullable = false)
	private String salesPrice;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getMakersId() {
		return makersId;
	}

	public void setMakersId(Integer id) {
		this.id = makersId;
	}
	
	public Integer getSmallCategoryId() {
		return smallCategoryId;
	}

	public void setSmallCategoryId(Integer id) {
		this.id = smallCategoryId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.name = description;
	}
	
	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.name = costPrice;
	}
	
	public String getretailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.name = retailPrice;
	}
	
	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.name = salesPrice;
	}
	
	@ManyToOne
	@JoinColumn(name = "makers_id", insertable = false, updatable = false)
	private Makers makers;
	
	@ManyToOne
	@JoinColumn(name = "small_category_id", insertable = false, updatable = false)
	private SmallCategory smallCategory;

}
