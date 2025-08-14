package com.workforce.constant;

public final class ApiPath {
    private ApiPath() {}
    public static final String DASH = "-";
    public static final String SEPARATOR = "/";
    public static final String VERSION = "/v1";
    public static final String OPEN_PARENTHESIS = "{";
    public static final String CLOSE_PARENTHESIS = "}";
    public static final String BASE_PATH = SEPARATOR + "api";
    public static final String SWAGGER_PATH = SEPARATOR + "swagger-ui/**";
    public static final String API_DOCS_PATH = SEPARATOR + "v3/api-docs/**";
    public static final String IDENTIFIER_ID = SEPARATOR + OPEN_PARENTHESIS + "id" + CLOSE_PARENTHESIS;
    public static final String IDENTIFIER_USERNAME = SEPARATOR + OPEN_PARENTHESIS + "username" + CLOSE_PARENTHESIS;
    public static final String IDENTIFIER_CODE = SEPARATOR + OPEN_PARENTHESIS + "code" + CLOSE_PARENTHESIS;
    public static final String IDENTIFIER_NAME = SEPARATOR + OPEN_PARENTHESIS + "name" + CLOSE_PARENTHESIS;

    public static final class Auth {
        public static final String ROOT_PATH = BASE_PATH + VERSION + SEPARATOR + "auths";
        public static final String LOGIN = SEPARATOR + "login";
        public static final String SERVICE_TOKEN = SEPARATOR + "oauth2/token";
    }

    public static final class User {
        public static final String ROOT_PATH = BASE_PATH + VERSION + SEPARATOR + "users";
        public static final String USER_IDENTIFIER = IDENTIFIER_ID;
        public static final String USER_IDENTIFIER_BY_USERNAME = "/find-by-username" + IDENTIFIER_USERNAME;
        public static final String ME = SEPARATOR + "me";
    }

    public static class Menu {
        public static final String ROOT_PATH = "/master-data/api/v1/menus";
        public static final String MENU_IDENTIFIER = "/{id}";
    }

    public static class UserRole {
        public static final String ROOT_PATH = "/master-data/api/v1/roles";
        public static final String USER_ROLE_IDENTIFIER = "/{id}";
    }

    public static class RoleMenuPermission {
        public static final String ROOT_PATH = "/master-data/api/v1/role-menu-permissions";
        public static final String ROLE_MENU_PERMISSION_IDENTIFIER = "/{id}";
    }

}
