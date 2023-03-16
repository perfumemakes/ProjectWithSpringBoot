package pdt.service;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pdt.dao.PostRepository;
import pdt.entity.Post;

@DynamicInsert
@DynamicUpdate
@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostRepository postRepo;

	public List<Post> getPostList() {
		List<Post> postList = (List<Post>) postRepo.findAll();
		return postList;
	}

	public void insertPost(Post post) {
		postRepo.save(post);

	}

	public Post getPost(Post post) {
		return postRepo.findById(post.getPostId()).get();
	}

	public void updatePost(Post post) {
		/*
		 * Board 객체는 영속성 context 영역에 저장 영속성 영역의 객체 데이터 저장 후 save - 새로운 데이터 갱신 확인 후에
		 * update
		 * 
		 */
		Post findPost = postRepo.findById(post.getPostId()).get();

		findPost.setText(post.getText());
		findPost.setKeyword1(post.getKeyword1());
		findPost.setKeyword2(post.getKeyword2());
		findPost.setKeyword3(post.getKeyword3());
		postRepo.save(findPost);
	}

	@Transactional
	public void deletePost(Post post) {
		postRepo.deleteById(post.getPostId());
	}

}