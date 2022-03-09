package com.zemoga.portfolies.rest.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Getter
@Setter
@Configuration
public class TwitterApiConfiguration {
	@Value("${twitter.oauth.consumerKey}")
	private String consumerKey;
	@Value("${twitter.oauth.consumerSecret}")
	private String consumerSecret;
	@Value("${twitter.oauth.accessToken}")
	private String accessToken;
	@Value("${twitter.oauth.accessTokenSecret}")
	private String accessSecretToken;
	private Twitter twitterInstance;

	@Bean
	public void initTwitterConnection() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessSecretToken);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitterInstance = tf.getInstance();
	}
}
