package com.yogita.netflix.model;

import jakarta.persistence.*;

@Entity
@Table(name="movies")
public class Movie {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String title;
private String description;
private String poster;
private String trailer;

@Column(name="iframe_link")
private String iframeLink;

private String category;

@Column(name="is_premium")
private boolean isPremium;

public int getId() { return id; }
public void setId(int id) { this.id = id; }

public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }

public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }

public String getPoster() { return poster; }
public void setPoster(String poster) { this.poster = poster; }

public String getTrailer() { return trailer; }
public void setTrailer(String trailer) {this.trailer = trailer; }

public String getIframeLink() { return iframeLink; }
public void setIframeLink(String iframeLink) { this.iframeLink = iframeLink; }

public String getCategory() { return category; }
public void setCategory(String category) { this.category = category; }

public boolean isPremium() { return isPremium; }
public void setPremium(boolean premium) { isPremium = premium; }

}