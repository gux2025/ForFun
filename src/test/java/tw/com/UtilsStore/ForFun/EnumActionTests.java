package tw.com.UtilsStore.ForFun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.UtilsStore.ForFun.Beans.EnumAction;

@SpringBootTest
public class EnumActionTests {

    @Test
    public void testEnum(){
        EnumAction.UP.execute();
    }
}
