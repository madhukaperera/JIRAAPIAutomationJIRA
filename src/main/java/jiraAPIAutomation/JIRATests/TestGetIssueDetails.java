package jiraAPIAutomation.JIRATests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import utils.MsgPayloads;
import utils.ReadPropertyValues;
import utils.ReusableFunc;

public class TestGetIssueDetails {

public static void main(String[] args) throws IOException, Throwable {
		
		ReadPropertyValues propVal = new ReadPropertyValues();
		
		
		//Get the base URI
		RestAssured.baseURI = propVal.getPropValues("jira_base_uri");
		
		//Implement a session filter
		SessionFilter jiraSessionFilter = new SessionFilter();

		//Login to JIRA
		String loginResponse = given().relaxedHTTPSValidation().log().all()
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
				String addCommentResponse = given().log().all().pathParam("issueID", issueID).header("Content-Type", "application/json").body(MsgPayloads.AddIssueComment("Here goes my first comment of the issue"))
				.when()
				.filter(jiraSessionFilter)
				.post(propVal.getPropValues("api_add_comment_resource"))
				.then().log().all()
				.assertThat()
				.statusCode(201).extract().response().asString();
				
				String commentID = ReusableFunc.RawToJson(addCommentResponse).getString("id");
				Thread.sleep(2000);
				
		//Add another comment to an existing issue
		String addCommentResponse2 = given().log().all().pathParam("issueID", issueID).header("Content-Type", "application/json").body(MsgPayloads.AddIssueComment("Here goes the second comment"))
				.when()
				.filter(jiraSessionFilter)
				.post(propVal.getPropValues("api_add_comment_resource"))
				.then().log().all()
				.assertThat()
				.statusCode(201).extract().response().asString();
				
				String commentID2 = ReusableFunc.RawToJson(addCommentResponse2).getString("id");
				
				System.out.print("Print the second comment."+ commentID2);
				
				
		//Update a comment in an issue
				String updateCommentResponse = given().log().all().pathParam("issueKey", issueKey).pathParam("commentID", commentID2).header("Content-Type", "application/json").body(MsgPayloads.UpdateComment("Here I am updating my second  comment of the issue"))
				.when()
				.filter(jiraSessionFilter)
				.put(propVal.getPropValues("api_update_comment_resource"))
				.then().log().all().extract().response().asString();
		
		//Get details from an existing issue, retrieve the comment
		
		String getIssueResponse = given().filter(jiraSessionFilter).pathParam("issueIdOrKey", issueID)
				.queryParam("fields", "comment")		
				.when()
				.get(propVal.getPropValues("api_get_issue_resource"))
				.then().log().all().extract().response().asString();
		
		System.out.println(getIssueResponse);
		
		int countOfComments = ReusableFunc.RawToJson(getIssueResponse).getInt("fields.comment.comments.size");
		
		for(int i =0; i < countOfComments; i++)
		{
			String commentID1Issue =  ReusableFunc.RawToJson(getIssueResponse).get("fields.comment.comments["+i+"].id").toString();
			if(commentID1Issue.equalsIgnoreCase(commentID))
			{
				String msgBodyOfCommentID1 = ReusableFunc.RawToJson(getIssueResponse).getString("fields.comment.comments["+i+"].body").toString();
				System.out.println(msgBodyOfCommentID1);
				Assert.assertEquals(msgBodyOfCommentID1, "Here goes my first comment of the issue");
			}
		}

		
	}

}
