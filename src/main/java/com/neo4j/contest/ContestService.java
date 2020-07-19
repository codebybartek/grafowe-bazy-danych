package com.neo4j.contest;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.session.Session;

class ContestService extends GenericService<Contest> {

    public ContestService(Session session) {
		super(session);
	}
    
	@Override
	Class<Contest> getEntityType() {
		return Contest.class;
	}
	
    Iterable<Map<String, Object>> getContestRelationships() {
        String query = 
        		"MATCH (b:Contest)-[r]-() " +
        		"WITH type(r) AS t, COUNT(r) AS c " +
        		"WHERE c >= 1 " +
        		"RETURN t, c";
        System.out.println("Executing " + query);
        HashMap<String, Object> params = new HashMap<String, Object>();
        return session.query(query, params).queryResults();
    }


}
