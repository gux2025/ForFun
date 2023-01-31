package tw.com.UtilsStore.ForFun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;
import java.util.List;

@SpringBootTest
public class ReflectionTests {

    @Test
    public void testThreadReflection(){
        // 方法一
        System.out.println("Thread.class.toString: " + Thread.class.toString());

        // 方法二
        try {
            Class<?> clz = Class.forName("java.lang.Thread");
            System.out.println("Class.forName: " + clz.toString());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 方法三
        Thread t = new Thread();
        Class<?> clzz = t.getClass();
        System.out.println("Thread.getClass: " + clzz.toString());
    }


    @Test
    public void testConstructor(){
        Class<?> c2 = Constructor_2.class;
        Constructor<?>[] cons = c2.getDeclaredConstructors();
        //"4: "
        System.out.println("Construct count: " + cons.length);
        try {
            //"1: "
            cons[0].setAccessible(true);
            cons[1].setAccessible(true);
            //"2: "
//            cons[0].newInstance();
            //"3: "
            cons[1].newInstance(new Object[] {1});
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    class Constructor_2 {
        Constructor_2() {
            System.out.println("執行 Constructor_2 建構函數");
        }

        private Constructor_2(int a) {
            System.out.println("執行 Constructor_2 建構函數, a = " + a);
        }
    }

    @Test
    public void testMethod(){
        MyMethod my = new MyMethod();

        Class<?> clz = my.getClass();

        Method[] ms = clz.getMethods();
        System.out.println("clz.getMethods(): " + ms.length);
        for(Method m : ms) {
            System.out.println("Method name: " + m.getName());
        }

        System.out.println();

        Method[] dms = clz.getDeclaredMethods();
        System.out.println("clz.getDeclaredMethods(): " + dms.length);
        for(Method m : dms) {
            System.out.println("Method name: " + m.getName());
        }
    }

    @Test
    public void testInvoke(){
        MyMethod my = new MyMethod();

        Class<?> clz = my.getClass();

        try {
            //"1. "
            Method ms = clz.getMethod("print");
            System.out.println("1.----------------------------");
            ms.invoke(my);

            //"2. "
            Method dms = clz.getDeclaredMethod("setA", new Class[] {int.class});
            System.out.println("2.----------------------------");
            dms.invoke(my, new Object[] {10});
            ms.invoke(my);

            //"3. "
            Method dms2 = clz.getDeclaredMethod("setA10", new Class[] {int.class});
            dms2.setAccessible(true);
            System.out.println("3.----------------------------");
            dms2.invoke(my, new Object[] {10});
            ms.invoke(my);

            //"4. "
            Method dms3 = clz.getDeclaredMethod("setB", new Class[] {int.class});
            dms3.setAccessible(true);
            System.out.println("4.----------------------------");
            dms3.invoke(my, new Object[] {1000});
            ms.invoke(my);

            //"5. "
            Method dms4 = MyMethod.class.getDeclaredMethod("setB", new Class[] {int.class});
            dms4.setAccessible(true);
            System.out.println("5.----------------------------");
            dms3.invoke(my, new Object[] {1111});
            ms.invoke(my);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyMethod {
        private int a = 0;
        private static int b = 0;

        public void print() {
            System.out.println("a is " + a);
            System.out.println("b is " + b);
        }

        public void setA(int a) {
            this.a = a;
        }

        private void setA10(int a) {
            this.a = a + 10;
        }

        private static void setB(int b) {
            MyMethod.b = b;
        }
    }

    @Test
    public void testField(){
        MyField m = new MyField();

        Class<?> clz = m.getClass();

        try {
            //"1. "
            Field fa = MyField.class.getDeclaredField("a");
            System.out.println("Instances, access Field a: " + fa.get(m));
            fa.set(m, 111);
            System.out.println("after change a: " + m.a);

            //"2. "
            Field fb = clz.getDeclaredField("b");
            fb.setAccessible(true);
            System.out.println("Instances, access Field a: " + fb.getInt(m));
            fb.setInt(m, 222);
            System.out.println("after change b: " + m.getB());

            //"3. "
            Field fc = clz.getDeclaredField("c");
            fc.setAccessible(true);
            System.out.println("Instances, access Field a: " + fc.getInt(m));
            fc.setInt(m, 333);
            System.out.println("after change c: " + m.getC());

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //"4. "
            Field fcc = MyField.class.getDeclaredField("c");
            fcc.setAccessible(true);
            System.out.println("No Instances, get static Field c: "
                    + fcc.getInt(MyField.class));
            fcc.setInt(MyField.class, 33333);
            System.out.println("after change c: " + fcc.getInt(MyField.class));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot access non instances object");
        }
    }

    class MyField {
        public int a = 10;
        private int b = 20;
        private static int c = 30;

        int getB() {
            return b;
        }

        int getC() {
            return c;
        }
    }

    @Test
    public void testAnnotation(){
        Class<?> clz = reflectionAnnotation.class;

        if(clz.isAnnotation()) {
            System.out.println("This class isAnnotation");
        }

        if(clz.isAnnotationPresent(ReAnTest_2.class)) {
            System.out.println("This class Annotation by ReAnTest_2");
        } else {
            System.out.println("This class Annotation \"not\" ReAnTest_2");
        }

        if(clz.isAnnotationPresent(ReAnTest_1.class)) {
            System.out.println("This class Annotation by ReAnTest_1");

            //"3. "
            ReAnTest_1 r = clz.getAnnotation(ReAnTest_1.class); //"3. "
            System.out.println("Age: " + r.age());
            System.out.println("Name: " + r.name());
        } else {
            System.out.println("This class Annotation \"not\" ReAnTest_1");
        }
    }

    @ReAnTest_1(age = 20, name = "Pan")
    @ReAnTest_2(age = 21, name = "Pana")
    public class reflectionAnnotation {

    }

    //"1. "
    @Retention(RetentionPolicy.RUNTIME)
    @interface ReAnTest_1 {
        int age() default 18;
        String name() default "Alien";
    }

    //"2. "
    @Retention(RetentionPolicy.CLASS)
    @interface ReAnTest_2 {
        int age() default 18;
        String name() default "Alien";
    }

    @Test
    public void testGenericArrayType() throws NoSuchFieldException {
        Field ff = ArrayType.class.getDeclaredField("lists");

        GenericArrayType genericType = (GenericArrayType) ff.getGenericType();
        System.out.println("getGenericComponentType: " + genericType.getGenericComponentType());

        // 1. 先獲取泛型實體
        Field aa = ArrayType.class.getDeclaredField("a");
        Field bb = ArrayType.class.getDeclaredField("b");

        ParameterizedType pa = (ParameterizedType) aa.getGenericType();
        ParameterizedType pb = (ParameterizedType) bb.getGenericType();

        System.out.println("pa: " + pa.getTypeName() + ", getRawType" + pa.getRawType());
        System.out.println("pb: " + pb.getTypeName() + ", getRawType" + pb.getRawType());

        // 2. 從泛型中拿到通配符
        WildcardType wTypeA = (WildcardType) pa.getActualTypeArguments()[0];	// 可能有多個上下限
        WildcardType wTypeB = (WildcardType) pb.getActualTypeArguments()[0];

        System.out.println(wTypeA.getUpperBounds()[0]);// 可能有多個上下限
        System.out.println(wTypeB.getLowerBounds()[0]);// 可能有多個上下限
    }

    public class ArrayType {

        List<String>[] lists;

        List<? extends Number> a;	// 上界
        List<? super String> b;

    }

}
