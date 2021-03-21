package com.maven.springboot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	EntityManager entityManager;
	
	public MyDataDaoImpl() {
		super();
	}
	
	public MyDataDaoImpl(EntityManager manager) {
		entityManager = manager;
	}
	@Override
	public List<MyData> getAll() {
		Query query = entityManager.createQuery("from MyData");
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public MyData findById(long id) {
		return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
	}
	
	@Override
	public List<MyData> findByName(String name){
		return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
	}
	
	@Override
	public List<MyData> find(String fstr){
		List<MyData> list = null;
		String qstr = "from MyData where id = ?1 or name like ?2 or mail like ?3";
		Long fid = 0L;
		try {
			fid = Long.parseLong(fstr);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
		Query query = entityManager.createQuery(qstr).setParameter(1, fid).setParameter(2, "%" + fstr + "%").setParameter(3, fstr + "@%");
		list = query.getResultList();
		return list;
	}
}
