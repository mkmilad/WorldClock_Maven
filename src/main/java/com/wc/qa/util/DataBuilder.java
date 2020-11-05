package com.wc.qa.util;

import java.util.ArrayList;
import java.util.List;

//import com.wc.qa.util.Read_Xlsx;
import com.wc.qa.datamodel.ProductData;

public class DataBuilder {
	
	public List<ProductData> prepareProductDatas(){
		List<ProductData> productsData = new ArrayList<ProductData>();
		
		Read_xlsx raed = new Read_xlsx("Product.xlsx", "src/main/resources/test/");
		Object[][] objs = new Object[raed.retrieveNoOfRows("Sheet1") -1][raed.retrieveNoOfCols("Sheet1")];
		objs = raed.retrieveTestData1("Sheet1");
		
		for(Object[] obj : objs) {
			ProductData productDat2 = new ProductData();
			productDat2.setProductName(obj[0].toString());
			productDat2.setCities_Shown(obj[1].toString());
			productDat2.setSort_by(obj[2].toString());
			
		}
		return productsData;
	}

}
