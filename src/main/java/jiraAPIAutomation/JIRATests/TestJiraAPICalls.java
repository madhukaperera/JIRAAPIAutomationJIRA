package jiraAPIAutomation.JIRATests;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import utils.MsgPayloads;
import utils.ReadPropertyValues;
import utils.ReusableFunc;

public class TestJiraAPICalls {

	public static void main(String[] args) throws IOException, Throwable {
		
		ReadPropertyValues propVal = new ReadPropertyValues();
		
		
		//Get the base URI
		RestAssured.baseURI = propVal.getPropValues("jira_base_uri");
		
		//Implement a session filter
		SessionFilter jiraSessionFilter = new SessionFilter();

		//Login to JIRA
		String loginResponse = given().log().all()
				.header("Content-Type", "application/json").body(MsgPayloads.LoginJIRA())
				.when()
				.filter(jiraSessionFilter)
				.post(propVal.getPropValues("api_login_resource")).then().log().all().extract().response().asString();
		
		//Create Issue
		String createIssueResponse = given().log().all()
				.header("Content-Type", "application/json").body(MsgPayloads.CreateJIRAIssue())
				.when()
				.filter(jiraSessionFilter)
				.post(propVal.getPropValues("api_create_issue_resource")).then().log().all().extract().response().asString();
		
		String issueID = ReusableFunc.RawToJson(createIssueResponse).getString("id");
		String issueKey = ReusableFunc.RawToJson(createIssueResponse).getString("key");
		
		System.out.print(issueID+"ticket created");
		System.out.print(issueKey+"ticket created");
		
		
		//Add a comment to an existing issue
		String addCommentResponse = given().log().all().pathParam("issueID", issueID).header("Content-Type", "application/json").body(MsgPayloads.AddIssueComment("Here goes the first comment of the issue"))
				.when()
				.filter(jiraSessionFilter)
				.post(propVal.getPropValues("api_add_comment_resource"))
				.then().log().all()
				.assertThat()
				.statusCode(201).extract().response().asString();
		String commentID = ReusableFunc.RawToJson(addCommentResponse).getString("id");
		
		Thread.sleep(2000);
		
		//Update a comment in an issue
		String updateCommentResponse = given().log().all().pathParam("issueKey", issueKey).pathParam("commentID", commentID).header("Content-Type", "application/json").body(MsgPayloads.UpdateComment("Herewith I am updating my second comment of the issue"))
		.when()
		.filter(jiraSessionFilter)
		.put(propVal.getPropValues("api_update_comment_resource"))
		.then().log().all().extract().response().asString();
		
		

	}

}
