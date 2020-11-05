

// Basically in Dataprovider section we mapping the Customer info with Product info

package com.wc.qa.dataProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wc.qa.datamodel.ProductData;
import com.wc.qa.util.DataBuilder;

public class DataProviders {  
	
@DataProvider(name="ProductData") // 
	
	public Iterator<Object[]> loginDataProvider(){
	 Collection<Object[]>  customerData = new ArrayList<Object[]>();  // making a variable for collection object
	 {
		 {
			 DataBuilder dataBuilder = new DataBuilder();
			 List<ProductData> data = dataBuilder.prepareProductDatas(); // making list for product data
			 
			 for(ProductData customerModel : data) {
				 customerData.add(new Object[] { customerModel});
			 }
		 }
	 }
	 return customerData.iterator();

}
	@Test(dataProvider = "ProductData")
	
	public void testData(ProductData data) {
		System.out.println(data.getProductName());
		System.out.println(data.getCities_Shown());
		System.out.println(data.getSort_by());
		
	}

}
