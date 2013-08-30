package javaexample;

import java.util.Arrays;
import java.util.List;

import com.twitter.util.Future;

public final class MockDatabase {
    public Future<String> getRecipeNameById(int id){
        // Wrap the result in a Future to simulate asynchronous database call
        return Future.value("Chocolate Cake");
    }
    public Future<List<String>> getIngredientsById(int id){
        return Future.value(Arrays.asList(
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
        ));
    }
}
