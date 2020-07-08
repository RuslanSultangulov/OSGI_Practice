package ru.sultangulov.task5.util;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Utility bundle for title handlers started");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Utility bundle for title handlers stopped");
    }

}
