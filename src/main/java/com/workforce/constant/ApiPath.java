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

    public static final String MASTER_DATA = SEPARATOR + "master-data";

    public static final class Auth {
        public static final String ROOT_PATH = BASE_PATH + VERSION + SEPARATOR + "auths";
        public static final String LOGIN = SEPARATOR + "login";
        public static final String SERVICE_TOKEN = SEPARATOR + "oauth2/token";
    }

    public static final class User {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "users";
        public static final String USER_IDENTIFIER = IDENTIFIER_ID;
    }

    public static class Menu {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "menus";
        public static final String MENU_IDENTIFIER = IDENTIFIER_ID;
    }

    public static class UserRole {
        public static final String ROOT_PATH = MASTER_DATA+ BASE_PATH + VERSION + SEPARATOR + "roles";
        public static final String USER_ROLE_IDENTIFIER = IDENTIFIER_ID;
    }

    public static class RoleMenuPermission {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "role-menu-permissions";
        public static final String ROLE_MENU_PERMISSION_IDENTIFIER = IDENTIFIER_ID;
    }

    public static class Department {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "departments";
        public static final String DEPARTMENT_IDENTIFIER = IDENTIFIER_ID;
    }

    public static class Designation {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "designations";
        public static final String DESIGNATION_IDENTIFIER = IDENTIFIER_ID;
        public static final String DESIGNATION_IDENTIFIER_DEPARTMENT_ID = "/by-department" + IDENTIFIER_ID;
    }

    public static class Project {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "projects";
        public static final String PROJECT_IDENTIFIER = IDENTIFIER_ID;
    }

    public static class Sprint {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "sprints";
        public static final String SPRINT_IDENTIFIER = IDENTIFIER_ID;
    }

    public static class Task {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "tasks";
        public static final String TASK_IDENTIFIER = IDENTIFIER_ID;
    }

}
