package com.target.retailServices.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import com.target.retailServices.domain.Product;
import com.target.retailServices.domain.ProductName;
import com.target.retailServices.domain.ProductPrice;
import com.target.retailServices.repositories.ProductNameRepositroy;
import com.target.retailServices.repositories.ProductPriceRepository;
import com.target.retailServices.util.exception.RetailServicesException;


/**
 * @author Muhammad Shouab
 *
 */
@Service
@PropertySource("classpath:api.properties")
public class ProductService {
	
	@Autowired
	private ProductPriceRepository productPriceRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${prod-name-api-url}")
    private String baseURL;
	
	private static final UriTemplate PRODUCT_REDSKY = new UriTemplate("/api/product/name/{product_id}");
	
	
	public Iterable<ProductPrice> getAllProductPrices() throws Exception{
        return productPriceRepository.findAll();
    }
	
	public ProductPrice updateProductPrice(ProductPrice price) throws Exception{
		if(price != null && price.getProductID() != null)
		{
        ProductPrice pricetoUpdate = productPriceRepository.findOne(price.getProductID());
        if(pricetoUpdate != null)
        {
        	pricetoUpdate.setPrice(price.getPrice());
        	pricetoUpdate.setCurrencyCode(price.getCurrencyCode());
        	productPriceRepository.save(pricetoUpdate);
        }
        else
        {
        	throw new RetailServicesException(
    				"Error while Updating Product Price - No Record Found");
        }
        return pricetoUpdate;
		}
		else
		{
			throw new RetailServicesException(
    				"Error while Updating Product Price - Invalid Data Supplied");
		}
    }
	
	public Integer saveProductPrice(ProductPrice price) throws Exception{
		if(price != null && price.getProductID() != null)
		{
			productPriceRepository.save(price);
			return price.getProductID();
		}
		else
		{
			throw new RetailServicesException(
    				"Error while Persisting Product Price - Invalid Information Supplied");
		}
    }

	
    private ProductPrice getPrice(Integer id) throws Exception{
    	if(id !=null)
    	{
    		return productPriceRepository.findOne(id);
    	}
    	else
    	{
    		throw new RetailServicesException(
    				"Error while Loading ProductName - Invalid Product Identifier Supplied");
    	}
    }
    
    private ProductName getProductName(Integer id) throws Exception
    {
    	if(id != null)
    	{
    		Map<String,String> uriVariables = new HashMap<>();
    		uriVariables.put("product_id", id.toString());
    		return restTemplate.getForObject(baseURL+PRODUCT_REDSKY, ProductName.class, uriVariables);
    	}
    	else
    	{
    		throw new RetailServicesException(
    				"Error while Loading ProductName - Invalid Product Identifier Supplied");
    	}
    }
    
    public Product getProduct(Integer productId) throws Exception
    {
    	if(productId !=null)
    	{
    		Product product = new Product();
    		product.setId(productId);
    		ProductName prodName = this.getProductName(productId);
    		if(prodName != null)
    		{
    			product.setName(prodName.getProductName());
    		}
    		else
    		{
    			throw new RetailServicesException(
        				"Error while loading Product Information - No Record Found");
    		}
    		product.setPrice(this.getPrice(productId));
    		return product;
    	}
    	else
    	{
    		throw new RetailServicesException(
    				"Error while loading Product Information - Invalid Product Identifier Supplied");
    	}
    }
    
    public ProductName getProductNameFromRepository(Integer id) throws Exception
    {
    	if(id != null)
    	{
    		ProductNameRepositroy prdNameRepo = ProductNameRepositroy.getInstance();
    		prdNameRepo.createProductNames();
    		return prdNameRepo.findProductName(id);
    	}
    	else
    	{
    		throw new RetailServicesException(
    				"Error while Loading ProductName Information - Invalid Product Identifier Supplied");
    	}
    }
    
    @Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
}
