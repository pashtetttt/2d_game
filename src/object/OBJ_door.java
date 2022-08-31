package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_door extends SuperObject{
    public OBJ_door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
