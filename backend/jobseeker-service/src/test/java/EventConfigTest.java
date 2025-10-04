import com.dreamjob.event.EventConfigProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventConfigTest {

    @Autowired
    private EventConfigProperties props;

    @Test
    void testBinding() {
        Assertions.assertThat(props).isNotNull();
        System.out.println("Bound properties: " + props.getJobapplication());
    }
}
