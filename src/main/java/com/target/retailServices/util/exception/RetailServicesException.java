/**
 * 
 */
package com.target.retailServices.util.exception;

/**
 * @author Muhammad Shouab
 *
 */
public class RetailServicesException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RetailServicesException(String message) {
		super(message);
	}

	public RetailServicesException(Throwable cause) {
		super(cause);
	}
	
	public RetailServicesException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
