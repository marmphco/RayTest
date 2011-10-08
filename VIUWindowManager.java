import java.awt.*;
import java.awt.event.*;

//the VIU prefix is there because WindowManager is a vague name and VIU stands for vector it up!
public class VIUWindowManager extends WindowAdapter
{

    public void windowClosing(WindowEvent event)
    {
        
        System.exit(0);
        
    }

}