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
    public static final String TASK_BOARD = SEPARATOR + "task-board";
    public static final String ATTENDANCE = SEPARATOR + "attendance-insight";
    public static final String LEAVE = SEPARATOR + "leave-insight";
    public static final String STANDUP = SEPARATOR + "standup-insight";
    public static final String REPORT = SEPARATOR + "report";

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
        public static final String PROJECT_BY_USER_IDENTIFIER = "/assign-user"+ IDENTIFIER_ID;
    }

    public static class Sprint {
        public static final String ROOT_PATH = MASTER_DATA + BASE_PATH + VERSION + SEPARATOR + "sprints";
        public static final String SPRINT_IDENTIFIER = IDENTIFIER_ID;
        public static final String SPRINT_BY_PROJECT_IDENTIFIER = "/by-project"+ IDENTIFIER_ID;
    }

    public static class Task {
        public static final String ROOT_PATH =TASK_BOARD+ BASE_PATH + VERSION + SEPARATOR + "tasks";
        public static final String TASK_IDENTIFIER = IDENTIFIER_ID;
        public static final String TASK_BY_SPRINT_IDENTIFIER = "/by-sprint"+ IDENTIFIER_ID;
        public static final String TASK_BY_USER_IDENTIFIER = "/by-user"+ IDENTIFIER_ID;
    }

    public static class Attendance {
        public static final String ROOT_PATH = ATTENDANCE + BASE_PATH + VERSION + SEPARATOR + "attendances";
        public static final String ATTENDANCE_IDENTIFIER = IDENTIFIER_ID;
        public static final String ATTENDANCE_BY_USER_IDENTIFIER = "/by-user"+ IDENTIFIER_ID;
        public static final String ATTENDANCE_BY_USER_IDENTIFIER_AND_WORK_DATE = SEPARATOR+ "by-user-work-date";
    }

    public static class AllocatedLeave {
        public static final String ROOT_PATH = LEAVE + BASE_PATH + VERSION + SEPARATOR + "allocated-leaves";
        public static final String ALLOCATED_LEAVE_IDENTIFIER = IDENTIFIER_ID;
        public static final String ALLOCATED_LEAVE_BY_USER_IDENTIFIER = "/by-user" + IDENTIFIER_ID;
        public static final String ALLOCATED_LEAVE_FOR_ALL_USER_IDENTIFIER = "/all" + "/{year}";
    }

    public static class LeaveRequest {
        public static final String ROOT_PATH = LEAVE + BASE_PATH + VERSION + SEPARATOR + "leave-requests";
        public static final String LEAVE_REQUEST_IDENTIFIER = IDENTIFIER_ID;
        public static final String LEAVE_REQUEST_BY_USER_IDENTIFIER = "/by-user" + IDENTIFIER_ID;
    }

    public static class DailyStandup {
        public static final String ROOT_PATH = STANDUP + BASE_PATH + VERSION + SEPARATOR + "daily-standups";
        public static final String DAILY_STANDUP_IDENTIFIER = IDENTIFIER_ID;
        public static final String DAILY_STANDUP_BY_USER_IDENTIFIER = "/by-user" + IDENTIFIER_ID;
        public static final String DAILY_STANDUP_BY_TO_DATE_IDENTIFIER = "/by-date" + "/{date}";
    }

    public static class Report {
        public static final String PROJECT_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "project-reports";
        public static final String TODAY_USER_TASK_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "daily-user-task-reports";
        public static final String USER_TASK_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "user-task-reports";
        public static final String STANDUP_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "standup-reports";
        public static final String TODAY_STANDUP_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "standup-reports-by-date"+ "/{date}";
        public static final String DAILY_ATTENDANCE_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "daily-attendance-reports";
        public static final String SPRINT_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "sprints-reports";
        public static final String PROJECT_OVERVIEW_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "project-overview-reports";
        public static final String PROJECT_ROAD_REPORT = REPORT + BASE_PATH + VERSION + SEPARATOR + "project-roadmap-reports";

    }
}