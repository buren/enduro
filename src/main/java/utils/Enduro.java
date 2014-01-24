package utils;

import java.net.URL;

public class Enduro {
    private static Enduro ourInstance = new Enduro();

    public static Enduro getInstance() {
        return ourInstance;
    }

    private Enduro() { }

    /**
     * Get path relative to root.
     * @param relativePath the relative path to get.
     * @return the absolute path to resource.
     */
    public String getResourcePath(String relativePath) {
        URL resourceUrl = Enduro.getInstance().getClass().getResource("../");
        return resourceUrl.getPath() + relativePath;
    }
}
