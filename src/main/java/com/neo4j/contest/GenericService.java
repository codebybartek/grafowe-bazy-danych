package com.neo4j.contest;

import org.neo4j.ogm.session.Session;

abstract class GenericService<T> implements Service<T> {

	protected Session session;
	
	public GenericService(Session session){
		this.session = session;
	}
	
    @Override
	public T read(Long id) { return session.load(getEntityType(), id); }
	
    @Override
	public Iterable<T> readAll() {
    	return session.loadAll(getEntityType());
    }

    @Override
	public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }
    
    @Override
	public void deleteAll() {
    	session.deleteAll(getEntityType());
    }
    
    @Override
	public void createOrUpdate(T entity) {
        session.save(entity);
    }


    abstract Class<T> getEntityType();
}
