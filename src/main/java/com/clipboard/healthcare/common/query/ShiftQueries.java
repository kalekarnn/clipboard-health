package com.clipboard.healthcare.common.query;

public class ShiftQueries {

    public static final String SEARCH_SHIFTS_BY_START_AND_END_DATE = "select to_char( s.start, 'YYYY-MM-DD') as start_date, string_agg(concat( id,'&',facility_id), '@') as id_fid\n" +
            "from \"Shift\" s \n" +
            "where s.\"profession\" = '%s' and s.is_deleted is false and s.worker_id is null \n" +
            "and s.start >= TO_TIMESTAMP(?,'YYYY-MM-DD hh24:mi:ss.nnn') and s.end < TO_TIMESTAMP(?,'YYYY-MM-DD hh24:mi:ss.nnn') and facility_id in (%s)  \n" +
            "group by 1 \n" +
            "order by start_date";


    public static final String GET_SHIFTS_BY_WORKER_ID_START_AND_END_DATE = "select * \n" +
            "from \"Shift\" s \n" +
            "where s.\"worker_id\" = ? \n" +
            "and s.start >= TO_TIMESTAMP(?,'YYYY-MM-DD hh24:mi:ss.nnn') and s.end < TO_TIMESTAMP(?,'YYYY-MM-DD hh24:mi:ss.nnn')  \n" +
            "order by start_date";
}
