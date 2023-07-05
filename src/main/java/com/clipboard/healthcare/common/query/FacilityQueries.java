package com.clipboard.healthcare.common.query;

public class FacilityQueries {

    public static final String FIND_FACILITY_DOCUMENTS = "select distinct fr.facility_id , fr.document_id  " +
            "from \"FacilityRequirement\" fr join \"Document\" d on fr.document_id = d.id " +
            "join \"Facility\" f on fr.facility_id = f.id where d.is_active is true  and f.is_active is true";
}
