/**
 * MustSwapReserveException is a very specific exception that occurs when the
 * user goes to purchase a new {@link Athlete} and wants to place that athlete as active.
 * As the number of active athletes is fixed to 5, performing this action would exceed this action.
 * This exception allows for the {@link MarketScreen} to account for this and prompt the user to
 * pick an active athlete to place as a reserve to create room for the new athlete
 */
public class MustSwapReserveException extends RuntimeException {

    /**
     * Creates a new instance of the {@code MustSwapReserveException}
     */
    public MustSwapReserveException() {
        super();
    }
}
