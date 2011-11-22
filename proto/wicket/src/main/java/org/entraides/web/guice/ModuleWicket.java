package org.entraides.web.guice;

import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.servlet.ServletModule;
import org.apache.wicket.Component;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Branchement du module wicket sur toutes les requêtes http
*/
public class ModuleWicket extends ServletModule {
    @Override
    protected void configureServlets() {

        // avoids "Error initializing WicketFilter - you have no <filter-mapping> element..."
        // IllegalArgumentException
        Map<String, String> params = new HashMap<String, String>();
        params.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        params.put("configuration", "deployment");
        filter("/*").through(WicketGuiceFilter.class, params);

        //on ajoute un listener pour tous les objets de type Component
        bindListener(new AbstractMatcher<TypeLiteral>() {

            public boolean matches(TypeLiteral typeLiteral) {
                return Component.class.isAssignableFrom(typeLiteral.getRawType());
            }
        }, new ComponentInstrumentation.ComponentTypeListener());

    }
}
