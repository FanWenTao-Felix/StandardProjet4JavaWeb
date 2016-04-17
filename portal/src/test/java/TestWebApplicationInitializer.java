import org.junit.Before;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Properties;

/**
 * Created by kingson.wu on 2015/12/8.
 */
public class TestWebApplicationInitializer {


    protected AnnotationConfigWebApplicationContext springContext;

    @Before
    public void setUp()
    {
        springContext = new AnnotationConfigWebApplicationContext();

        //Properties properties = ConfigProject.getDefaultProperties();
        //springContext.getEnvironment().getPropertySources().addLast(new PropertiesPropertySource("default", properties));

        //springContext.register(abcVisibleApi.class);
        springContext.refresh();

        //abcVisibleApi = springContext.getBean(abcVisibleApi.class);
    }
}
