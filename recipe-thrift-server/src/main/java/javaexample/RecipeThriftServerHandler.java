package javaexample;

import java.util.Arrays;
import java.util.List;

import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;
import scala.Tuple2;

public final class RecipeThriftServerHandler implements RecipeService.ServiceIface {

    private final MockDatabase database;

    public RecipeThriftServerHandler(){
        database = new MockDatabase();
    }

    @Override
    public Future<Recipe> getRecipe(int recipeId) {
        Future<String> nameFuture = database.getRecipeNameById(recipeId);
        Future<List<String>> ingredientsFuture = database.getIngredientsById(recipeId);

        Future<Recipe> recipe = nameFuture.join(ingredientsFuture).transformedBy(new FutureTransformer<Tuple2<String, List<String>>, Recipe>() {
            public Recipe map(Tuple2<String, List<String>> futures) {
                String name = futures._1();
                List<String> ingredients = futures._2();
                Recipe recipe = new Recipe(name, ingredients);
                return recipe;
            }
        });

        return recipe;
    }

    @Override
    public Future<List<Integer>> getRecipeIds() {
        return Future.value(Arrays.asList(0,1,2,3));
    }
}