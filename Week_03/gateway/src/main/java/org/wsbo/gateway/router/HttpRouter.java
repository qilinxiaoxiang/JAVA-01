package org.wsbo.gateway.router;

import java.util.List;

public interface HttpRouter {
    String route(List<String> backendUrls);
}
