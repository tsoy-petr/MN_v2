package com.android.hootor.mn_v2.ui.common.navigation;

import java.util.HashMap;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

/**
 * Created by terrakok 27.11.16
 */
public class LocalCiceroneHolder {
    private HashMap<String, Cicerone<Router>> containers;

    public LocalCiceroneHolder() {
        containers = new HashMap<>();
    }

    public Cicerone<Router> getCicerone(String containerTag) {
        if (!containers.containsKey(containerTag)) {
            containers.put(containerTag, Cicerone.create());
        }
        return containers.get(containerTag);
    }
}
