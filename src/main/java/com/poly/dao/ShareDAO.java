package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.entity.Share;
import com.poly.utils.JpaUtils;

public class ShareDAO extends AsmDAO<Share, Long>{
	
	EntityManager em = JpaUtils.getEntityManager();
	
	@Override
	public void insert(Share entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			System.out.println("Thêm mới thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Thêm mới thất bại!");
			e.printStackTrace();
		}
		JpaUtils.shutdown();
	}

	@Override
	public void update(Share entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Share selectById(Long id) {
		return em.find(Share.class, id);
	}

	@Override
	public List<Share> selectAll() {
		String jpql = "select o from Share o";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		List<Share> list = query.getResultList();
		return list;
	}
	
}
