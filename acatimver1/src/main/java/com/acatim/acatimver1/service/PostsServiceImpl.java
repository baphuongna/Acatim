package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.PostsDAO;
import com.acatim.acatimver1.entity.Posts;

@Service
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostsDAO postsDAO;
	
	@Override
	public int countPosts() {
		return this.postsDAO.countPosts();
	}

	@Override
	public int countPostsByUserName(String userName) {
		return this.postsDAO.countPostsByUserName(userName);
	}

	@Override
	public List<Posts> getPostsByUserName(PageableService pageable, String userName) {
		return this.postsDAO.getPostsByUserName(pageable, userName);
	}

	@Override
	public Posts getPostsById(String id) {
		return this.postsDAO.getPostsById(id);
	}

	@Override
	public void addPost(Posts posts) {
		this.postsDAO.addPost(posts);
	}

	@Override
	public void updatePosts(Posts posts) {
		this.postsDAO.updatePosts(posts);
	}

	@Override
	public void activePosts(String id, boolean active) {
		this.postsDAO.activePosts(id, active);
	}

	@Override
	public void deletePosts(String id) {
		this.postsDAO.deletePosts(id);
	}

}
