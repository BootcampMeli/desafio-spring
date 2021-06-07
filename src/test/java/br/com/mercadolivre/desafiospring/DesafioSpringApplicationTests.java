package br.com.mercadolivre.desafiospring;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;


@SpringBootTest
class DesafioSpringApplicationTests {

	@Test
	void contextLoads() {
		RequestSpecification requestSpec = new RequestSpecBuilder().build();
		requestSpec.baseUri("http://localhost:8080/");
	}

	@Test
	public void userFollowSalesmanReturn200(){
		when().post("users/1/follow/2").then().statusCode(200);
	}

	@Test
	public void userFollowSalesmanReturn400UserNotASalesman(){
		when().post("users/1/follow/4").then().statusCode(400).body("msg", containsString("Id 4 is not a Salesman"));
	}

	@Test
	public void userUnfollowSalesmanReturn200(){
		when().post("users/1/unfollow/2").then().statusCode(200);
	}

	@Test
	public void userUnollowSalesmanReturn400UserNotASalesman(){
		when().post("users/1/unfollow/4").then().statusCode(400).body("msg", containsString("Id 4 is not a Salesman"));
	}

	@Test
	public void userFollowedList(){
		when().get("users/1/followed/list").then().statusCode(200).body("followed.name", hasItem("eMaria"));
	}

	@Test
	public void userFollowersCount(){
		when().get("users/2/followers/count").then().statusCode(200).body("followersCount", equalTo(1));
	}

	@Test
	public void getPromoPostsList(){
		when().get("products/1/list").then().statusCode(200).body("userName", equalTo("aHenrique"));
	}

	@Test
	public void getPromoPostsCountPromo(){
		when().get("products/1/countPromo").then().statusCode(200).body("promoProductsCount", equalTo(0));
	}

	@Test
	public void userFollowersList(){
		when().get("users/2/followers/list").then().statusCode(200);
	}
}
