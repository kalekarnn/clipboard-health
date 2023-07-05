package com.clipboard.healthcare.common.query;

public class DocumentQueries {

    public static final String FIND_ACTIVE_DOCUMENTS_BY_WORKER_ID = "select id, name, is_active from \"Document\" d where d.is_active is true and d.id in (select distinct document_id  from \"DocumentWorker\" dw where worker_id = ?)";
}
