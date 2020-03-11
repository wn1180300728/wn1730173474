package P3;

import java.util.ArrayList;
import java.util.List;


public class Person {
	private String name;//��������
	private List<Person>  myFriend = new ArrayList<>();
	private static ArrayList<String> allPerson = new ArrayList<>();//���沢���������˵�����
	
	/**
	 * ���췽��
	 * ������������ظ�������
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
