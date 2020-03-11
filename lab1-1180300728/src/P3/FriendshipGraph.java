package P3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FriendshipGraph {
	public List<Person> person = new ArrayList<Person>();
	
	
	
	public void addVertex(Person somebody){
		person.add(somebody);
		
		
	}
	
	public void addEdge(Person p1,Person p2){
		p1.addFriend(p2);
		
	}
	
	public int getDistance(Person p1,Person p2){
		Queue<Person> queue = new LinkedList<Person>();
		Map<Person,Integer> distance = new HashMap<Person, Integer>();
		queue.offer(p1);
		distance.put(p1, 0);
		while(!queue.isEmpty()){
			Person firstPerson = queue.poll();
			List<Person> adjacencyList = firstPerson.getAllFriend();
			Integer dis = distance.get(firstPerson);
			for(Person s : adjacencyList){
				if(!distance.containsKey(s)){
					distance.put(s, dis+1);
					queue.offer(s);
					if(s == p2)
						return distance.get(p2);
				}
			}
		}
		if(p1.equals(p2)&&p1==p2)
		{
			return 0;
		}
		
		
		
		
		return -1;
	}
	
	
	
	
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ross");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		//should print 1
		System.out.println(graph.getDistance(rachel, ben));
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		//should print -1
		
		
		
	}

}
