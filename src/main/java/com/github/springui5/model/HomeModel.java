package com.github.springui5.model;

import com.github.springui5.domain.Fruit;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model for {@code home.view.js} view. Will be automatically serialized to Json via default {@linkplain
 * org.springframework.http.converter.HttpMessageConverter} configured by {@linkplain
 * org.springframework.web.servlet.config.annotation.EnableWebMvc} annotation on {@linkplain
 * com.github.springui5.conf.WebAppConfigurer} configuration class.
 *
 * @author gushakov
 */
public class HomeModel implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HomeModel.class);

    private List<Fruit> listOfFruit;

    private String error;

    public List<Fruit> getListOfFruit() {
        return listOfFruit;
    }

    public void setListOfFruit(List<Fruit> listOfFruit) {
        this.listOfFruit = listOfFruit;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HomeModel() {
        listOfFruit = new ArrayList<>(Arrays.asList(new Fruit("apple", 1), new Fruit("orange", 2)));
    }

    public HomeModel add(Fruit fruit) {
        // set id, it is 0 after deserializing from Json
        fruit.setId(Fruit.newId());
        listOfFruit.add(fruit);
        return this;
    }

    public HomeModel delete(final long id) {
        CollectionUtils.filter(listOfFruit, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return ((Fruit) object).getId() != id;
            }
        });
        return this;
    }

    public HomeModel update(final Fruit fruit) {
        // find the fruit with the same id
        Fruit oldFruit = (Fruit) CollectionUtils.find(listOfFruit, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return ((Fruit) object).getId() == fruit.getId();
            }
        });
        // update the fruit
        oldFruit.setName(fruit.getName());
        oldFruit.setQuantity(fruit.getQuantity());
        return this;
    }

    public HomeModel storeError(String error) {
        this.error = error;
        return this;
    }

    public HomeModel clearError() {
        this.error = null;
        return this;
    }

    public HomeModel show() {
        logger.debug(Arrays.toString(listOfFruit.toArray()));
        return this;
    }

}
