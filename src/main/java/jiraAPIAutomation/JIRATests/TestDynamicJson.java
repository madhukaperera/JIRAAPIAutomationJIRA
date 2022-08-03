package jiraAPIAutomation.JIRATests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.MsgPayloads;
import utils.ReusableFunc;
import io.restassured.path.json.JsonPath;


public class TestDynamicJson {
	
	
	@Test(dataProvider = "isbn_aisle")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").
		body(MsgPayloads.AddBook(isbn, aisle)).
		when().
		post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js2 = ReusableFunc.RawToJson(response); 
		String id = js2.get("ID");
		System.out.println(id);
	}

	@DataProvider(name="isbn_aisle")
	public Object[][] getData()
	{	
		//Multi-dimensional array - collection of arrays
		return new Object[][] {{"isbn223","q"}, {"isbn3","12213"}, {"isbn86","4577"}};
	}
	
		
}