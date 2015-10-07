package com.phoenix.storage;

import java.util.ArrayList;
import java.util.List;

import javax.json.*;

import java.sql.*;



public class ChannelManager
{
	Connection c = null;
	Statement stmt=null; 

public Connection createConnection()
{
	    
	    try {
	      Class.forName("org.sqlite.JDBC"); 
	      c = DriverManager.getConnection("jdbc:sqlite:Channel.db");
	      
	      DatabaseMetaData dbm = c.getMetaData();
		   // check if "employee" table is there
		   ResultSet tables = dbm.getTables(null, null, "CHANNEL_ENTRY", null);
		   if (tables.next()) {
		    System.out.println("Table exist");
		   }
		   else {
			   stmt = c.createStatement();
			      String sql = "CREATE TABLE CHANNEL_ENTRY " +
			                   "(CHANNELNO INT PRIMARY KEY     NOT NULL," +
			                   "CHANNELNAME           varchar(30)  " + 
			                   "TIME        varchar(10), " + 
			                   "PROGRAMNAME varchar(30),"+ 
			                   "PROGDESCRIPTION varchar(100),"+
			                   "URL varchar(300),"+
			                   "SUBSCRIPTIONKEY varchar(10))";
			      
			      stmt.executeUpdate(sql);
			      stmt.close();
			      System.out.println("Table CHANNEL_ENTRY IS CREATED ... ");
		   }  
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("Opened database successfully");
	    return c;
	}

	public void closeConnection (){
		try{
			this.c.close();
			System.out.println(" closing db ....");
			
		}
		catch(Exception e)
		{
			System.out.println("Error in closing db ....");
		}
	}
	
	public void addInDb(JsonObject obj){
		int chID=obj.getInt("CHANNELNO");
		String name=obj.getString("CHANNELNAME");
		String time=obj.getString("TIME");
		String Pname=obj.getString("PROGRAMNAME");
		String desc=obj.getString("PROGDESCRIPTION");
		String url=obj.getString("URL");
		String subskey=obj.getString("SUBSCRIPTIONKEY");
		
		
		String sql = "INSERT INTO CHANNEL_ENTRY (CHANNELNO,CHANNELNAME,TIME,PROGRAMNAME,PROGDESCRIPTION,URL,SUBSCRIPTIONKEY) " +
                "VALUES (?,?,?,?,?,? ,?);"; 
		try {
			
			PreparedStatement ps =this.c.prepareStatement(sql);
			ps.setInt(1,chID);
			ps.setString(2, name);
			ps.setString(3, time);
			ps.setString(4, Pname);
			ps.setString(5, desc);
			ps.setString(6, url);
			ps.setString(7, subskey);
			
			
			ps.executeUpdate();
			ps.close();
			
			System.out.println("Inserted into db ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
public void updateInDb(JsonObject obj ){
	
	int chID=obj.getInt("CHANNELNO");
	String name=obj.getString("CHANNELNAME");
	String time=obj.getString("TIME");
	String Pname=obj.getString("PROGRAMNAME");
	String desc=obj.getString("PROGDESCRIPTION");
	String url=obj.getString("URL");
	String subskey=obj.getString("SUBSCRIPTIONKEY");
	
	System.out.println(" prepare1");
	String sql = "UPDATE CHANNEL_ENTRY SET CHANNELNAME=?,TIME=?, PROGRAMNAME=?, PROGDESCRIPTION=?, URL=?,SUBSCRIPTIONKEY=? WHERE CHANNELNO =? " ; 
	try {
		PreparedStatement ps =this.c.prepareStatement(sql);
		System.out.println(" prepare2");
		ps.setString(1, name);
		ps.setString(2, time);
		ps.setString(3, Pname);
		ps.setString(4, desc);
		ps.setString(5, url);
		ps.setString(6, subskey);
		ps.setInt(7, chID);
		System.out.println(" prepare2.11");
		ps.executeUpdate();
		System.out.println(" prepare3");
		System.out.println(" Update is complete ...");
		ps.close();
		
		System.out.println("Inserted into db ");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

		
	}
/*public void readFromDb(){
	String sql = "SELECT * FROM CHANNEL_ENTRY ;";

	try {
		stmt=this.c.createStatement();
		ResultSet rs= stmt.executeQuery(sql);
	      while ( rs.next() ) {
	         int id = rs.getInt("CHANNELNO");
	         String  name = rs.getString("CHANNELNAME");
	         String desc  = rs.getString("PROGDESCRIPTION");
	         String  TIME = rs.getString("TIME");
	         String sub = rs.getString("SUBSCRIPTIONKEY");
	         System.out.println( "ChanelNo = " + id );
	         System.out.println( "CHANNELNAME = " + name );
	         System.out.println( "PROGDESCRIPTION = " + desc );
	         System.out.println( "TIME = " + TIME );
	         System.out.println( "SUBSCRIPTIONKEY = " + sub );
	         System.out.println();
	      }
	      rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	System.out.println("Displayed from  db ");
	
}*/

public JsonObject readFromDbByChannelID(int chid){
	String sql = "SELECT * FROM CHANNEL_ENTRY ;";
	String name,pname,desc,time,sub,url;
	int id;
	JsonObject obj =null;
	try {
		stmt=this.c.createStatement();
		ResultSet rs= stmt.executeQuery(sql);
	      while ( rs.next() ) {
	          id = rs.getInt("CHANNELNO");
	         if(id==chid)
	         {
	           name = rs.getString("CHANNELNAME");
	           pname  = rs.getString("PROGRAMNAME");
	          desc  = rs.getString("PROGDESCRIPTION");
	          url = rs.getString("URL");
	           time = rs.getString("TIME");
	          sub = rs.getString("SUBSCRIPTIONKEY");
	        // System.out.println( "CHANNELNO = " + id );
	        // System.out.println( "CHANNELNAME = " + name );
	        // System.out.println( "PROGDESCRIPTION = " + desc );
	        // System.out.println( "PROGRAMNAME = " + pname);
	        // System.out.println( "TIME = " + time );
	        // System.out.println( "URL = " + url );
	        // System.out.println( "SUBSCRIPTIONKEY = " + sub );
	        // System.out.println();
	          obj =  Json.createObjectBuilder()
	  			   .add("CHANNELNO", chid)
	  			   .add("CHANNELNAME", name)
	  			   .add("TIME", time)
	  			   .add("PROGRAMNAME", desc)
	  			   .add("PROGDESCRIPTION", desc)
	  			 .add("URL", url)
	  			   .add("SUBSCRIPTIONKEY", sub).build();
	         break;
	         }
	      }
	      rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Displayed from  db ");
	return obj;
}


public JsonObject readFromDbByChannelName(String name){
	String sql = "SELECT * FROM CHANNEL_ENTRY ;";
	String chname,pname,desc,time,sub,url;
	int id;
	JsonObject obj =null;
	try {
		stmt=this.c.createStatement();
		ResultSet rs= stmt.executeQuery(sql);
	      while ( rs.next() ) {
	    	  chname = rs.getString("CHANNELNAME");
	          
	         if(chname.equals(name))
	         {
	        	 id = rs.getInt("CHANNELNO");
	           pname  = rs.getString("PROGRAMNAME");
	          desc  = rs.getString("PROGDESCRIPTION");
	          url = rs.getString("URL");
	           time = rs.getString("TIME");
	          sub = rs.getString("SUBSCRIPTIONKEY");
	        // System.out.println( "CHANNELNO = " + id );
	        // System.out.println( "CHANNELNAME = " + name );
	        // System.out.println( "PROGDESCRIPTION = " + desc );
	        // System.out.println( "PROGRAMNAME = " + pname);
	        // System.out.println( "TIME = " + time );
	        // System.out.println( "URL = " + url );
	        // System.out.println( "SUBSCRIPTIONKEY = " + sub );
	        // System.out.println();
	          obj =  Json.createObjectBuilder()
	  			   .add("CHANNELNO", id)
	  			   .add("CHANNELNAME", name)
	  			   .add("TIME", time)
	  			   .add("PROGRAMNAME", desc)
	  			   .add("PROGDESCRIPTION", desc)
	  			 .add("URL", url)
	  			   .add("SUBSCRIPTIONKEY", sub).build();
	         break;
	         }
	      }
	      rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Displayed from  db ");
	return obj;
}
	
	public static void main(String args[]){
	
		ChannelManager ch=new ChannelManager();
		Connection con=ch.createConnection();
		
	JsonObject	obj1 =  Json.createObjectBuilder()
	  			   .add("CHANNELNO", 4)
	  			   .add("CHANNELNAME", "SUBTV")
	  			   .add("TIME", "11:30")
  			   .add("PROGRAMNAME", "TarakMehtakaultachashma")
	  			   .add("PROGDESCRIPTION", "Comedy SHOW")
	  			 .add("URL", "C:\\KBC.mp4")
	  			   .add("SUBSCRIPTIONKEY", "ROMasdasY%&^* ").build();;
		
	//	ch.addInDb(obj1);
		
		JsonObject obj = ch.readFromDbByChannelID(4);
			

           System.out.println(obj.getInt("CHANNELNO"));
        System.out.println(obj.getString("CHANNELNAME"));
        System.out.println(obj.getString("PROGDESCRIPTION"));
        System.out.println(obj.getString("PROGRAMNAME"));
        System.out.println(obj.getString("TIME"));
        System.out.println(obj.getString("URL"));
        System.out.println( obj.getString("SUBSCRIPTIONKEY"));
        
        obj1 =  Json.createObjectBuilder()
	  			   .add("CHANNELNO", 4)
	  			   .add("CHANNELNAME", "KTV")
	  			   .add("TIME", "11:30")
	  			   .add("PROGRAMNAME", "ULTAPULTA")
	  			   .add("PROGDESCRIPTION", "Comedy SHOW")
	  			 .add("URL", "C:\\KBC.mp4")
	  			   .add("SUBSCRIPTIONKEY", "ROMasdasY%&^* ").build();;
        
		
        ch.updateInDb(obj1);
        
         obj = ch.readFromDbByChannelID(4);
         obj = ch.readFromDbByChannelName("KTV");
		

        System.out.println(obj.getInt("CHANNELNO"));
     System.out.println(obj.getString("CHANNELNAME"));
     System.out.println(obj.getString("PROGDESCRIPTION"));
     System.out.println(obj.getString("PROGRAMNAME"));
     System.out.println(obj.getString("TIME"));
     System.out.println(obj.getString("URL"));
     System.out.println( obj.getString("SUBSCRIPTIONKEY"));
        
		ch.closeConnection();
		
		
	}
}