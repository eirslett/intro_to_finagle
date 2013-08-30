namespace java javaexample
namespace scala scalaexample

struct Recipe {
    string name;
    list<string> ingredients;
}
service RecipeService {
    Recipe getRecipe(i32 recipeId);
    list<i32> getRecipeIds();
}