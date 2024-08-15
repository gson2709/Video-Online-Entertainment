package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import com.poly.entity.LikeByUsers;
import com.poly.entity.LikesCount;
import com.poly.entity.SenderByVideo;
import com.poly.utils.JpaUtils;

public class ReportDAO {
	EntityManager em = JpaUtils.getEntityManager();
	
	public List<LikesCount> getVideoFavoriteCount(){
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getFavoriteCount");
		return query.getResultList();
	}
	
	public List<LikeByUsers> getUsersLikeByVideo(String videoId){
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getUsersLikeByVideo");
		query.setParameter("VideoId", videoId);
		return query.getResultList();
	}
	
	public List<SenderByVideo> getSenderByVideo(String videoId){
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getSenderByVideo");
		query.setParameter("VideoId", videoId);
		return query.getResultList();
	}
}
