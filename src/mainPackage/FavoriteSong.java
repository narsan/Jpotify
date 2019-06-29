package mainPackage;

import java.io.IOException;

/**
 * @author shakiba
 * this is a class that kind of palylist with no functionality of rename and delete hole playlist
 */

public class FavoriteSong extends PlayList {

    public FavoriteSong() throws IOException {

        super("Favorite songs");
        super.delete.setVisible(false);
        super.setNewName.setVisible(false);


    }
}
