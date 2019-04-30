package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.Posts;

public interface PostsService {
	int countPosts();

	int countPostsByUserName(String userName);

	List<Posts> getPostsByUserName(PageableService pageable, String userName);

	Posts getPostsById(String id);

	void addPost(Posts posts);

	void updatePosts(Posts posts);

	void activePosts(String id, boolean active);

	void deletePosts(String id);

}
