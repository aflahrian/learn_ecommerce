package com.example.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter 
@Setter
public class Order {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long productId;
	    private int quantity;
	    private LocalDateTime orderDateTime;
	    
	    @Transient
	    private String response;
	    
	    @Transient
	    private String status;

}
