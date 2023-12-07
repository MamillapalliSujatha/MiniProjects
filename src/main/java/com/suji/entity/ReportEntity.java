package com.suji.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ELEGIBILITY_DTLS")
public class ReportEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sno;

	private String name;
	
	private String email;
	
	private Long mobile;
	
	private String gender;
	
	private Long ssn;
	
    private String planName;
	
	private String planStatus;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String createdBy;

	private String updatedBy;

	@CreationTimestamp
	@Column(name="created_date",updatable=false)
	private LocalDate createDate;

	@UpdateTimestamp
	@Column(name="update_date",insertable=false)
	private LocalDate updateDate;
	
	
}
