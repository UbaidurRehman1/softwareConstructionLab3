/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.security.Timestamp;
import java.text.Normalizer.Form;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	
    	tweets.sort(Comparator.comparing(Tweet::getTimestamp));
    	
    	Instant start = tweets.get(0).getTimestamp();
    	
    	Instant end = tweets.get(tweets.size() - 1).getTimestamp();
    	
    	return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	
    	Set<String> mentionedUsers = new HashSet<>();

    	for(Tweet tweet : tweets)
    	{
    		String text = tweet.getText();
    		
    		boolean isContain = text.contains(" @");
    		if(isContain)
    		{
    			long count1 = text.codePoints().filter(ch -> ch == '@').count();
    			int from = 0;
    			for(int i = 0; i < count1; i++)
    			{
    				try
    				{
        				int index = text.indexOf(" @", from);
        				int last = text.indexOf(" ", index + 1);
        				from = index;
        				
        				String user = text.substring(index, last);
        				try
        				{
            				mentionedUsers.add(user);
        					
        				}
        				catch(Exception exp)
        				{
        	
        				}
    					
    				}
    				catch(Exception exp) {} 
    			}
    			    			
    		}
    	}
    	
    	return mentionedUsers;
    }

}
