package com.feed.save.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDto {

	private long id;
	private String title;
	private String link;
	private String description;
	private Date publishDate;
}
