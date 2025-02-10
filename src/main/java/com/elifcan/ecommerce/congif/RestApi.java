package com.elifcan.ecommerce.congif;

public class RestApi {
    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";
    private static final String BASE_URL = DEV + VERSION;

    public static final String CART = BASE_URL + "/cart";
    public static final String CATEGORY = BASE_URL + "/category";
    public static final String PRODUCT = BASE_URL + "/product";
    public static final String USER = BASE_URL + "/user";

    public static final String ADD_TO_CART = BASE_URL + "/add-to-cart";

    public static final String REGISTER_USER = BASE_URL + "/register-user";

    public static final String ADD_PRODUCT = BASE_URL + "/add-product";

    public static final String ADD_CATEGORY = BASE_URL + "/add-category";
}
