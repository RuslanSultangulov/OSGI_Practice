package ru.sultangulov.task5.vedomosti_handler;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Vedomosti XML title handler service started!");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Vedomosti XML title handler service stopped!");
    }

}
