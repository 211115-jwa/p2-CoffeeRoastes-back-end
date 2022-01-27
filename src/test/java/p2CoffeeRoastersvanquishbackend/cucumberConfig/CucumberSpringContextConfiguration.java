package p2CoffeeRoastersvanquishbackend.cucumberConfig;
import p2CoffeeRoastersvanquishbackend.cucumberConfig.*;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ch.qos.logback.core.joran.action.AppenderAction;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = AppenderAction.class, loader = SpringBootContextLoader.class)

public class CucumberSpringContextConfiguration {
	
	private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

	 
	  @Before
	  public void setUp() {
	    LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");

}
}