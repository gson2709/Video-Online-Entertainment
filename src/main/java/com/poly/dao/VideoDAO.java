package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.poly.entity.Video;
import com.poly.utils.JpaUtils;

public class VideoDAO extends AsmDAO<Video, String>{
	
	EntityManager em = JpaUtils.getEntityManager();

	@Override
	public void insert(Video entity) {
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
	public void update(Video entity) {
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
		Video entity = em.find(Video.class, id);
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			System.out.println("Xóa thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Xóa thất bại!");
			e.printStackTrace();
		}
		JpaUtils.shutdown();
	}

	@Override
	public Video selectById(String id) {
		return em.find(Video.class, id);
	}

	@Override
	public List<Video> selectAll() {
		String jpql = "select o from Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		List<Video> list = query.getResultList();
		return list;
	}
	
	public List<Video> page(double pos, int currentMax){
		String jpql = "select o from Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setFirstResult((int) ((pos * currentMax) - currentMax));
		query.setMaxResults(currentMax);
		return query.getResultList();
	}
	public List<Video> selectOther(String id){
		String jpql = "select o from Video o where o.id not like :id";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("id", id);
		List<Video> list = query.getResultList();
		return list;
	}
	
	public List<Video> selectByUser(String userId){
		String jpql = "select o from Favorite o where o.user.id=:uid";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("uid", userId);
		return query.getResultList();
	}
}
