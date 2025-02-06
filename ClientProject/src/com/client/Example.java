package com.client;

public class Example {

	public static void main(String[] args) {
		String name = "Dinga" ;
		String phoneNo = "98076542" ;
		
		 String query = "select * from signIn_table where username='"+name+"' "+"and mobileNumber='"+phoneNo+"'" ;
		 System.out.println(query);
	}

}
