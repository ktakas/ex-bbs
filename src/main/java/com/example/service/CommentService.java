package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

/**
 * コメント情報を操作するサービス.
 * 
 * @author kohei.takasaki
 *
 */
@Service
@Transactional
public class CommentService {
	
	@Autowired
	private CommentRepository repository;
	
	/**
	 * 記事IDと一致するコメント情報を全件取得する.
	 * 
	 * @param articleId 記事ID
	 * @return コメント情報のリスト
	 */
	public List<Comment> findByArticleId(int articleId) {
		return repository.findByArticleId(articleId);
	}
	
	/**
	 * 引数のコメント情報をインサートする.
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		repository.insert(comment);
	}
	
	/**
	 * 引数の記事IDと一致するコメントをすべて削除する.
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteByArticleId(int articleId) {
		repository.deleteByArticleId(articleId);
	}
}
