package tw.com.UtilsStore.ForFun;

import tw.com.UtilsStore.ForFun.Beans.Student;
import tw.com.UtilsStore.ForFun.Beans.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class GenericsTests {

    @Test
    void testJVM(){
        // 泛型只在編譯階段有效。 看下面的代碼：
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("類型相同");
        }
    }

    @Test
    // 關於<T extends Comparable<? super T>>的解釋
    void testGenerics(){
        List<Person> allPersons = new ArrayList<Person>();
        allPersons.add(new Person(10));
        allPersons.add(new Person(20));

        List<Person> mixedPerson = new ArrayList<Person>();
        mixedPerson.add(new Person(30));
        mixedPerson.add(new Student(40));

        List<Student> allStudent = new ArrayList<Student>();
        allStudent.add(new Student(5));
        allStudent.add(new Student(18));

//        mySort1(allPersons);  // 1
//        mySort1(mixedPerson); // 2
//        mySort1(allStudent);  // 3
//        mySort2(allPersons);  // 4
//        mySort2(mixedPerson); // 5
//        mySort2(allStudent);  // 6
    }

    public static <T extends Comparable<T>> void mySort1(List<T> list)
    {
        Collections.sort(list);
        for(T item: list) System.out.println(item);
    }
    public static <T extends Comparable<? super T>> void mySort2(List<T> list)
    {
        Collections.sort(list);
        for(T item: list) System.out.println(item);
    }


}
