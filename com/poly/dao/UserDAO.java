package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.entity.User;
import com.poly.utils.JpaUtils;

public class UserDAO extends AsmDAO<User, String>{
	
	EntityManager em = JpaUtils.getEntityManager();
	
	@Override
	public void insert(User entity) {
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
	public void update(User entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			System.out.println("Cập nhật thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Cập nhật thất bại!");
		}
		JpaUtils.shutdown();	
	}

	@Override
	public void delete(String id) {
		User entity = em.find(User.class, id);
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			System.out.println("Xóa thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Xóa thất bại!");
		}
		JpaUtils.shutdown();
		
	}

	@Override
	public User selectById(String id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> selectAll() {
		String jpql = "select o from User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();
		return list;
	}
}
