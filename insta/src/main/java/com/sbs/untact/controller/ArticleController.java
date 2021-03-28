package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Util;
import com.sbs.untact.util.Article;

@Controller
public class ArticleController {
	private List<Article> articles;
	private int articleLastId;
	
	public ArticleController() {
		articles = new ArrayList<>();
		
		articles.add(new Article(++articleLastId,Util.getRegDate(),Util.getRegDate(),"제목1","내용1"));
		articles.add(new Article(++articleLastId,Util.getRegDate(),Util.getRegDate(),"제목2","내용2"));
	}
	
	@RequestMapping("usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articles.get(id - 1);
	}
	
	@RequestMapping("usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
	
	@RequestMapping("usr/article/doAdd")
	@ResponseBody
	public Map<String,Object> showdoAdd(String title, String body) {
		String regDate =  Util.getRegDate();
		String updateDate = regDate;
		
		articles.add(new Article(++articleLastId,regDate, updateDate, title ,body));
		
		Map<String, Object>map = new HashMap<>();
		
		return Util.mapOf("resultCode", "S-1","msg", "성공","id", articleLastId);
	}
	
	@RequestMapping("usr/article/doDelete")
	@ResponseBody
	public Map<String,Object> showdoDelete(int id){
		boolean deleteArticleRs = deleteArticle(id);
		
		Map<String, Object> rs = new HashMap<>();
		
		if (deleteArticleRs == false) {
			return Util.mapOf("resultCode", "F-1","msg", "해당 게시물은 삭제되었거나 없는 게시물입니다.");

		} 
		return Util.mapOf("resultCode", "S-1","msg", "삭제하였습니다.","id", id);
	}

	private boolean deleteArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				
				return true;
			}
		}
		return false;
	} 
	@RequestMapping("usr/article/doModify")
	@ResponseBody
	public Map<String,Object> showdoModify(int id,String title, String body){
		Article selArticle = null;
		
		for(Article article : articles) {
			if(article.getId() == id) {
				selArticle = article;
				break;
			}
		}
		Map<String, Object> rs = new HashMap<>();
		
		return;
	}
}
