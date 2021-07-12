/**
 *
 */
package org.grocery.core.purchagehistory.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.product.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.grocery.core.purchagehistory.dao.GroceryMostPurchasedProductsDao;
import org.springframework.beans.factory.annotation.Required;

import com.sun.istack.logging.Logger;


/**
 * @author pappusharma
 *
 */
public class GroceryMostPurchasedProductsDaoImpl extends DefaultProductDao implements GroceryMostPurchasedProductsDao
{


	private static final Logger LOG = Logger.getLogger(GroceryMostPurchasedProductsDaoImpl.class);

	private UserService userService;
	private FlexibleSearchService flexibleSearchService;

	@Override
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Override
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	private static final String MOST_PURCHAGED_PRODUCT = "SELECT {e.product} FROM { OrderEntry AS e JOIN Order AS o ON {e.order} = {o.pk}} WHERE {o.pk} in "
			+ " ({{ select * from ({{select {o.pk} from {Order as o} WHERE {o.creationTime} > ?date Order By {o.creationTime}  Limit ?numberOfOrders}})prod }})"
			+ " Group By {e.product} Order By count(*) ";

	/*
	 * private static final String BEST_SELLER_PRODUCTS =
	 * "SELECT {e.product} FROM { OrderEntry AS e JOIN Order AS o ON {e.order} = {o.pk}} " +
	 * " WHERE {o.pk} in ({{select {o.pk} from {Order as o} WHERE {o.creationTime} > ?date Order By {o.creationTime} Limit ?numberOfOrders}}) "
	 * + " Group By {e.product} Order By count(*) ";
	 */

	private static final String BEST_SELLER_PRODUCTS = "SELECT {e.product} FROM { OrderEntry AS e JOIN Order AS o ON {e.order} = {o.pk}} "
			+ " WHERE {o.pk} in ({{select {o.pk} from {Order as o} WHERE {o.creationTime} > ?date Order By {o.creationTime} }}) "
			+ " Group By {e.product} Order By count(*) ";


	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected UserService getUserService()
	{
		return userService;
	}


	@Override
	public List<ProductModel> getMostPuchasedProduct(final int count)
	{
		final CustomerModel currentUser = (CustomerModel) userService.getCurrentUser();
		final Map<String, Object> params = new HashMap<>();
		params.put("user", currentUser);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(MOST_PURCHAGED_PRODUCT, params);
		query.setCount(count);

		final SearchResult<ProductModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<ProductModel> getBestSellerProducts(final Integer numberOfOrders, final Integer numberOfDays)
	{
		final Map<String, Object> params = new HashMap<>();

		final LocalDate today = LocalDate.now();
		final LocalDate date = today.minusDays(numberOfDays);
		params.put("date", date.toString());
		params.put("numberOfOrders", numberOfOrders);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(BEST_SELLER_PRODUCTS, params);

		final SearchResult<ProductModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

}
