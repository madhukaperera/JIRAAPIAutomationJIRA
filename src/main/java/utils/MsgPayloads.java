package utils;

public class MsgPayloads {
	
	public static String addPlace()
	{
		return "{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -38.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Frontline house\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shop\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"French-IN\"\n"
				+ "}";
	}
	
	public static String updatePlace(String placeId, String newAddress)
	{
		return "{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}";
	}
	
	public static String coursePrice()
	{
		return "{\n"
				+ "\n"
				+ "\"dashboard\": {\n"
				+ "\n"
				+ "\"purchaseAmount\": 910,\n"
				+ "\n"
				+ "\"website\": \"rahulshettyacademy.com\"\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "\"courses\": [\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"Selenium Python\",\n"
				+ "\n"
				+ "\"price\": 50,\n"
				+ "\n"
				+ "\"copies\": 6\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"Cypress\",\n"
				+ "\n"
				+ "\"price\": 40,\n"
				+ "\n"
				+ "\"copies\": 4\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"RPA\",\n"
				+ "\n"
				+ "\"price\": 45,\n"
				+ "\n"
				+ "\"copies\": 10\n"
				+ "\n"
				+ "}\n"
				+ "\n"
				+ "]\n"
				+ "\n"
				+ "}";
	}

	public static String AddBook(String isbn, String aisle) {
		 String payloadString = "{\n"
					+ "\n"
					+ "\"name\":\" Hudekalawa\",\n"
					+ "\"isbn\":\""+isbn+"\",\n"
					+ "\"aisle\":\""+aisle+"\",\n"
					+ "\"author\":\"Most Ven Seelgaweshi \"\n"
					+ "}\n"
					+ "";
			
			return payloadString;
	}
	
	public static String LoginJIRA()
	{
		return "{ \n"
				+ "    \"username\": \"madhukaperera\", \n"
				+ "    \"password\": \"Srilanka@12345\" \n"
				+ "}";
	}
	
	public static String CreateJIRAIssue()
	{
		return "{\n"
				+ "    \"fields\": {\n"
				+ "        \"project\": {\n"
				+ "            \"key\" : \"REST\"\n"
				+ "        },\n"
				+ "        \"summary\": \"JIRA REST Automation Test bug for update comments\",\n"
				+ "        \"issuetype\": {\n"
				+ "            \"name\": \"Bug\"\n"
				+ "        },\n"
				+ "        \"assignee\": {\n"
				+ "            \"name\": \"madhukaperera\"\n"
				+ "        },\n"
				+ "        \"reporter\": {\n"
				+ "            \"name\": \"madhukaperera\"\n"
				+ "        },\n"
				+ "        \"labels\": [\n"
				+ "            \"bugfix\",\n"
				+ "            \"blitz_test\"\n"
				+ "        ],\n"
				+ "        \"environment\": \"environment\",\n"
				+ "        \"description\": \"description\",\n"
				+ "        \"components\": [\n"
				+ "            {\n"
				+ "                \"id\": \"10000\"\n"
				+ "            }\n"
				+ "        ]\n"
				+ "        \n"
				+ "    }\n"
				+ "}";
	}
	
	public static String AddIssueComment()
	{
		return "{\n"
				+ "    \"body\": \"My First Automated Issue Comment for REST-9\",\n"
				+ "    \"visibility\": {\n"
				+ "        \"type\": \"role\",\n"
				+ "        \"value\": \"Administrators\"\n"
				+ "    }\n"
				+ "}";
	}
	
	public static String UpdateComment()
	{
		return "{\n"
				+ "    \"body\": \"First ever automated comment updated for REST-9 ticket successfully.\",\n"
				+ "    \"visibility\": {\n"
				+ "        \"type\": \"role\",\n"
				+ "        \"value\": \"Administrators\"\n"
				+ "    }\n"
				+ "}";
	}
}

