package com.DAOServiceLayer;
import com.Beans.UserDetails;
public interface DaoLoginInterface 
{
	boolean verifyUserName(String Username);
	boolean createUser(UserDetails user) ;
	boolean validate(UserDetails user) ;
	UserDetails getUserDetails(UserDetails user);
}
