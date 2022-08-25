package com.apitojira;

import org.pojo.Pojo;
import org.pojo.Webautomation;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

public class ApiToJira {
	@Test
public void courseDetails() {
		
		String url = "https://www.googleapis.com/oauth2/v4/token?code=4%2F0AdQt8qjN9MG3F6T3duDUKwzaKAQ1o2xX6xMrbEH1Kw-2e8jsBNIc60Os3XBJ2GLZRWfvcw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=consent&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&client_secret=erZOWM9g3UtwNRj340YYaK_W&redirect_uri=https://rahulshettyacademy.com/getCourse.php&grant_type=authorization_code";
		String[] a = url.split("code=");
		String[] b = a[1].split("&scope");
		String code = b[0];
		
		
		String accesstoken = given().log().all().queryParams("code",code)
		.queryParams("scope","https://www.googleapis.com/auth/userinfo.email")
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code").header("content-type","application/json").urlEncodingEnabled(false)
		.when().post("https://www.googleapis.com/oauth2/v4/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
			JsonPath j = new JsonPath(accesstoken);
			String token = j.get("access_token");
			System.out.println("Access token is :"+token);
		
		Pojo course = given().log().all().queryParam("access_token",token).header("content-type","application/json").expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(Pojo.class);
		System.out.println(course.getLinkedIn());
		
		List<Webautomation> web = course.getCourses().getWebAutomation();
		for(int i = 0;i<web.size();i++) {
			System.out.println("Course title is : "+web.get(i).getCourseTitle());
			System.out.println("Price is :" +web.get(i).getPrice());
		}
	}

}

		
		