package com.target.retailServices.rest.controller;

/**
 * @author Muhammad Shouab
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.target.retailServices.domain.Product;
import com.target.retailServices.domain.ProductName;
import com.target.retailServices.domain.ProductPrice;
import com.target.retailServices.services.ProductService;
import com.target.retailServices.util.exception.RetailServicesException;

@RestController
@RequestMapping("/api/product")
public class RetailServicesRestController {

    @Autowired
    private ProductService productService;
    
    private static final Logger logger = LoggerFactory.getLogger(RetailServicesRestController.class);
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    
    @RequestMapping(method=RequestMethod.POST, value = "/price/save")
    public ResponseEntity<String> save(@RequestBody ProductPrice price) {
    	try {
			productService.saveProductPrice(price);
		} catch (Exception e) {
			logger.error("Error Saving Product Price Information", e);
			throw new RetailServicesException("Error Saving Product Price Information"+e);
		}
        return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.GET, path="{id}")
    @ResponseBody
    public Product getProduct(@PathVariable Integer id) throws Exception {
        try {
        	if(id !=null)
        	{
        		return productService.getProduct(id);
        	}
        	else
        	{
        		throw new RetailServicesException("Error fetching the Product Details - Invalid Data Supplied");
        	}
		} catch (Exception e) {
			logger.error("Error fetching the Product Details", e);
			throw new RetailServicesException("Error fetching the Product Details"+e);
		}
    }

    @RequestMapping(method=RequestMethod.PUT, value="/price/modify/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody ProductPrice price) {
        try {
        	if(id != null)
        	{
        		price.setProductID(id);
        		productService.updateProductPrice(price);
        	}
        	else
        	{
        		throw new RetailServicesException("Error while updating the Product Price Info - BAD Data Supplied");
        	}
		} catch (Exception e) {
			logger.error("Error while updating the PRoduct Price Info ",e);
			throw new RetailServicesException("Error while updating the Product Price Info ",e);
		}
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    @RequestMapping(method=RequestMethod.GET, path="/name/{id}")
    @ResponseBody
    public ProductName getProductName(@PathVariable Integer id) throws Exception {
        try {
        	if(id !=null)
        	{
        		return productService.getProductNameFromRepository(id);
        	}
        	else
        	{
        		throw new RetailServicesException("Error while loading Product Name Ifnormation - Invalid Data Supplied");
        	}
		} catch (Exception e) {
			logger.error("Error while loading Product Name Information ",e);
			throw new RetailServicesException("Error while loading Product Name Ifnormation ",e);
		}
    }
}
