import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * MarginBorder is a simplified {@link CompoundBorder} that consists of a {@link LineBorder} and an {@link EmptyBorder}
 */
public class MarginBorder extends CompoundBorder {

    /**
     * Creates a new MarginBorder with the specified arguments
     * @param borderWidth The width of the solid line
     * @param borderColour The colour of the solid line
     * @param marginThickness The width of the empty margin
     */
    public MarginBorder(int borderWidth, Color borderColour, int marginThickness) {
        super(new LineBorder(borderColour, borderWidth), new EmptyBorder(marginThickness, marginThickness, marginThickness, marginThickness));
    }
}
