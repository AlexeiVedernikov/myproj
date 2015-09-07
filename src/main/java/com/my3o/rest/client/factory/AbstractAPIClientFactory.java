package com.my3o.rest.client.factory;

import com.my3o.rest.client.RestAPIClient;
import com.my3o.rest.constant.ClientProvider;

public abstract class AbstractAPIClientFactory<Client extends RestAPIClient> {
    public abstract Client getApiClient(ClientProvider provider) throws Exception;
}
