package org.springframework.security.oauth2.client.token.grant.implicit;

import org.springframework.security.oauth2.client.token.grant.redirect.AbstractRedirectResourceDetails;

/**
 * <p>
 *
 * @author Dave Syer
 */
public class ImplicitResourceDetails extends AbstractRedirectResourceDetails {

	public ImplicitResourceDetails() {
		setGrantType("implicit");
	}

}
