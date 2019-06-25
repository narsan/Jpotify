package mainPackage;

import java.io.IOException;

public class FavoriteSong extends PlayList {

    public FavoriteSong() throws IOException {

        super("Favorite songs");
        super.delete.setVisible(false);
        super.setNewName.setVisible(false);


    }
}
