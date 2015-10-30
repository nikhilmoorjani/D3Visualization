package myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 







import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class user extends HttpServlet 
{
  private static final long serialVersionUID = 1L;
  List<DocumentFormat> documents = new ArrayList<DocumentFormat>();
  public user()
  {
    super();
  }
 
//  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//  {
////    StringBuffer sb = new StringBuffer();
////    
////    try 
////    {
////      BufferedReader reader = request.getReader();
////      String line = null;
////      while ((line = reader.readLine()) != null)
////      {
////        sb.append(line);
////      }
////    } catch (Exception e) { e.printStackTrace(); }
//// 
//    StringBuffer sb = new StringBuffer();
////	File file = new File(getServletContext().getRealPath("/WEB-INF/classes/inputjson.txt"));
////	Scanner sc = new Scanner(file);
////	try{
////		while(sc.hasNextLine()){
////			sb.append(sc.nextLine());
////		}
////	}catch(Exception e){
////		e.printStackTrace();
////	}
//    System.out.println(System.getProperty("user.dir"));
//	
//    JSONParser parser = new JSONParser();
//    JSONObject joUser = null;
////    try
////    {
////      joUser = (JSONObject) parser.parse(sb.toString());
////    } catch (ParseException e) { e.printStackTrace(); }
//// 
////    String user = (String) joUser.get("name");
//// 
////    response.setContentType("text/html");
////    PrintWriter out = response.getWriter();
////    out.write("A new user " + user + " has been created.");
////    out.flush();
////    out.close();
//  }
  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		PrintWriter out = response.getWriter();
//		out.println("Hello wold");
		
		StringBuffer sb = new StringBuffer();
		Map<String, Integer> categoryMap = new HashMap<String, Integer>();
		Map<String, Integer> topicMap = new HashMap<String, Integer>();
		Map<String, Integer> locationMap = new HashMap<String, Integer>();
		Map<String, Integer> dateMap = new  HashMap<String, Integer>();
//		File file = new File("inputjson.txt");
//		Scanner sc = new Scanner(file);
//		try{
//			while(sc.hasNextLine()){
//				sb.append(sc.nextLine());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		//System.out.println(System.getProperty("user.dir"));
		try 
	    {
	      BufferedReader reader = request.getReader();
	      String line = null;
	      while ((line = reader.readLine()) != null)
	      {
	        sb.append(line);
	      }
	    } catch (Exception e) { e.printStackTrace(); }
	 
		JSONParser parser = new JSONParser();
	    JSONObject joUser = null;
	    try
	    {
	      joUser = (JSONObject) parser.parse(sb.toString());
	    } catch (ParseException e) { e.printStackTrace(); }
	 
	    joUser = (JSONObject)joUser.get("response");
	   // System.out.println(joUser.toString());
	    JSONArray arrayObj = (JSONArray)joUser.get("docs");
	    System.out.println("array size is: " + arrayObj.size()); 
	    for(int i=0; i<arrayObj.size();i++){
	    	String id="";
	    	String url="";
	    	String date = "";
	    	String title = "";
	    	String category = "";
	    	String topic = "";
	    	String location = "";
	    	String longitude = "";
	    	String latitude = "";
	    	
	    	DocumentFormat tempDoc = new DocumentFormat();
	    	JSONObject tempObj = (JSONObject) arrayObj.get(i);
	    	tempDoc.id = tempObj.get("id")!=null?tempObj.get("id").toString():"";
	    	tempDoc.docUrl = tempObj.get("url")!=null?tempObj.get("url").toString():"";
	    	category = tempObj.get("category")!=null?tempObj.get("category").toString():"";
	    	tempDoc.title = tempObj.get("title")!=null?tempObj.get("title").toString():"";
	    	topic = tempObj.get("topics")!=null?tempObj.get("topics").toString():"";
	    	tempDoc.date = tempObj.get("date")!=null?tempObj.get("date").toString():"";
	    	
	    
	    	location = tempObj.get("location")!=null?tempObj.get("location").toString():"";
	    	tempDoc.latitude = tempObj.get("latitude")!=null?tempObj.get("latitude").toString():"";
	    	tempDoc.longitude = tempObj.get("longitude")!=null?tempObj.get("longitude").toString():"";
	    	System.out.println(tempDoc.latitude);
	    	String[] categories = category.split(",");
	    	String [] topics = topic.split(",");
	    	String [] locations = location.split(",");
	    	
	    	
	    	
	    	if(tempDoc.date!=""){
	    		if(dateMap.containsKey(tempDoc.date)){
	    			dateMap.put(tempDoc.date, dateMap.get(tempDoc.date)+1);
	    		}else{
	    			dateMap.put(tempDoc.date, 1);
	    		}
	    	}
	    	
	    	tempDoc.category = new ArrayList<String>();
	    	if(categories.length!=0){
	    		if(!(categories.length==1 && categories[0].equalsIgnoreCase("others"))){
			    	for(String cat :  categories){
			    		if(cat=="")
			    			continue;
			    		tempDoc.category.add(cat);
			    		if(categoryMap.containsKey(cat.toLowerCase())){
			    			categoryMap.put(cat.toLowerCase(), categoryMap.get(cat.toLowerCase())+1);
			    		}else{
			    			categoryMap.put(cat.toLowerCase(), 1);
			    		}
			    	}
	    		}
	    	}
	    	tempDoc.topics = new ArrayList<String>();
	    	if(topics.length !=0){
	    		if(!(topics.length==1 && topics[0].equalsIgnoreCase("others"))){
			    	for(String top : topics){
			    		if(top=="")
			    			continue;
			    		tempDoc.topics.add(top);
			    		if(topicMap.containsKey(top.toLowerCase())){
			    			topicMap.put(top.toLowerCase(), topicMap.get(top.toLowerCase())+1);
			    		}else{
			    			topicMap.put(top.toLowerCase(), 1);
			    		}
			    	}
	    		}
	    	}
	    	tempDoc.location = new ArrayList<String>();
	    	if(locations.length!=0){
	    		if(!(locations.length==1 && locations[0].equalsIgnoreCase("others"))){
			    	for(String loc : locations){
			    		if(loc=="")
			    			continue;
			    		tempDoc.location.add(loc);
			    		if(locationMap.containsKey(loc.toLowerCase())){
			    			locationMap.put(loc.toLowerCase(), locationMap.get(loc.toLowerCase())+1);
			    		}else{
			    			locationMap.put(loc.toLowerCase(), 1);
			    		}
			    	}
	    		}
	    	}
	    	
	    	documents.add(tempDoc);
	    	int u=0;
	    }
	    
	    //build JSON in proper format.
	    JSONObject calendarView = new JSONObject();
	    JSONArray dateValues = new JSONArray();
	    for(String date : dateMap.keySet()){
	    	JSONArray dateItem = new JSONArray();
	    	dateItem.add(date);
	    	dateItem.add(dateMap.get(date).toString());
	    	dateValues.add(dateItem);
	    }
	    
	    calendarView.put("data", dateValues);
	    
	    
	    //build flare for both bubblechart, dendogram and circlepacking
	    JSONObject flare = new JSONObject();
	    flare.put("name", "flare");
	    
	    JSONArray flareChildren = new JSONArray();
	    flare.put("children", flareChildren);
	    
	    JSONObject firstChild = new JSONObject();
	    firstChild.put("name", "categories");
	    
	    JSONArray firstChildChildren = new JSONArray();
	    firstChild.put("children", firstChildChildren);
	    
	    JSONObject secondChild = new JSONObject();
	    secondChild.put("name", "topics");
	    
	    JSONArray secondChildChildren = new JSONArray();
	    secondChild.put("children", secondChildChildren);
	    
	    flareChildren.add(firstChild);
	    flareChildren.add(secondChild);
	    
	    
	    for(String category : categoryMap.keySet()){
	    	JSONObject categoryItem = new JSONObject();
	    	categoryItem.put("name", category);
	    	categoryItem.put("size", categoryMap.get(category));
	    	firstChildChildren.add(categoryItem);
	    }
	    
	    for(String topic : topicMap.keySet()){
	    	JSONObject topicItem = new JSONObject();
	    	topicItem.put("name", topic);
	    	topicItem.put("size", topicMap.get(topic));
	    	secondChildChildren.add(topicItem);
	    }
	    
	    
	    //build wordcloud
	    JSONArray wordCloudTopics = new JSONArray();
	    for(String topic : topicMap.keySet())
	    	wordCloudTopics.add(topic);
	    
	    JSONArray wordCloudCategories = new JSONArray();
	    for(String category: categoryMap.keySet())
	    	wordCloudCategories.add(category);
	    
	    JSONArray wordCloudLocations = new JSONArray();
	    for(String location : locationMap.keySet())
	    	wordCloudLocations.add(location);
	    
	    //build datamaps
	    JSONArray dataMaps = new JSONArray();
	    
	    for(DocumentFormat document : documents){
	    	if(document.latitude!="" && document.longitude!=""){
	    		JSONObject dataMap = new JSONObject();
	    		dataMap.put("name", document.id);
	    		dataMap.put("latitude", document.latitude);
	    		dataMap.put("longitude", document.longitude);
	    		dataMap.put("radius", "5");
	    		dataMap.put("fillKey", "gt50");
	    		dataMaps.add(dataMap);
	    	}
	    }
	    
	    
	    JSONObject finalJson = new JSONObject();
	    JSONArray formats = new JSONArray();
	    finalJson.put("result", formats);
	    
	    JSONObject bubbleChartObj = new JSONObject();
	    bubbleChartObj.put("bubbleChart", flare);
	    formats.add(bubbleChartObj);
	    
	    JSONObject calendarViewObj = new JSONObject();
	    calendarViewObj.put("calendarView", calendarView);
	    formats.add(calendarViewObj);
	    
	    JSONObject circlePackingObj = new JSONObject();
	    circlePackingObj.put("circlePacking", flare);
	    formats.add(circlePackingObj);
	    
	    JSONObject datamapsObj = new JSONObject();
	    datamapsObj.put("dataMaps", dataMaps);
	    formats.add(datamapsObj);
	    
	    JSONObject dendogramObj = new JSONObject();
	    dendogramObj.put("dendogram", flare);
	    formats.add(dendogramObj);
	    
	    
	    JSONObject wordCloudTopicsObj = new JSONObject();
	    wordCloudTopicsObj.put("wordCloudTopics", wordCloudTopics);
	    formats.add(wordCloudTopicsObj);
	    
	    JSONObject wordCloudCategoriesObj = new JSONObject();
	    wordCloudCategoriesObj.put("wordCloudCategories", wordCloudCategories);
	    formats.add(wordCloudCategoriesObj);
	    
	    JSONObject wordCloudLocationObj = new JSONObject();
	    wordCloudLocationObj.put("wordCloudLocations", wordCloudLocations);
	    formats.add(wordCloudLocationObj);
	    
	    
	    response.setContentType("application/json");
	    
	    
	    PrintWriter out = response.getWriter();
	  //  out.write(finalJson.toJSONString());
	    out.write(finalJson.toString());
	    out.flush();
	    out.close();
	}
}


class DocumentFormat{
	String id;
	String docUrl;
	ArrayList<String> category;
	ArrayList<String> topics;
	ArrayList<String> location;
	String date;
	String longitude;
	String latitude;
	String title;
}
