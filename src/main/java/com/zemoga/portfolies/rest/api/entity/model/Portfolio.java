package com.zemoga.portfolies.rest.api.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to map the portfolio data to table of database.
 * 
 * @author OMAR ROSAS-PC
 * @version 1.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Portfolio {
	// Portfolio Data.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idportfolio")
	private Long idPortfolio;
	private String description;
	@Column(name = "experience_summary")
	private String experienceSummary;
	private Integer id;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "last_names")
	private String lastNames;
	private String names;
	private String tittle;
	@Column(name = "twitter_user_id")
	private String twitterUserId;
	@Column(name = "twitter_user_name")
	private String twitterUserName;
	@Column(name = "user_id")
	private String userId;
	private String address;
	private String email;
	private String experience;
	@Column(name = "image_path")
	private String imagePath;
	private String name;
	private String phone;
	@Column(name = "twitter_user")
	private String twitterUser;
	@Column(name = "zip_code")
	private String zipCode;
}
