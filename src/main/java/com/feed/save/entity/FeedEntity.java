package com.feed.save.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "feed")
@Getter
@Setter
public class FeedEntity {
	@Id
	private long id;
	@Column(name="TITLE")
	private String title;
	@Column(name="LINK")
	private String link;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="PUBLISHDATE")
	private Date publishDate;

}
