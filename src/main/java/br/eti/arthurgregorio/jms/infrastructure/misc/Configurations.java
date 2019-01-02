package br.eti.arthurgregorio.jms.infrastructure.misc;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The class that hold the configurations of the application reading the default properties file under the resources
 * folder in the classpath
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 11/01/2018
 */
public final class Configurations {

    private static final ResourceBundle CONFIG_PROPERTIES;

    static {
        CONFIG_PROPERTIES = ResourceBundle.getBundle("application");
    }

    /**
     * Search in the configuration source for a key and retrieve his value, as {@link String}
     *
     * @param configuration the configuration to search his value
     * @return the value for this configuration
     */
    public static String get(String configuration) {
        try {
            return CONFIG_PROPERTIES.getString(Objects.requireNonNull(configuration));
        } catch (MissingResourceException ex) {
            return null;
        }
    }

    /**
     * Same as {@link #get(String)} but return the value as {@link Boolean}
     *
     * @param configuration the configuration to search his value
     * @return the value for this configuration
     */
    public static boolean getAsBoolean(String configuration) {
        return Boolean.valueOf(Objects.requireNonNull(get(configuration)));
    }

    /**
     * Same as {@link #get(String)} but return the value as {@link Integer}
     *
     * @param configuration the configuration to search his value
     * @return the value for this configuration
     */
    public static int getAsInteger(String configuration) {
        return Integer.valueOf(Objects.requireNonNull(get(configuration)));
    }

    /**
     * Retrieve the base URL of the application
     *
     * @return the base URL
     */
    public static String getBaseURL() {

        final FacesContext facesContext = FacesContext.getCurrentInstance();

        final HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        final StringBuilder builder = new StringBuilder();

        String actualPath = request.getRequestURL().toString();

        builder.append(actualPath.replace(request.getRequestURI(), ""));
        builder.append(request.getContextPath());

        return builder.toString();
    }
}
