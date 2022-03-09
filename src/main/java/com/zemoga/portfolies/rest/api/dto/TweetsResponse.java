package com.zemoga.portfolies.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to map the information of the user's tweets
 * 
 * @author OMAR ROSAS-PC
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class TweetsResponse {
	private String fullName;
	private String userName;
	private String content;
	private String profileImageUrl;
}
