package com.apitojira;

import org.pojo.Pojo;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class Sample {
	
	@Test
	
	public void RahulShetty() {
		
		
	Response r = new Response();
	
    r.setStatus("");
    r.setScope("");
    r.setPlace_id("");
    r.setReference("");
    r.setId("");
    
	
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	String response = given().log().all().queryParam("key","qaclick123").body(r)
	.when().post(" /maps/api/place/add/json")
	.then().log().all().extract().response().asString();
	
	System.out.println("My first response is:"+response);

}
}