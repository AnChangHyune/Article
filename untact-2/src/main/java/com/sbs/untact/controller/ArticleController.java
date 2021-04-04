package com.sbs.untact.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Controller
public class ArticleController {
	private List<Article> articles;
	private int articleLastId;
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(){
		return articles;
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body){
		int id = addArticle(title, body);
		
		Article article = getArticleById(id);
		
		return new ResultData("S-1", id+"번 글이 등록되었습니다.", "article",article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id){
		boolean delete = deleteArticle(id);
		
		if(delete == false) {
			return new ResultData("F-1", id+"번 글은 존재하지 않습니다.", "id",id);
		}
		return new ResultData("S-1", id+"번 글을 삭제하였습니다.", "id",id);
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id,String title, String body){
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", id+"번 글은 존재하지 않습니다.", "id",id);
		}
		return new ResultData("S-1", id+"번 글 입니다", "article",article);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id,String title, String body){
		boolean modified = modifyArticle(id,title, body);
		
		if(modified == false) {
			return new ResultData("F-1", id+"번 글은 존재하지 않습니다.", "id",id);
		}
		return new ResultData("S-1", id+"번 글을 수정했습니다.", "article",getArticleById(id));
	}
	



	//=========================================================
	
	private boolean modifyArticle(Integer id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return false;
		}
		
		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);
		
		return true;
	}
	
	private boolean deleteArticle(Integer id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}
	
	private Article getArticleById(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	private int addArticle(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		
		Article article = new Article(id,regDate,updateDate,title,body);
		articles.add(article);
		
		articleLastId = id;
		return id;
	}
		
}
