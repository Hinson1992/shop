package com.netbook.bigmal;


public abstract class UserdaoFactory {
	public static Userdao getadminlogindao()
	{
		return new Userdaoimpl();
	}
		
}
