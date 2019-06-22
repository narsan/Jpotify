import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoriteSong extends PlayList {

    public FavoriteSong(){

        super("Favorite songs");
        super.delete.setVisible(false);
        super.setNewName.setVisible(false);


    }
}
