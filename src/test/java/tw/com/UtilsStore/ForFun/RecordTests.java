package tw.com.UtilsStore.ForFun;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class RecordTests {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Person{
        private Integer id;
        private String name;
        private Integer score;
    }

    record Score(String name, int score){}

    @Test
    void testRecord(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"小明", 50));
        personList.add(new Person(2,"小華", 80));
        personList.add(new Person(3,"阿信", 100));

        List<Score> newList = personList.stream()
                .map(x-> new Score(x.getName(),x.getScore()))
                .collect(Collectors.toList());

        newList.forEach(score -> System.out.println(score.toString()));
    }

}
