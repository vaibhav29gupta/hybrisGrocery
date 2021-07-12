package org.grocery.core.socialmedia.restrictions;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.localization.Localization;
import org.grocery.core.model.restrictions.SocialMediaCustomerRestrictionModel;

public class SocialMediaCustomerRestrictionDescription implements DynamicAttributeHandler<String, SocialMediaCustomerRestrictionModel> {

    @Override
    public String get(SocialMediaCustomerRestrictionModel model) {
        StringBuilder result = new StringBuilder();
        String localizedString = Localization.getLocalizedString("type.SocialMediaCustomerRestriction.description.text");
        result.append(localizedString == null ? "Social Media Customer Restriction" : localizedString);

        return result.toString();
    }

    @Override
    public void set(SocialMediaCustomerRestrictionModel model, String s) {
        throw new UnsupportedOperationException();
    }
}
