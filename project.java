package IBM2.restassured;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class project {
	
	//DataProvider 
	@DataProvider(name="provider")
	public Object[][] data(){
		Object[][] sweetyData=new Object[2][8];
		sweetyData[0][0]="1";
		sweetyData[0][1]="mouni15";
		sweetyData[0][2]="mounika";
		sweetyData[0][3]="dilip";
		sweetyData[0][4]="mounika12@gmail.com";
		sweetyData[0][5]="passs2123";
		sweetyData[0][6]="978789076";
		sweetyData[0][7]="1";
		sweetyData[1][0]="2";
		sweetyData[1][1]="viyan67";
		sweetyData[1][2]="viyan";
		sweetyData[1][3]="ved";
		sweetyData[1][4]="mounika@gmail.com";
		sweetyData[1][5]="123434567";
		sweetyData[1][6]="8220908706";
		sweetyData[1][7]="2";
	
		return sweetyData;
	}
	
	//Create the user using the data provider
	@SuppressWarnings("unchecked")
	@Test(enabled=true,priority=1,dataProvider="provider")
	public void createUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		JSONObject obj =new JSONObject();
		
		obj.put("id",id);
		obj.put("username",un);
		obj.put("firstName",fn);
		obj.put("lastName",ln);
		obj.put("email",email);
		obj.put("password",pass);
		obj.put("phone",ph);
		obj.put("userstatus",us);
		
		
		given().contentType(ContentType.JSON)
		       .body(obj.toJSONString()).
		 when().post("/user")
		.then().statusCode(200).log().all();
		
	}
	
	//Get user data 
	@Test(enabled=true,priority=2)
	public void getUser(){
		
		RestAssured.baseURI="https://petstore.sweety.io/v2"; 
		
		given().get("/user/mouni15").
		then()
		.statusCode(200).log().all();
		
		
		given().get("/user/viyan67").
		then()
		.statusCode(200).log().all();
	}
	
	//Login into the account using the dataProvider
	@Test(enabled=true,priority=3,dataProvider="provider")
	public void LoginUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
       RestAssured.baseURI="https://petstore.sweety.io/v2"; 
		
	
	given().queryParam("username", un)
	.queryParam("password", pass).
	when()
	  .get("/user/login").
	then()
	   .statusCode(200)
	   .log().all();
		
		
	}
	
	//Update the user using the put method
	@SuppressWarnings("unchecked")
	@Test(enabled=true,priority=4)
	public void UpdateUser(){
		 RestAssured.baseURI="https://petstore.sweety.io/v2"; 
		 
		 
		 JSONObject obj=new JSONObject();
			
		 obj.put("id","1");
			obj.put("username","mouni15");
			obj.put("firstName","mounika");
			obj.put("lastName","dilip");
			obj.put("email","mounika12@gmail.com");
			obj.put("password","passs2123");
			obj.put("phone","978789076");
			obj.put("userstatus","1");
			
			given().contentType(ContentType.JSON)
			   .body(obj.toJSONString())
			.when()
			   .put("/user/mouni15")
			   .then()
			   .statusCode(200).log().all();
	}
	
	//delete the user
	@Test(enabled=true,priority=5)
	public void deleteUser(){
		 RestAssured.baseURI="https://petstore.sweety.io/v2"; 
		 
		 given().delete("/user/mouni15").then().statusCode(200).log().all();
		 given().delete("/user/viyan67").then().statusCode(200).log().all();
	}

}
