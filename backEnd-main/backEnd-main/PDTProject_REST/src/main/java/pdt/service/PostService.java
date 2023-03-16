package pdt.service;

import pdt.entity.Post;

public interface PostService {

	void insertPost(Post post);

	Post getPost(Post post);

	void updatePost(Post post);

	void deletePost(Post post);

//	void updateIlike(Long postId);

//	void updateIlike2(Long postId);

}
