package P3;

import java.util.ArrayList;
import java.util.List;


public class Person {
	private String name;//本人名字
	private List<Person>  myFriend = new ArrayList<>();
	private static ArrayList<String> allPerson = new ArrayList<>();//保存并更新所有人的名字
	
	/**
	 * 构造方法
	 * 解决输入名字重复的问题
	 */
	public Person(String name){
		if(!allPerson.contains(name)){
			allPerson.add(name);
			this.name = name;
			
		
		}
		else{
			System.out.println("Name Repeat!");
			System.exit(0);
		}
	}
	
	public String getName(){
		return name;
	}
	public void addFriend(Person someone){
		myFriend.add(someone);
	}
	public List<Person> getAllFriend(){
		return myFriend;
	}
	
	

}
