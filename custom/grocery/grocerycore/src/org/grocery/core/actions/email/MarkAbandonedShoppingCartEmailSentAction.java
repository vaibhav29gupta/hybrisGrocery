package org.grocery.core.actions.email;

import de.hybris.platform.commerceservices.model.process.AbandonedShoppingCartEmailProcessModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.task.RetryLaterException;

/**
 * Mark a {@link CartModel} that a notification email has been sent as it was handled by an {@link AbandonedShoppingCartEmailProcessModel}.
 */
public class MarkAbandonedShoppingCartEmailSentAction extends AbstractProceduralAction<AbandonedShoppingCartEmailProcessModel> {

    @Override
    public void executeAction(AbandonedShoppingCartEmailProcessModel abandonedShoppingCartEmailProcessModel) throws RetryLaterException, Exception {
        CartModel cartModel = abandonedShoppingCartEmailProcessModel.getAbandonedCart();
        cartModel.setAbandonedShoppingCartEmailSent(Boolean.TRUE);

        getModelService().save(cartModel);
    }

}
