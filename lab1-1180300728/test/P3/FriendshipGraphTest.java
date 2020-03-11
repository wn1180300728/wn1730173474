package P3;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.TestClass;

public class FriendshipGraphTest {
	
	


	@Test
	public void testaddVertex(){
		Person z = new Person("Z");
		Person x = new Person("X");
		Person v = new Person("V");
	    FriendshipGraph graph=new FriendshipGraph();
		graph.addVertex(z);
		graph.addVertex(x);
		graph.addVertex(v);
		
		
		assertTrue(graph.person.contains(z));
		assertTrue(graph.person.contains(x));
		assertTrue(graph.person.contains(v));
		
		
		
		
		

	}
	
	@Test
	public void testaddEdge(){
		Person n =new Person("N");
		Person e =new Person("E");
		FriendshipGraph graph=new FriendshipGraph();
		graph.addVertex(n);
		graph.addVertex(e);
		graph.addEdge(n, e);
		graph.addEdge(e, n);
		assertTrue(graph.person.get(1).getAllFriend().get(0).getName()=="N");
		
		
		
		
		
		
	}
	
	
	
	@Test
	public void testgetDistance() {
		FriendshipGraph graph = new FriendshipGraph();
		Person abel = new Person("Abel");
		Person bill = new Person("Bill");
		Person carl = new Person("Carl");
		Person denny = new Person("Denny");
		Person eric = new Person("Eric");
		graph.addVertex(abel);
		graph.addVertex(bill);
		graph.addVertex(carl);
		graph.addVertex(denny);
		graph.addVertex(eric);
		graph.addEdge(abel, bill);
		graph.addEdge(bill, abel);
		graph.addEdge(bill, carl);
		graph.addEdge(carl, bill);
		graph.addEdge(carl, denny);
		graph.addEdge(denny, carl);
		assertEquals("expected distance", 0, graph.getDistance(abel, abel));
		assertEquals("expected distance", 1, graph.getDistance(abel, bill));
		assertEquals("expected distance", 1, graph.getDistance(bill, carl));
		assertEquals("expected distance", 1, graph.getDistance(carl, denny));
		assertEquals("expected distance", 2, graph.getDistance(abel, carl));
		assertEquals("expected distance", 2, graph.getDistance(bill, denny));
		assertEquals("expected distance", 3, graph.getDistance(abel, denny));
		assertEquals("expected distance", -1, graph.getDistance(abel, eric));
		
	}

}
