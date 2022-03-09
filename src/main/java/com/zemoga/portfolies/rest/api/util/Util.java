package com.zemoga.portfolies.rest.api.util;

import java.util.Iterator;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.zemoga.portfolies.rest.api.dto.TweetsResponse;
import com.zemoga.portfolies.rest.api.entity.model.Portfolio;

/**
 * Class to define support functions.
 * 
 * @author OMAR ROSAS-PC
 * @version 1.0
 *
 */
public class Util {

	private Util() {
	}

	/**
	 * Get the portfolies list in JSON format.
	 * 
	 * @param List of portfolies.
	 * @return List of portfolies in JSON format.
	 */
	public static String getListPorfoliosToJSON(Iterable<Portfolio> listPortfolies) {
		StringBuilder response = new StringBuilder();
		Iterator<Portfolio> iterator = listPortfolies.iterator();
		listPortfolies.forEach(it -> {
			response.append(new GsonBuilder().setPrettyPrinting().create().toJson(iterator.next()) + "\n");
		});
		return response.substring(0, response.length() - 1);
	}

	/**
	 * Get the portfolio data in JSON format.
	 * 
	 * @param Current portfolio.
	 * @return Portfolio data in JSON format.
	 */
	public static String getPorfolioToJSON(Portfolio portfolio) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(portfolio);
	}

	/**
	 * Get the tweets list in JSON format.
	 * 
	 * @param List of tweets.
	 * @return List of tweets in JSON format.
	 */
	public static String getListTweetToJSON(List<TweetsResponse> listTweets) {
		StringBuilder response = new StringBuilder();
		listTweets.stream().forEach(tweet -> {
			response.append(new GsonBuilder().setPrettyPrinting().create().toJson(tweet) + "\n");
		});
		return response.substring(0, response.length() - 1);
	}

	public static String getMessageNotFound(Long id) {
		return "Order with ID :" + id + " Not Found!";
	}

}
