/**
 * 
 */
package com.target.retailServices.repositories;

import org.springframework.data.repository.CrudRepository;
import com.target.retailServices.domain.ProductPrice;

/**
 * @author Muhammad Shouab
 *
 */
public interface ProductPriceRepository extends CrudRepository<ProductPrice, Integer>{
	
	@Override
	public ProductPrice findOne(Integer id);

	

}
