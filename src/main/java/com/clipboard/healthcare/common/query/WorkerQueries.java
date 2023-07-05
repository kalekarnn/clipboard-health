package com.clipboard.healthcare.common.query;

public class WorkerQueries {

    public static final String FIND_WORKER_BY_ID = "select id, name, is_active, profession from \"Worker\" where id = ?";
}
