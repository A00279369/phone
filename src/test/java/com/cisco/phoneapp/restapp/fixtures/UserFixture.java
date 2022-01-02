package com.cisco.phoneapp.restapp.fixtures;


import com.cisco.phoneapp.restapp.entities.Phone;
import com.cisco.phoneapp.restapp.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 
 * Convenience Class with testdata used in various unit tests
 * 
 * @author damian
 *
 */
public class UserFixture {
	   
	   public static String username1;
	   public static String username2;
	   public static String username3;

	   
	   public static List<User> getFindAll(){
		  List<User>all = new ArrayList<>();
		  all.add(getUser1());
		  all.add(getUser2());
		  all.add(getUser3());
		  return all;
	   }

	public static User getUser1 () { return new User(UUID.randomUUID(),"User Name","password1","user@email.com","");}
	public static User getUser2 () { return new User(UUID.randomUUID(),"","password2","","");}
	public static User getUser3 () { return new User(UUID.randomUUID(),"","password3","","");}

	public static Phone getPhone1 (UUID userId) {
	   	return new Phone( UUID.randomUUID(), "phoneName", "phoneModel", "phoneNumber",userId);}


	public static User getValidUser() {
	  return new User(UUID.randomUUID(), "user name", "email@address.com", "password", "");

	  }

	  public static String getJSON(User user) throws Exception {
		  ObjectMapper objectMapper = new ObjectMapper();
		  return objectMapper.writeValueAsString(user);
	  }

//	  public static String getJSON(User user) throws Exception {
//		  ObjectMapper objectMapper = new ObjectMapper();
//		  return objectMapper.writeValueAsString(user);
//	  }

}