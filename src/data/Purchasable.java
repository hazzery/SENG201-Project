package data;
import gui.DisplayPanel;


/**
 * The Purchasable interface allows an object to be purchased.
 * It requires that the object provide the following:
 * <ul>
 *     <li>A name,</li>
 *     <li>A description of the object in the form of a map,</li>
 *     <li>An initial purchase price, and</li>
 *     <li>A resale price</li>
 * </ul>
 * This allows the object to be represented on a {@link DisplayPanel} so it can be displayed on the GUI
 *
 * @author Harrison Parkes
 */
public interface Purchasable extends Displayable {

        /**
         * Gets the price the object should cost when purchased at the market
         * @return The price the object should initially cost
         */
        int getContractPrice();

        /**
         * Gets the value the user can get back when selling the object back to the market
         * @return The resale price of the object
         */
        int getSellBackPrice();
}
