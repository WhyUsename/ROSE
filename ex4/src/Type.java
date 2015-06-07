/**
 * the tag the identifier's Type
 * @author Zemin Chen
 *
 */

import java.util.*;

public class Type 
{
	public int id;		//1 is int, 2 is bool, 3 is array, 4 is record
	public String name;
	
	public Type arrayElement;
	public Vector<Type> recordElement;
	
	public Type()
	{
		this.id = 0;
		this.name = null;
		this.arrayElement = null;
		this.recordElement = null;
	}
	
	public Type(int id, String name, Type arrayElement)
	{
		this.id = id;
		this.name = name;
		this.arrayElement = arrayElement;
		this.recordElement = null;
	}
	
	public Type(int id, String name, Vector<Type> recordElement)
	{
		this.id = id;
		this.name = name;
		this.arrayElement = null;
		this.recordElement = recordElement;
	}
	
	public Type(int id, String name)
	{
		this.id = id;
		this.name = name;
		this.arrayElement = null;
		this.recordElement = null;
	}

	public Type(int id)
	{
		this.id = id;
		this.name = null;
		this.arrayElement = null;
		this.recordElement = null;
	}

	public Type(Type t)
	{
		this.id = t.id;
		this.name = t.name;
		this.recordElement = t.recordElement;
		this.arrayElement = t.arrayElement;
	}
}
