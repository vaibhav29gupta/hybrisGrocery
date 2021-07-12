/**
 * 
 */
package org.grocery.core.cache.impl;


import org.apache.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
/**
 * @author arshdeepsingh
 *
 */

/**
 * DefaultGroceryManualCacheRegion - Default implementation of GroceryManualCacheRegion
 *
 * This internally uses the EHCahce repository to maintain cached value
 *
 */
public class DefaultGroceryManualCacheRegion extends InvalidationBasedGorceryManualCacheRegion
{

	private static final Logger LOG = Logger.getLogger(DefaultGroceryManualCacheRegion.class);

   /**
    * EHCache Manager
    */
   private CacheManager cacheManager;
   /**
    * Cache instance
    */
   private Cache cacheMap;

   /**
    * @param cacheName
    */
   public DefaultGroceryManualCacheRegion(final String cacheName) {
       super(cacheName);
   }
   
   @Override
   protected void initCacheProvider() {
       this.cacheManager = CacheManager.getInstance();
       if (!this.cacheManager.cacheExists(this.cacheName)) {
           final Cache cacheMap = new Cache(this.createCacheConfiguration());
           this.cacheManager.addCache(cacheMap);
           this.cacheMap = this.cacheManager.getCache(this.cacheName);
       }
   }

   /**
    * Creates EHCache configuration
    */
   private CacheConfiguration createCacheConfiguration() {
       final CacheConfiguration config = new CacheConfiguration();
       config.setStatistics(false);
       LOG.info("GroceryManualCacheRegion " + this.cacheName + ", eviction policy " + this.evictionPolicy + ", size " + this.maxEntries);
       config.setMemoryStoreEvictionPolicy(this.evictionPolicy.toString());
       config.setMaxElementsOnDisk(1);
       config.setMaxEntriesLocalHeap(this.maxEntries.longValue());
       config.setName(this.cacheName);
       config.overflowToDisk(false);
       if (this.ttlSeconds == null) {
           config.setEternal(true);
       } else {
           config.setEternal(false);
           config.setTimeToLiveSeconds(this.ttlSeconds.longValue());
       }

       return config;
   }

   /*
    * (non-Javadoc)
    *
    * @see
    * au.com.fantasticfurniture.core.cache.FFManualCacheRegion#put(java.lang.
    * Object, java.lang.Object)
    */
   @Override
   public void put(final Object key, final Object value) {
       this.cacheMap.put(new Element(key, value));
   }

   /*
    * (non-Javadoc)
    *
    * @see
    * au.com.fantasticfurniture.core.cache.FFManualCacheRegion#get(java.lang.
    * Object)
    */
   @Override
   public Object get(final Object key) {
       final Element element = this.cacheMap.get(key);
       Object returnedValue = null;
       if (element != null) {
           returnedValue = element.getObjectValue();
       }
       return returnedValue;
   }

   /*
    * (non-Javadoc)
    *
    * @see
    * au.com.fantasticfurniture.core.cache.FFManualCacheRegion#invalidate(java.
    * lang.Object)
    */
   @Override
   public void invalidate(final Object key) {
       this.invalidate(key, INVALIDATIONTYPE_MODIFIED);
   }
   
   @Override
   public void clear() {
       for (final Object key : this.cacheMap.getKeys()) {
           this.invalidate(key, INVALIDATIONTYPE_CLEARALL);
           break;
       }
   }

   /*
    * (non-Javadoc)
    *
    * @see
    * au.com.fantasticfurniture.core.cache.impl.AbstractFFManualCacheRegion#
    * removeCacheElement(java.lang.Object)
    */
   @Override
   protected void removeCacheElement(final Object key) {
       this.cacheMap.remove(key);
   }

   /*
    * (non-Javadoc)
    *
    * @see au.com.fantasticfurniture.core.cache.impl.
    * InvalidationBasedFFManualCacheRegion#removeAllCacheElements()
    */
   @Override
   protected void removeAllCacheElements() {
       this.cacheMap.removeAll();
   }

}
