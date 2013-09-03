package javaexample;

import java.util.Arrays;
import java.util.List;

import com.twitter.util.Future;

public final class MockDatabase {
    public static final String NAME = "Chocolate Cake";
    public static final List<String> INGREDIENTS = Arrays.asList(
            "flour",
            "sugar",
            "cocoa powder",
            "baking powder",
            "soda",
            "eggs",
            "milk",
            "vegetable oil",
            "vanilla extract",
            "water",
            "chocolate",
            "cream"
    );

    public static Future<Recipe> getRecipeById(int id){
        // Wrap the result in a Future to simulate asynchronous database call
        return Future.value(new Recipe(NAME, INGREDIENTS));
    }
}
