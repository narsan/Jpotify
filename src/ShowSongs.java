import com.mpatric.mp3agic.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
public class ShowSongs extends Library {

        public ShowSongs() {

        }

        public JPanel CreatButtonFromSongs() {

            JLabel Title = new JLabel();
            Title.setVisible(true);
            JButton songImage = new JButton();
            songImage.setVisible(true);
            JPanel sonsPanel = new JPanel();
            sonsPanel.setVisible(true);
            sonsPanel.setLayout(new BoxLayout(sonsPanel, BoxLayout.X_AXIS));


            for (int i = 0; i < getSongs().size(); i++) {

                try {
                    Mp3File mp3File = new Mp3File(getSongs().get(i));
                    System.out.println(getSongs().size());
                    if (mp3File.hasId3v1Tag()) {
                        ID3v1 id3v1 = mp3File.getId3v1Tag();

                        Title.setText(id3v1.getTitle());
                        System.out.println(id3v1.getTitle());

                        Title.setFont(new Font("Arial", Font.PLAIN, 10));
                    }

                    if (mp3File.hasId3v2Tag()) {

                        ID3v2 id3v2 = mp3File.getId3v2Tag();

                        byte[] imageData = id3v2.getAlbumImage();
                        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                        songImage.setIcon(imageIcon);
                        String mimeType = id3v2.getAlbumImageMimeType();
                        System.out.println(mimeType);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }


                JPanel songData = new JPanel();
                songData.setLayout(new BoxLayout(songData, BoxLayout.Y_AXIS));
                songData.add(songImage);
                songData.add(Title);
                sonsPanel.add(songData);


            }

            return sonsPanel;

        }


    }

