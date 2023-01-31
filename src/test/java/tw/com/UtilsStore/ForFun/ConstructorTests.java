package tw.com.UtilsStore.ForFun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.UtilsStore.ForFun.Beans.Car;

@SpringBootTest
public class ConstructorTests {

    @Test
    void testCar(){
        Car car = new Car("blue", 100);
    }

    @Test
    void testSteelCar(){
        Car car = new Car(100);
    }

}
