/**
 * Tester for Chapter 10: ArrayLists and Comparators
 * 
 */
import java.io.*;
import java.nio.file.*;
import java.lang.reflect.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class Ch10Test {
	public static int testsFailed = 0;
	public static Class ch;
	
	public static void main (String[] args) {
		try{
			assert false;
			throw new Exception("Asserts not enabled");
		}catch(AssertionError ae){
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		
		String className = "Ch10";
		try{
			ch = Class.forName(className);
			testDoubleList();
			testMinToFront();
			testStutter();
			testFilterRange();
			testReverseFile();

		}catch(ClassNotFoundException cnfe){
			System.out.println("Could not find class "+className);
			System.exit(-1);
		}
		System.out.println();
		if(testsFailed == 0){
			System.out.println("All tests passed");
		}else{
			System.out.println(testsFailed+ " test(s) failed");
			System.exit(-1);
		}
	}
	public static void assertEquals(Method method, Object expected, Object actual, Object[] params)throws Exception{
		method.invoke(null, params);
		assertEquals(expected, actual);
	}
	
	public static void assertEquals(Method method, Object expected, Object actual)throws Exception{
		Object[] params = new Object[1];
		params[0] = actual;
		assertEquals(method, expected, actual, params);
	}
	public static void assertEquals(Object expected, Object actual){
		assert expected.equals(actual) : "Expected " + expected.toString() + " but was " + actual.toString();
	}
	public static void assertThrows(Method method, String type, Object[]params) throws Exception{
		try{
			method.invoke(null, params);
			throw new Exception("No "+type+" thrown");
		}catch(InvocationTargetException ite){
			String cause = ite.getCause().getClass().getSimpleName();
			if(!cause.equals(type)){
				assert false : "Expected "+type+" but was " + cause;
			}
		}
	}
	public static void assertThrows(Method method, String type, Object param) throws Exception{
		Object[]params = new Object[1];
		params[0] = param;
		assertThrows(method, type, params);
	}
	public static void testDoubleList(){
		String methodName = "doubleList";
		try{
			Method method = ch.getMethod(methodName, ArrayList.class);
			ArrayList<String> actual = new ArrayList<>(List.of("how", "are", "you?"));
			ArrayList<String> expected = new ArrayList<>(List.of("how", "how", "are", "are", "you?", "you?"));
			assertEquals(method, expected, actual);
			
			actual = new ArrayList<>();
			expected = new ArrayList<>();
			assertEquals(method, expected, actual);
					
			actual = new ArrayList<>(List.of(""));
			expected = new ArrayList<>(List.of("", ""));
			assertEquals(method, expected, actual);
			
			actual = null;
			assertThrows(method, "IllegalArgumentException", actual);
			
		}catch(AssertionError ae){
			testsFailed++;
			System.out.println(ae.getMessage());
			System.out.println(methodName + " test failed");
			return;
		}
		catch(Exception e){
			testsFailed++;
			System.out.println(e.getMessage());
			System.out.println(methodName + " test failed");
			return;
		}
		System.out.println(methodName + " test passed");
	}
	
	public static void testMinToFront(){
		String methodName = "minToFront";
		try{
			Method method = ch.getMethod(methodName, ArrayList.class);
			ArrayList<Integer> actual = new ArrayList<>(List.of(3, 8, 92, 4, 2, 17, 9));
			ArrayList<Integer> expected = new ArrayList<>(List.of(2, 3, 8, 92, 4, 17, 9));
			assertEquals(method, expected, actual);
			
			actual = new ArrayList<>();
			assertThrows(method, "IllegalArgumentException", actual);
					
			actual = new ArrayList<>(List.of(0));
			expected = new ArrayList<>(List.of(0));
			assertEquals(method, expected, actual);
			
			actual = null;
			assertThrows(method, "IllegalArgumentException", actual);
			
		}catch(AssertionError ae){
			testsFailed++;
			System.out.println(ae.getMessage());
			System.out.println(methodName + " test failed");
			return;
		}
		catch(Exception e){
			testsFailed++;
			System.out.println(e.getMessage()+" "+ e.getCause());
			System.out.println(methodName + " test failed");
			return;
		}
		System.out.println(methodName + " test passed");
	}
	
	public static void testStutter(){
		String methodName = "stutter";
		try{
			Method method = ch.getMethod(methodName, ArrayList.class, int.class);
			Object[] params = new Object[2];
			ArrayList<String> actual = new ArrayList<>(List.of("how", "are", "you?"));
			ArrayList<String> expected = new ArrayList<>(List.of("how","how","how","how","are","are","are","are","you?","you?","you?","you?"));
			params[0] = actual; params[1] = 4;
			assertEquals(method, expected, actual, params);
					
			actual = new ArrayList<>(List.of(""));
			expected = new ArrayList<>(List.of("","",""));
			params[0] = actual; params[1] = 3;
			assertEquals(method, expected, actual, params);
			
			expected = new ArrayList<>();
			params[1] = 0;
			assertEquals(method, expected, actual, params);
			params[1] = Integer.MIN_VALUE;
			assertEquals(method, expected, actual, params);
			
			params[0] = null;
			assertThrows(method, "IllegalArgumentException", params);
			params[0] = new ArrayList<>(); params[1] = 1;
			assertThrows(method, "IllegalArgumentException", params);
			
			
		}catch(AssertionError ae){
			testsFailed++;
			System.out.println(ae.getMessage());
			System.out.println(methodName + " test failed");
			return;
		}
		catch(Exception e){
			testsFailed++;
			System.out.println(e.getMessage()+" "+ e.getCause());
			System.out.println(methodName + " test failed");
			return;
		}
		System.out.println(methodName + " test passed");
	}
	
	public static void testFilterRange(){
		String methodName = "filterRange";
		try{
			Method method = ch.getMethod(methodName, ArrayList.class, int.class, int.class);
			Object[] params = new Object[3];
			ArrayList<Integer> actual = new ArrayList<>(List.of(4, 7, 9, 2, 7, 7, 5, 3, 5, 1, 7, 8, 6, 7));
			ArrayList<Integer> expected = new ArrayList<>(List.of(4, 9, 2, 3, 1, 8));
			params[0] = actual; params[1] = 5; params[2] = 7;
			assertEquals(method, expected, actual, params);
					
			actual = new ArrayList<>(List.of(42));
			expected = new ArrayList<>(List.of(42));
			params[0] = actual; params[1] = 3; params[2] = 5;
			assertEquals(method, expected, actual, params);
			
			expected = new ArrayList<>();
			actual = new ArrayList<>();
			params[0] = actual; params[1] = Integer.MIN_VALUE; params[2] = Integer.MAX_VALUE;
			assertEquals(method, expected, actual, params);
			
			
			actual = new ArrayList<>(List.of(0,1,2,3,4,5,6,7,8,9));
			params[0] = actual;
			assertEquals(method, expected, actual, params);
			
			params[0] = null;
			assertThrows(method, "IllegalArgumentException", params);
			params[0] = new ArrayList<>(); params[1] = Integer.MAX_VALUE; params[2] = Integer.MIN_VALUE;
			assertThrows(method, "IllegalArgumentException", params);
			
			
		}catch(AssertionError ae){
			testsFailed++;
			System.out.println(ae.getMessage());
			System.out.println(methodName + " test failed");
			return;
		}
		catch(Exception e){
			testsFailed++;
			System.out.println(e.getMessage()+" "+ e.getCause());
			System.out.println(methodName + " test failed");
			return;
		}
		System.out.println(methodName + " test passed");
	}
	
	public static void testReverseFile(){
		String methodName = "reverseFile";
		try{
			File lorem = new File("lorem.txt");
			Method method = ch.getMethod(methodName, File.class);
			Object[] params = new Object[1];
			params[0] = lorem;
			String expected = merol.replaceAll("\\r|\\n", "").trim();
			String actual = ((String)method.invoke(method, params)).replaceAll("\\r|\\n", "").trim();
			assertEquals(expected, actual);
			params[0] = null;
			assertThrows(method, "IllegalArgumentException", params);
		}catch(NoSuchMethodException nsme){
			System.out.println("Could not find optional method <public static String reverseFile(File)>");
			return;
		}catch(AssertionError ae){
			testsFailed++;
			System.out.println(ae.getMessage());
			System.out.println(methodName + " test failed");
			return;
		}
		catch(Exception e){
			testsFailed++;
			System.out.println(e.getMessage()+" "+ e.getCause());
			System.out.println(methodName + " test failed");
			return;
		}
		System.out.println(methodName + " test passed");
	}
	
	public static String merol = """
lorem. egestas a ante, condimentum egestas Suspendisse augue.
vestibulum rutrum ut, ligula at vulputate lectus, sem Proin tristique. vel
sem vel eleifend Donec sem. eu sagittis facilisis, tristique a consectetur
massa, sapien Mauris quis. congue est interdum tincidunt massa, enim pretium
Praesent interdum. quis nisi non rhoncus Nam posuere. convallis ultricies
Mauris est. quam amet sit Vestibulum eleifend. pharetra pellentesque Donec
mattis. porta ac augue tristique Curabitur feugiat. et odio blandit porta
Donec nec. commodo metus interdum amet sit dolor, dolor aliquet Nullam
diam. blandit vel Maecenas congue. dignissim mauris eget erat ultricies
Ut turpis. fermentum et, nunc placerat consectetur, ex ac Phasellus
nibh. dictum eget, quam ornare auctor, tellus vel Phasellus cursus. eget
elit eleifend congue Nunc vehicula. commodo nulla volutpat non dui, iaculis
varius Sed enim. ac purus nulla gravida feugiat est, tincidunt felis metus
tincidunt, lobortis amet sit mauris varius, Nunc tempor. malesuada semper lectus
imperdiet Sed nunc. eget pellentesque eu, blandit ac vestibulum metus, lacus
Curabitur dictumst. platea habitasse hac In orci. in dui mauris luctus
quis elit, sodales augue dolor ultricies, fermentum id metus varius, In
egestas. fermentum mi eget mi vel Mauris metus. suscipit non Aenean
sodales. consequat elit non nibh eu Donec potenti. Suspendisse quis. placerat
ipsum tincidunt eget massa, euismod vehicula Proin hendrerit. non nulla
ultrices varius Praesent dictum. ut felis imperdiet fringilla Integer
scelerisque. amet sit velit pharetra porta Donec ultrices. amet sit nisl
eleifend lobortis Integer est. varius eget velit, posuere ac Pellentesque
metus. tempus vulputate quis, leo in pulvinar
velit, orci Cras a. gravida ex vestibulum id felis, purus pellentesque
Donec pellentesque. lacus rhoncus in malesuada, tortor sed lacus faucibus
Vivamus dui. elementum rutrum et, dui et sagittis augue, lacus Quisque
est. in posuere vitae, rhoncus amet sit posuere lorem, risus Suspendisse
consequat. fringilla urna nec libero posuere Nullam felis. amet sit tempor
nec, tristique eget porta turpis, nulla Donec nibh. sed sagittis dignissim,
auctor non fringilla justo, metus Sed congue. iaculis eleifend Curabitur
arcu. in sodales sed, mattis
nec iaculis velit, orci Duis rutrum. mi gravida vel ornare, lorem sed
enim volutpat In tincidunt. pulvinar tincidunt Nunc nec. egestas neque
malesuada ac quam, sagittis sollicitudin Donec est. molestie non Nam libero.
interdum et Nullam risus. non metus nibh tristique ac nibh, auctor magna est
euismod, cursus eu mauris malesuada, Nam eget. finibus mauris tristique
sed nisl, pharetra finibus Vestibulum vehicula. facilisis amet sit purus
accumsan Nullam elit. adipiscing consectetur amet, sit dolor ipsum Lorem
""";
}
//© 2021 GitHub, Inc.
//Terms
//Privacy
//Security
//Status
//Docs
//Contact GitHub
//Pricing
//API
//Training
//Blog
//About
