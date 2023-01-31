package tw.com.UtilsStore.ForFun.Beans;

public class Person implements Comparable<Person>
{
    protected int age;
    public Person(int age)
    {
        this.age = age;
    }
    @Override
    public int compareTo(Person other)
    {
        return this.age - other.age;
    }
}