package com.example.demo;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Products {
	@Id
	private UUID pid = UUID.randomUUID();
	private String name;
	private String category;
	private String text;
	
	@Override
	public String toString() {
		return "Products [pid=" + pid + ", name=" + name + ", category=" + category + ", text=" + text + "]";
	}

	public UUID getPid() {
		return pid;
	}

	public void setPid(UUID pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

}
