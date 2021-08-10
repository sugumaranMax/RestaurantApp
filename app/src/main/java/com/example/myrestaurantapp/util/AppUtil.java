package com.example.myrestaurantapp.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.myrestaurantapp.menu.MenuCategory;
import com.example.myrestaurantapp.menu.MenuItems;
import com.example.myrestaurantapp.menu.MenuMainModel;
import com.example.myrestaurantapp.menu.MenuModel;
import com.example.myrestaurantapp.restaurant.RestaurantMainModel;
import com.example.myrestaurantapp.restaurant.RestaurantModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AppUtil {
    public static final String RESTAURANT_JSON = "restaurant";
    public static final String MENU_JSON = "menu";
    public static final String RAW_PATH = "raw";

    private RestaurantMainModel restaurantMainModel;
    private MenuMainModel menuMainModel;
    private final Gson gson;
    private static AppUtil appUtilInstance = null;
    private final MyInterface myInterface;

    private AppUtil(MyInterface myInterface){
        gson = new Gson();
        this.myInterface = myInterface;

    }

    public static AppUtil getInstance(MyInterface myInterface){
        if(appUtilInstance == null){
            appUtilInstance = new AppUtil(myInterface);
        }
        return appUtilInstance;
    }

    public String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getResources().openRawResource(context.getResources().
                    getIdentifier(fileName, RAW_PATH, context.getPackageName()));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

    public <T> Object stringConvertToClass(Context context, String jsonFileName, Type typeOf){
        return gson.fromJson(getJsonFromAssets(context, jsonFileName), typeOf);
    }

    public void initializeData(Context context) {
        restaurantMainModel = (RestaurantMainModel)stringConvertToClass(context, RESTAURANT_JSON,
                RestaurantMainModel.class);
        menuMainModel = (MenuMainModel)stringConvertToClass(context, MENU_JSON,
                MenuMainModel.class);
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Do any code.
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Do any code.
        }

        @Override
        public void afterTextChanged(Editable s) {
            searchQuery(s.toString());
        }
    };

    private void searchQuery(String query) {
        List<RestaurantModel> restaurantTempList = new ArrayList<>();
        List<MenuItems> menuTempList = new ArrayList<>();

        if(restaurantMainModel!=null && restaurantMainModel.getRestaurants()!=null &&
        !restaurantMainModel.getRestaurants().isEmpty()){
            for(RestaurantModel restaurantModel : restaurantMainModel.getRestaurants()){
                if(restaurantModel.getCuisine_type().toLowerCase().contains(query.toLowerCase())){
                    //Check query for Restaurant cuisine type.
                    restaurantTempList.add(restaurantModel);
                }else if(restaurantModel.getName().toLowerCase().contains(query.toLowerCase())){
                    //Check query for Restaurant name.
                    restaurantTempList.add(restaurantModel);
                }
            }
        }

        if(menuMainModel!=null && menuMainModel.getMenus()!=null &&
        !menuMainModel.getMenus().isEmpty()){
            for(MenuModel menuModel : menuMainModel.getMenus()){
                //Get Menu Category.
                if(menuModel!=null && menuModel.getCategories()!=null &&
                        !menuModel.getCategories().isEmpty()){
                    for(MenuCategory menuCategory: menuModel.getCategories()){
                        //Get Menu item.
                        if(menuCategory!=null && menuCategory.getMenuItems()!=null &&
                                !menuCategory.getMenuItems().isEmpty()){
                            for(MenuItems menuItems : menuCategory.getMenuItems()){
                                //Check query for menu item name.
                                    if(menuItems.getName().toLowerCase().contains(query.toLowerCase())){
                                        menuTempList.add(menuItems);
                                    }
                            }
                        }
                    }
                }
            }
        }

        myInterface.updateData(menuTempList, restaurantTempList);
    }

    public void addTextChangeListener(EditText edtSearch) {
        edtSearch.addTextChangedListener(textWatcher);
    }
}
