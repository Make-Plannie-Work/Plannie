package MakePlannieWork.Plannie;

import MakePlannieWork.Plannie.controller.GebruikerController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class PlannieApplicationTests {

	@Autowired
	private GebruikerController gebruikerController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(gebruikerController).isNotNull();
	}
}
