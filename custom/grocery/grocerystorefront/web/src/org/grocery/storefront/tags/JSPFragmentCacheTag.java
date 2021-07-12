/**
 * 
 */
package org.grocery.storefront.tags;


import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.grocery.core.cache.GroceryManualCacheRegion;

/**
 * @author arshdeepsingh
 *
 */
public class JSPFragmentCacheTag extends BodyTagSupport
{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(JSPFragmentCacheTag.class);

	// attributes set in the jsp page
	private String key;

	private GroceryManualCacheRegion cache = null;

	private boolean cacheHit = true;

	private boolean cacheEnabled = true;

	private String currentSite = null;

	/**
	 * Instantiates a new JSP fragment cache tag.
	 */
	public JSPFragmentCacheTag() {
		this.key = null;
		this.cacheHit = true;
		this.currentSite = null;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the key to set
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		this.initCache();
		if (!cacheEnabled) {
			return BodyTagSupport.EVAL_BODY_INCLUDE;
		}
		int result = BodyTagSupport.SKIP_BODY;
		if(null != cache){
			LOG.debug("Cache is not null.");
			final String cachedResponse = (String) cache.get(currentSite + "-" + key);
			if (cachedResponse == null) {
				result = BodyTagSupport.EVAL_BODY_BUFFERED;
				cacheHit = false;
			} else {
				cacheHit = true;
				// we have cached content: write content and skip body
				try {
					pageContext.getOut().write(cachedResponse);
				} catch (final IOException e) {
					throw new JspException(e);
				}
			}
		}
		this.log();
		return result;
	}

	/**
	 * Inits the cache.
	 */
	public void initCache() {
		final HttpServletRequest localHttpServletRequest = (HttpServletRequest) this.pageContext.getRequest();
		Object fragCacheEnabled = localHttpServletRequest.getAttribute("fragmentCacheEnabled");  
		if(null != fragCacheEnabled){
			cacheEnabled = ((Boolean) fragCacheEnabled).booleanValue();
			if (cache == null) {
				cache = (GroceryManualCacheRegion) localHttpServletRequest.getAttribute("jspFragmentCacheRegion");
			}
			currentSite = (String) localHttpServletRequest.getAttribute("spruce-site");
		}
	}

	/**
	 * Log.
	 */
	private void log() {
		LOG.debug("Cache Key : " + this.key + " , CacheHit status :  " + this.cacheHit);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		if (!cacheEnabled) {
			return Tag.EVAL_PAGE;
		}
		this.log();
		if (cacheHit == false) {
			final String bodyContentAsString = bodyContent.getString();
			if(null != cache){
				cache.put(currentSite + "-" + key, bodyContentAsString);
				try {
					pageContext.getOut().write(bodyContentAsString);
				} catch (final IOException e) {
					throw new JspException(e);
				}
			}
		}
		return Tag.EVAL_PAGE;
	}

	/**
	 * @return the currentSite
	 */
	public String getCurrentSite() {
		return currentSite;
	}

	/**
	 * @param currentSite
	 *            the currentSite to set
	 */
	public void setCurrentSite(final String currentSite) {
		this.currentSite = currentSite;
	}

}
