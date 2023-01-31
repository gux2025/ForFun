package tw.com.UtilsStore.ForFun;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StreamTests {

	@Data
	public static class Student {
		private String name;
		private int age;
		private boolean member;
		private Grade grade;
		public Student() {
		}
		public List<Student> students = Arrays.asList(
				new Student("張初一", 13, false, Grade.JUNIOR_ONE),
				new Student("李初二", 14, false, Grade.JUNIOR_TWO),
				new Student("孫初三", 15, false, Grade.JUNIOR_THREE),
				new Student("王初一", 12, false, Grade.JUNIOR_ONE),
				new Student("錢初二", 14, false, Grade.JUNIOR_TWO),
				new Student("周初三", 16, false, Grade.JUNIOR_THREE));
		public Student(String name, int age, boolean member, Grade grade) {
			this.name = name;
			this.age = age;
			this.member = member;
			this.grade = grade;
		}
		public enum Grade{
			JUNIOR_ONE,JUNIOR_TWO,JUNIOR_THREE
		}
	}

	@Test
	void contextLoads() {
		System.out.println("=== === Test Stream API 啟動囉 === ===");
	}

	@Test
	void testForEach(){
		System.out.println("=== === Test ForEach === ===");
		Student s = new Student();
		var o = new Object();
//		Stream<Student> stream = s.students.stream();
//		System.out.println("< 第一行 >");
//		stream.forEach(System.out::println);
//		System.out.println("< 第二行 >");
//		stream.forEach(System.out::println);
	}
	@Test
	void testFlatMap(){
		// 扁平化: 將許多流連結成一個流
		System.out.println("=== === Test FlatMap === ===");
		List<String> wordList = Arrays.asList("hello","word");
		List<String> result = wordList.stream()
				.map(w -> w.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(result);
	}
	@Test
	void testReduce(){
		System.out.println("=== === Test Reduce === ===");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		// 將一個流中的元素組合起來，第一個參數為起始值
		Integer result = numbers.stream().reduce(5, Integer::sum);
		System.out.println(result);
	}

	@Test
	void testHowManyDistinctWordsInFile() throws IOException {
		System.out.println("=== === Test HowManyDistinct === ===");

		try (Stream<String> stringStream = Files.lines(Paths.get("C:\\Users\\1511019\\Desktop\\觀察者模式.txt"))) {
			long count = stringStream.flatMap(lines -> {
						return Arrays.stream(lines.split(""));
					})
					.distinct()
					.count();
			System.out.println(" == == count == == -> " + count);
		}
	}

	@Test
	void testIterate() throws IOException {
		System.out.println("=== === Test Iterate === ===");
		Stream.iterate(3, n -> n*n).limit(5).forEach(System.out::println);

	}
	@Test
	void testGenerate() throws IOException {
		System.out.println("=== === Test Generate === ===");
		Stream.generate(Math::random).limit(10).forEach(System.out::println);
	}
	@Test
	void testPrimitiveValue() throws IOException {
		// byte、short、int、long 範圍
		System.out.printf("%d ~ %d%n",
				Byte.MIN_VALUE, Byte.MAX_VALUE);
		System.out.printf("%d ~ %d%n",
				Short.MIN_VALUE, Short.MAX_VALUE);
		System.out.printf("%d ~ %d%n",
				Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.printf("%d ~ %d%n",
				Long.MIN_VALUE, Long.MAX_VALUE);
		// float、double 精度範圍
		System.out.printf("%d ~ %d%n",
				Float.MIN_EXPONENT, Float.MAX_EXPONENT);
		System.out.printf("%d ~ %d%n",
				Double.MIN_EXPONENT, Double.MAX_EXPONENT);
		// char 可表示的 Unicode 範圍
		System.out.printf("%h ~ %h%n",
				Character.MIN_VALUE, Character.MAX_VALUE);
		// boolean 的兩個值
		System.out.printf("%b ~ %b%n",
				Boolean.TRUE, Boolean.FALSE);
	}
	@Test
	void testScanner() throws IOException {
		var console = new Scanner(System.in);
		var number = (int) (Math.random() * 10);
		var guess = -1;

		do {
			System.out.print("猜數字（0 ~ 9）:");
			guess = console.nextInt();
		} while(guess != number);

		System.out.println("猜中了...XD");
	}

	@Test
	void testChar() {
		System.out.println("\uD834\uDD1E".charAt(0) == 0X1D11E);
		System.out.println("\uD834\uDD1E".codePointAt(0) == 0X1D11E);
	}

	@Test
	void testOptional() throws IOException {
		Map<String, String> books = new HashMap<>();
		books.put("978-0201633610", "Design patterns : elements of reusable object-oriented software");
		books.put("978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
		books.put("978-0134685991", "Effective Java");

		Optional<String> op = books.entrySet().stream()
				.filter(f -> f.getValue().equals("Effective Java"))
				.map(Map.Entry::getKey)
				.findFirst();

		assertEquals("978-0134685991", op.get());
		System.out.println("Optional isPresent ? >>> " + op.isPresent());
		System.out.println("value ? >>> " + op.get());
	}

}
