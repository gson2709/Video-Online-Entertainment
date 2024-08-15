package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.entity.Favorite;
import com.poly.entity.User;
import com.poly.utils.JpaUtils;

public class FavoriteDAO extends AsmDAO<Favorite, Long>{
	
	EntityManager em = JpaUtils.getEntityManager();

	@Override
	public void insert(Favorite entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			System.out.println("Thêm mới thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Thêm mới thất bại");
		}
		JpaUtils.shutdown();
	}

	@Override
	public void update(Favorite entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			System.out.println("Cập nhật thành công");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Thêm mới thất bại");
		}
		JpaUtils.shutdown();
	}

	@Override
	public void delete(Long id) {
		Favorite entity = em.find(Favorite.class, id);
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
	public Favorite selectById(Long id) {
		return em.find(Favorite.class, id);
	}

	@Override
	public List<Favorite> selectAll() {
		String jpql = "select o from Favorite o";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		List<Favorite> list = query.getResultList();
		return list;
	}
	
	public List<Favorite> selectByUser(String userId){
		return em.find(User.class, userId).getFavorites();
	}
	
	public boolean isUserLike(List<Favorite> list ,String userId) {
		boolean check = false;
		for (Favorite like : list) {
			if (like.getUser().getUsername().equalsIgnoreCase(userId)) {
				check = true;
				break;
			}
		}
		return check;
	}
	
}
