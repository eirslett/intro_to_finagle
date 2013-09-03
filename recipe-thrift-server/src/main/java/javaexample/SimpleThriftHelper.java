package javaexample;

import org.apache.thrift.protocol.TBinaryProtocol;

import java.util.Arrays;

public class SimpleThriftHelper {
    public static RecipeService.Service toService(RecipeService.ServiceIface handler){
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        return new RecipeService.Service(handler, protocolFactory);
    }

    public static Recipe simpleCakeRecipe(){
        return new Recipe("Cake", Arrays.asList("flour", "sugar", "eggs", "chocolate"));
    }
}
