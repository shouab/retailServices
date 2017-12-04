/**
 * 
 */
package com.target.retailServices.repositories;

import java.io.Serializable;
import java.util.HashMap;
import com.target.retailServices.domain.ProductName;

/**
 * @author Muhammad Shouab
 *
 */
public final class ProductNameRepositroy implements Serializable{

	private static final long serialVersionUID = 1L;

    HashMap<Integer,ProductName> productNameMap = null;

	    // private instance variable
	    private static ProductNameRepositroy INSTANCE = new ProductNameRepositroy();

	    //**************************
	    // Singleton related methods
	    //**************************

	    private ProductNameRepositroy() {
	        if (INSTANCE != null) {
	        	throw new IllegalStateException("Already instantiated");
	        }
	    }

	    public static ProductNameRepositroy getInstance() {

	        return ProductNameRepositroy.INSTANCE;
	    }

	    
	   
	   public void createProductNames(){

	        if (productNameMap == null)
	        {
	            productNameMap = new HashMap<>();
	        }

	        // clear the hash map
	        productNameMap.clear();

	        ProductName product01 = new ProductName();
	        product01.setProductId(15117729);
	        product01.setProductName("The Big Lebowski (Blu-ray) (Widescreen)");
	        product01.setProductDescription("Wide Screen Blu-Ray Enabled System");
	        productNameMap.put(product01.getProductId(), product01);

	        ProductName product02 = new ProductName();
	        product02.setProductId(16483589);
	        product02.setProductName("The Big Lebowski (Blu-ray) (Widescreen)");
	        product02.setProductDescription("Wide Screen Blu-Ray Enabled System");
	        productNameMap.put(product02.getProductId(), product02);
	        
	        ProductName product03 = new ProductName();
	        product03.setProductId(16696652);
	        product03.setProductName("The Big Lebowski (Blu-ray) (Widescreen)");
	        product03.setProductDescription("Wide Screen Blu-Ray Enabled System");
	        productNameMap.put(product03.getProductId(), product03);
	        
	        ProductName product04 = new ProductName();
	        product04.setProductId(16752456);
	        product04.setProductName("The Big Lebowski (Blu-ray) (Widescreen)");
	        product04.setProductDescription("Wide Screen Blu-Ray Enabled System");
	        productNameMap.put(product04.getProductId(), product04);
	        
	        ProductName product05 = new ProductName();
	        product05.setProductId(15643793);
	        product05.setProductName("The Big Lebowski (Blu-ray) (Widescreen)");
	        product05.setProductDescription("Wide Screen Blu-Ray Enabled System");
	        productNameMap.put(product05.getProductId(), product05);
	    }

	    public ProductName findProductName(Integer id) {
	        ProductName productName = null;
	        // Attempt to find the product in our Product map
	        productName = productNameMap.get(id);
	        return productName;
	    }
}
