package com.feed.save.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.feed.save.dto.FeedDto;

@Service
public class FeedRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public FeedRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(Object[] params) {
		jdbcTemplate.update("INSERT into FEED (id,title, link, description, publishDate) VALUES (?,?, ?, ?,?)", params);
	}

	public List<FeedDto> getFeeds() {
		return jdbcTemplate.query("SELECT * from FEED ", new FeedRowMapper());
	}
	
	public List<FeedDto> getLast10Feeds() {
		return jdbcTemplate.query("SELECT * from FEED order by publishDate desc LIMIT 10 ", new FeedRowMapper());
	}
}
