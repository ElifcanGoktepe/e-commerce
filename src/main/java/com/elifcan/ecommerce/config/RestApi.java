package com.elifcan.ecommerce.config;

public class RestApi {
    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";
    private static final String BASE_URL = DEV + VERSION;

    public static final String CART = BASE_URL + "/cart";
    public static final String CATEGORY = BASE_URL + "/category";
    public static final String PRODUCT = BASE_URL + "/product";
    public static final String USER = BASE_URL + "/user";

    public static final String ADD_TO_CART = "/add-to-cart";
    public static final String REMOVE_FROM_CART =  "/remove-from-cart";
    public static final String CLEAR_CART =  "/clear-cart";
    public static final String UP_DOWN_SEPET = "/up-down-sepe";

    public static final String REGISTER_USER = "/register-user";
    public static final String LOGIN_USER = "/login-user";

    public static final String ADD_PRODUCT ="/add-product";
    public static final String GET_ALL_PRODUCT = "/get-all-product";
    public static final String FIND_BY_ID = "/find-by-id";
    public static final String DELETE_PRODUCT = "/delete-product";

    public static final String ADD_CATEGORY =  "/add-category";
    public static final String MAIN_CATEGORY = "/main-category";
    public static final String SUB_CATEGORY =  "/sub-category";
}
