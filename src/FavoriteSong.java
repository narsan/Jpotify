import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoriteSong extends PlayList {

    public FavoriteSong(){

        super("favorite songs");
        super.delete.setVisible(false);
        super.setNewName.setVisible(false);
        super.playList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
