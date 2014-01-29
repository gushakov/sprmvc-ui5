package com.github.springui5.conf;

import com.github.springui5.model.HomeViewModel;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web application context configuration using Java annotations.
 *
 * @author gushakov
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.github.springui5.web"})
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * Enable default view ("index.html") mapped under "/".
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Set up the cached resource handling for OpenUI5 runtime served from the webjar in {@code /WEB-INF/lib} directory
     * and local JavaScript files in {@code /resources} directory.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/", "/resources/**")
                .setCachePeriod(31556926);
    }

    /**
     * Session-scoped view-model bean for {@code home.view.js} view persisting in between successive Ajax requests.
     */
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HomeViewModel homeModel() {
        return new HomeViewModel();
    }
}
