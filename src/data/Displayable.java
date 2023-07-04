package data;

import gui.DisplayPanel;
import java.util.Map;

public interface Displayable {
        /**
         * Displayable objects must have a name, so the user knows what they are seeing
         * @return A name for the object
         */
        String getName();

        /**
         * A map describing the characteristics of the object
         * @return A map from characteristics to their values
         */
        Map<String, String> getStats();

        /**
         * Register a panel to be updated when the object changes
         * @param panel The panel to register
         */
        void registerPanel(DisplayPanel panel);
}
