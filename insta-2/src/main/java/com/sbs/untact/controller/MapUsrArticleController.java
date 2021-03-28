package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.util.ResultData;
import com.sbs.untact.util.Util;

@Controller
public class MapUsrArticleController {
	private List<Article> articles;
	private int articlesLastId;
	
	public MapUsrArticleController() {
		articles = new ArrayList<>();
		
		articles.add(new Article(++articlesLastId, Util.getNoewDaeStr(),Util.getNoewDaeStr(),"제목1", "내용1"));
		articles.add(new Article(++articlesLastId, Util.getNoewDaeStr(),Util.getNoewDaeStr(),"제목", "내용2"));
	}
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public List<Article> doWrite() {
		return articles;
	}
	
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public Article getArticle(int id) {
		return articles.get(id - 1);
	}
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		boolean deleteArticleRs = deleteArticle(id);
		Map<String, Object> map = new HashMap<>();
		
		if(deleteArticleRs == false) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		return new ResultData("S-1", "성공하였습니다.", "id", id);
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
	
	
}

