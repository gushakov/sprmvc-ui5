package com.github.springui5.model;

/**
 * View-model bean for {@code home.view.js}. Wraps an instance of {@linkplain com.github.springui5.model.HomeModel}.
 * Will be retrieved from web application context and autowired to {@linkplain com.github.springui5.web.HomeController}.
 *
 * @author gushakov
 * @see com.github.springui5.conf.WebAppConfigurer
 */
public class HomeViewModel {

    private HomeModel homeModel;

    /**
     * Initializes and returns a new model.
     */
    public HomeModel getNewHomeModel() {
        homeModel = new HomeModel();
        return homeModel;
    }

    /**
     * Returns the model for this view-model.
     */
    public HomeModel getHomeModel() {
        if (homeModel == null) {
            throw new RuntimeException("HomeModel has not been initialized yet.");
        }
        return homeModel;
    }

}
