/**
 * 
 */
package com.target.retailServices.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Muhammad Shouab
 *
 */
@Document(collection = "ProductPrice")
public class ProductPrice {
		
	@Id
	@JsonIgnore
	private Integer productID;
	private double price;
	private String currencyCode;
	
	/**
	 * 
	 */
	public ProductPrice() {
	}
	
	/**
	 * @param productID
	 * @param price
	 * @param currencyCode
	 */
	public ProductPrice(Integer productID, double price, String currencyCode) {
		super();
		this.productID = productID;
		this.price = price;
		this.currencyCode = currencyCode;
	}
	
	@JsonIgnore
	public Integer getProductID() {
		return productID;
	}
	
	@JsonProperty
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productID == null) ? 0 : productID.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPrice other = (ProductPrice) obj;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		return true;
	}
}
