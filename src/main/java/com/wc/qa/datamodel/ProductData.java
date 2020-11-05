package com.wc.qa.datamodel;

import com.wc.qa.datamodel.ProductData;

public class ProductData {
	
	private String productName;
	private String Cities_Shown;
	private String Sort_by;
   //private String color;

	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		
		this.productName =  productName;
		
	}
	
	public String getCities_Shown() {
		return Cities_Shown;
	}
	
	public void setCities_Shown(String Cities_Shown) {
		
		this.Cities_Shown =  Cities_Shown;
		
	}
	
	public String getSort_by() {
		return Sort_by;
	}
	
	public void setSort_by(String Sort_by) {
		
		this.Sort_by =  Sort_by;
		
	}
	
	
	
	@Override
	public int hashCode() {
	final int prime = 31; // maximum value
	int result = 1; // initial/starting value
	result = prime * result + ((productName == null)? 0 : productName.hashCode());
	result = prime * result + ((Cities_Shown == null)? 0 : Cities_Shown.hashCode());
	result = prime * result + ((Sort_by == null)? 0 : Sort_by.hashCode());
	
	return result;
	
	}
	
	@Override
	
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ProductData other = (ProductData) obj;
		
		if(productName == null) {
			if(other.productName != null)
				return false;
				
		}else if (!productName.equals(other.productName))
			return false;
		
		
		
		if(Cities_Shown == null) {
			if(other.Cities_Shown != null)
				return false;
				
		}else if (!Cities_Shown.equals(other.Cities_Shown))
			return false;
		
		
		if(Sort_by == null) {
			if(other.Sort_by != null)
				return false;
				
		}else if (!Sort_by.equals(other.Sort_by))
			return false;
		return true;
	
	}
	
     @Override
     public String toString() {
    	 return "ProductData[productName = "+ productName + ", Cities_Shown=" + Cities_Shown +", Sort_by=" + Sort_by + "]";
     }


}
