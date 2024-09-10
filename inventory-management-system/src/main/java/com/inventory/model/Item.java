package com.inventory.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
    private String itemName;
    private String itemEnteredByUser;
    private LocalDateTime itemEnteredDate;
    private Double itemBuyingPrice;
    private Double itemSellingPrice;
    private LocalDateTime itemLastModifiedDate;
    private String itemLastModifiedByUser;
    
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
    
    @PrePersist
    protected void onCreate() {
        itemEnteredDate = LocalDateTime.now();
        itemLastModifiedDate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        itemLastModifiedDate = LocalDateTime.now();
    }
    
    
    
    public Item(Long itemId, String itemName, String itemEnteredByUser, LocalDateTime itemEnteredDate,
			Double itemBuyingPrice, Double itemSellingPrice, LocalDateTime itemLastModifiedDate,
			String itemLastModifiedByUser, ItemStatus itemStatus) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemEnteredByUser = itemEnteredByUser;
		this.itemEnteredDate = itemEnteredDate;
		this.itemBuyingPrice = itemBuyingPrice;
		this.itemSellingPrice = itemSellingPrice;
		this.itemLastModifiedDate = itemLastModifiedDate;
		this.itemLastModifiedByUser = itemLastModifiedByUser;
		this.itemStatus = itemStatus;
	}

	public Item() {
		super();
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemEnteredByUser() {
		return itemEnteredByUser;
	}

	public void setItemEnteredByUser(String itemEnteredByUser) {
		this.itemEnteredByUser = itemEnteredByUser;
	}

	public LocalDateTime getItemEnteredDate() {
		return itemEnteredDate;
	}

	public void setItemEnteredDate(LocalDateTime itemEnteredDate) {
		this.itemEnteredDate = itemEnteredDate;
	}

	public Double getItemBuyingPrice() {
		return itemBuyingPrice;
	}

	public void setItemBuyingPrice(Double itemBuyingPrice) {
		this.itemBuyingPrice = itemBuyingPrice;
	}

	public Double getItemSellingPrice() {
		return itemSellingPrice;
	}

	public void setItemSellingPrice(Double itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
	}

	public LocalDateTime getItemLastModifiedDate() {
		return itemLastModifiedDate;
	}

	public void setItemLastModifiedDate(LocalDateTime itemLastModifiedDate) {
		this.itemLastModifiedDate = itemLastModifiedDate;
	}

	public String getItemLastModifiedByUser() {
		return itemLastModifiedByUser;
	}

	public void setItemLastModifiedByUser(String itemLastModifiedByUser) {
		this.itemLastModifiedByUser = itemLastModifiedByUser;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	public enum ItemStatus {
        AVAILABLE,
        SOLD
    }

}
