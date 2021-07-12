package org.grocery.core.job;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.AbandonedShoppingCartCronJobModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import org.apache.log4j.Logger;
import org.grocery.core.event.AbandonedShoppingCartEvent;
import org.grocery.core.order.dao.GroceryCommerceCartDao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * Job responsible with publishing {@link AbandonedShoppingCartEvent}s for all the abandoned {@link CartModel}s after the
 * session closed and a certain configurable amount of time has passed.
 */
public class AbandonedShoppingCartJob extends AbstractJobPerformable<AbandonedShoppingCartCronJobModel> {

    private static final Logger LOGGER = Logger.getLogger(AbandonedShoppingCartJob.class);
    private static final String MINUTES_TO_EXPIRE = "abandonedshoppingcart.minutesaftersessioncloses";
    private static final String DEFAULT_SESSION_TIMEOUT = "default.session.timeout";

    private ConfigurationService configurationService;
    private BaseSiteService baseSiteService;
    private UserService userService;
    private GroceryCommerceCartDao groceryCommerceCartDao;
    private EventService eventService;
    private TimeService timeService;
    private CommonI18NService commonI18NService;

    @Override
    public PerformResult perform(AbandonedShoppingCartCronJobModel cronJobModel) {
        if (cronJobModel.getSites() == null || cronJobModel.getSites().isEmpty()) {
            LOGGER.warn("There are no sites defined for " + cronJobModel.getCode());
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        LOGGER.info("Abandoned Shopping Cart Job starting ...");
        final LocalDateTime currentDateTime = getCurrentDateTime();

        int sessionTimeout = getConfigurationService().getConfiguration().getInt(DEFAULT_SESSION_TIMEOUT);
        int minutesToExpire = getConfigurationService().getConfiguration().getInt(MINUTES_TO_EXPIRE);
        Date modifiedBefore = toDate(currentDateTime.plus(sessionTimeout, ChronoUnit.SECONDS)
                .plus(minutesToExpire, ChronoUnit.MINUTES));

        for (BaseSiteModel site : cronJobModel.getSites()) {
            LOGGER.info("Searching for Abandoned Shopping Carts for site: " + site.getUid());
            List<CartModel> abandonedShoppingCarts = getGroceryCommerceCartDao().getAbandonedCartsForSiteAndUser(modifiedBefore,
                    site, getUserService().getAnonymousUser());
            abandonedShoppingCarts.stream().filter(c -> c.getEntries() != null && !c.getEntries().isEmpty()).
                    filter(c -> c.getAbandonedShoppingCartEmailSent() == Boolean.FALSE).
                    forEach(cartModel -> publishAbandonedShoppingCartEvent(cartModel, site));
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private Date toDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private LocalDateTime getCurrentDateTime() {
        final Date currentDate = getTimeService().getCurrentTime();
        return LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
    }

    private void publishAbandonedShoppingCartEvent(CartModel cartModel, BaseSiteModel siteModel) {
        AbandonedShoppingCartEvent abandonedShoppingCartEvent = new AbandonedShoppingCartEvent(cartModel);
        abandonedShoppingCartEvent.setCustomer((CustomerModel) cartModel.getUser());
        abandonedShoppingCartEvent.setSite(siteModel);
        abandonedShoppingCartEvent.setBaseStore(siteModel.getStores().get(0));
        abandonedShoppingCartEvent.setLanguage(getCommonI18NService().getCurrentLanguage());
        abandonedShoppingCartEvent.setCurrency(getCommonI18NService().getCurrentCurrency());
        LOGGER.info("Abandoned Shopping Cart: " + cartModel.getGuid() + " for customer: " + ((CustomerModel) cartModel.getUser()).getUid());
        getEventService().publishEvent(abandonedShoppingCartEvent);
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public GroceryCommerceCartDao getGroceryCommerceCartDao() {
        return groceryCommerceCartDao;
    }

    public void setGroceryCommerceCartDao(GroceryCommerceCartDao groceryCommerceCartDao) {
        this.groceryCommerceCartDao = groceryCommerceCartDao;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }
}
