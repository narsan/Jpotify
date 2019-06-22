import java.io.IOException;

public class SharedPlayList extends PlayList {


    public SharedPlayList() throws IOException {


        super("Shared playlist");
        super.delete.setVisible(false);
        super.setNewName.setVisible(false);
    }


}
