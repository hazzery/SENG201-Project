import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MarginBorder extends CompoundBorder{
    public MarginBorder(int borderWidth, Color borderColour, int marginWidth) {
        super(new LineBorder(borderColour, borderWidth), new EmptyBorder(marginWidth, marginWidth, marginWidth, marginWidth));
    }
}
