package com.todo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.constants.CommonConstants;


@Entity
@Table(name = "todo_transaction", schema = CommonConstants.SCHEMA)
public class TodoTransaction implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status_id")
	private Long statusId;

	@Column(name = "is_deleted" ,nullable = false , insertable=false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
		
	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@OneToOne(fetch = FetchType.EAGER,targetEntity = TodoStatus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private TodoStatus todoStatusBean;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public TodoStatus getTodoStatusBean() {
		return todoStatusBean;
	}

	public void setTodoStatusBean(TodoStatus todoStatusBean) {
		this.todoStatusBean = todoStatusBean;
	}	
	




}
