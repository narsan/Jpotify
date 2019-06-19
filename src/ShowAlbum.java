//import com.mpatric.mp3agic.ID3v2;
//import com.mpatric.mp3agic.InvalidDataException;
//import com.mpatric.mp3agic.Mp3File;
//import com.mpatric.mp3agic.UnsupportedTagException;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class ShowAlbum extends JButton {
//
//    public ShowAlbum() {
//
//
//        this.setFont(new Font("Arial", Font.PLAIN, 20));
//        this.setText("Albums");
//        this.setBackground(Color.BLACK);
//        this.setForeground(Color.white);
//        this.setBorder(null);
//    }
//
//    public JPanel getAlbums() {
//
//        ArrayList<File> sameAlbum = new ArrayList<>();
//        ArrayList<String> AlbumNames=new ArrayList<>();
//        JPanel Albums = new JPanel();
//        Albums.setBackground(Color.BLACK);
//        Albums.setLayout(new GridLayout(0, Library.getSongs().size()));
//
//        String albumName = "";
//        for (int i = 0; i < Library.getSongs().size(); i++) {
//
//            for (int j = 0; j < Library.getSongs().size(); j++) {
//
//                File file = Library.getSongs().get(i);
//                try {
//                    Mp3File mp3File = new Mp3File(file);
//                    if (mp3File.hasId3v2Tag()) {
//                        ID3v2 id3v2 = mp3File.getId3v2Tag();
//
//                        albumName = id3v2.getAlbum();
//
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedTagException e) {
//                    e.printStackTrace();
//                } catch (InvalidDataException e) {
//                    e.printStackTrace();
//                }
//
//                File checkIfSame = Library.getSongs().get(j);
//                try {
//                    Mp3File mp3File = new Mp3File(checkIfSame);
//                    if (mp3File.hasId3v2Tag()) {
//
//                        ID3v2 id3v2 = mp3File.getId3v2Tag();
//                        if (id3v2.getAlbum().equals(albumName)) {
//
//
//                            sameAlbum.add(checkIfSame);
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedTagException e) {
//                    e.printStackTrace();
//                } catch (InvalidDataException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            if (sameAlbum.size()>=2){
//                if (AlbumNames.size()!=0) {
//
//                    if (!AlbumNames.contains(albumName)) {
//
//
//                        JPanel AlbumInfo = showAlbum(sameAlbum);
//                        Albums.add(AlbumInfo);
//                    }
//
//                }
//
//                if (AlbumNames.size()==0){
//
//                    JPanel AlbumInfo = showAlbum(sameAlbum);
//                    Albums.add(AlbumInfo);
//
//                }
//
//
//            }
//
//
//            sameAlbum.clear();
//            AlbumNames.add(albumName);
//
//
//        }
//
//        return Albums;
//    }
//
//    public JPanel showAlbum(ArrayList<File> musicsFile) {
//        JPanel showAlbumInfo = new JPanel();
//        showAlbumInfo.setLayout(new BoxLayout(showAlbumInfo, BoxLayout.Y_AXIS));
//        Mp3File mp3File = null;
//        try {
//            mp3File = new Mp3File(musicsFile.get(0));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (UnsupportedTagException e) {
//            e.printStackTrace();
//        } catch (InvalidDataException e) {
//            e.printStackTrace();
//        }
//        if (mp3File.hasId3v2Tag()) {
//
//            ID3v2 id3v2 = mp3File.getId3v2Tag();
//            String AlbumName = id3v2.getAlbum();
//            JLabel AlbumTitle = new JLabel(AlbumName);
//            AlbumTitle.setForeground(Color.WHITE);
//            JButton AlbumImage = new JButton();
//            AlbumImage.setBorder(null);
//            byte[] imageData = id3v2.getAlbumImage();
//            BufferedImage img = null;
//            try {
//                img = ImageIO.read(new ByteArrayInputStream(imageData));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
//            showAlbumInfo.setBackground(Color.BLACK);
//            AlbumImage.setIcon(imageIcon);
//            showAlbumInfo.add(AlbumImage);
//            showAlbumInfo.add(AlbumTitle);
//
//        }
//
//        return showAlbumInfo;
//    }
//
//
//}
