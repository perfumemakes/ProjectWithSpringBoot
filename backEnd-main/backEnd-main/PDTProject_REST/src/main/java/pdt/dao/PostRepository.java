package pdt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pdt.entity.Post;
import pdt.entity.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "select * from post", nativeQuery = true)
	public List<Post> getPostList();

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("select p from Post p where p.userId = :id")
	public List<Post> getPostListWithUserId(@Param("id") User userId);

//	@Transactional
//	@Modifying(clearAutomatically = true, flushAutomatically = true)
//	@Query("update Post p set p.likePost = p.likePost + 1 where p.postId = :id")
//	public void updateIlike(@Param("id") Long postId);

//	@Transactional
//	@Modifying(clearAutomatically = true, flushAutomatically = true)
//	@Query("update Post p set p.likePost = p.likePost - 1 where p.postId = :id")
//	public void updateIlike2(@Param("id") Long postId);

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "update post set like_count = (select count(*) from ilike where post_id=:id) where post_id=:id", nativeQuery = true)
	public void countLike(@Param("id") Long postId);

//	@Transactional
//	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "select like_count from post where post_id=:id", nativeQuery = true)
	public Long returnLike(@Param("id") Long postId);
	
	@Query(value = "select * from post where text like %:word%", nativeQuery = true)
	public List<Post> searchKeyword(@Param("word") String keyword);
	
	@Query(value = "select(A.keyword1), count(*) from ((select keyword1 from post union all select keyword2 from post) union all select keyword3 from post) A group by A.keyword1 limit 5;", nativeQuery = true)
	public List<Map<String, Object>> countKeyword();

	// (select keyword1, count(*) from post group by keyword1 union distinct select
	// keyword2, count(*) from post group by keyword2) union distinct select
	// keyword3, count(*) from post group by keyword3;
	// (select keyword1 from post group by keyword1 union select keyword2 from post
	// group by keyword2) union select keyword3 from post group by keyword3;
	// (select keyword1 from post union all select keyword2 from post) union all
	// select keyword3 from post;
	// select keyword1, count(*) from ((select keyword1 from post union all select
	// keyword2 from post) union all select keyword3 from post) A;
	// select keyword1, count(distinct keyword1) from ((select keyword1 from post
	// union all select keyword2 from post) union all select keyword3 from post) A;
	// select(A.keyword1), count(*) from ((select keyword1 from post union all
	// select keyword2 from post) union all select keyword3 from post) A group by
	// A.keyword1 limit 5;

}
