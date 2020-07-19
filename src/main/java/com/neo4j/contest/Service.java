package com.neo4j.contest;

interface Service<T> {
	
    T read(Long id);

    Iterable<T> readAll();

    void delete(Long id);
    
    void deleteAll();

    void createOrUpdate(T object);
}
