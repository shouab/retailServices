package com.target.retailServices.rest.controller;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.target.retailServices.domain.Product;
import com.target.retailServices.domain.ProductPrice;
import com.target.retailServices.services.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RetailServicesRestController.class, secure = false)
public class RetailServicesControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	Product product = new Product(15643793, new ProductPrice(15643793,500.00,"YEN"), "DVD Player");

	String examplePutJson = "{\"productID\": 16696652,\"price\": 1400.50,\"currencyCode\": \"USD\"}";

	@Test
	public void getProduct() throws Exception {
		Mockito.when(productService.getProduct(Mockito.anyInt())).thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/product/15117729").accept(
					MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":15643793,\"name\":\"DVD Player\",\"price\":{\"price\":500.0,\"currencyCode\":\"YEN\"}}";

		JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
	}

	@Test
	public void updateProductPrice() throws Exception {
		ProductPrice mockprice = new ProductPrice(16696652, 1000.00, "EURO");

		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				productService.updateProductPrice(Mockito.any(ProductPrice.class))).thenReturn(mockprice);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/api/product/price/modify/16696652")
				.accept(MediaType.APPLICATION_JSON).content(examplePutJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

}
