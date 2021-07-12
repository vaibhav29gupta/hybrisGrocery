package org.grocery.service.promotions;

import de.hybris.platform.store.BaseStoreModel;

/**
 * Service responsible with getting details on the Promotion and MultiCodeCoupon related to some Grocery processes.
 * Possible processes for which this can be used are configured in PromotionProcess enumeration.
 * Details of these promotions are setup on the baseStore
 */
public interface ProcessesRelatedPromotionService {
  enum PromotionProcess {REGISTRATION, CART_ABANDONED}

  String getPromotionMessage(BaseStoreModel store, PromotionProcess process);

  String getNewCouponCode(BaseStoreModel store, PromotionProcess process);
}

