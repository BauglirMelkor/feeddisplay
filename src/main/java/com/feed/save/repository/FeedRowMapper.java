package com.feed.save.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.feed.save.dto.FeedDto;

public class FeedRowMapper implements RowMapper<FeedDto> {

	@Override
	public FeedDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		FeedDto feedDto = new FeedDto();
		feedDto.setId(rs.getLong("ID"));
		feedDto.setTitle(rs.getString("TITLE"));
		feedDto.setDescription(rs.getString("DESCRIPTION"));
		feedDto.setLink(rs.getString("LINK"));
		feedDto.setPublishDate(rs.getDate("PUBLISHDATE"));
		return feedDto;
	}

}
