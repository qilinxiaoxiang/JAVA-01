package org.wsbo.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpRouter implements HttpRouter{
    @Override
    public String route(List<String> backendUrls) {
        int size = backendUrls.size();
        Random random = new Random(System.currentTimeMillis());
        return backendUrls.get(random.nextInt(size));
    }
}
