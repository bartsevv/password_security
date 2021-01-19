package bartsev.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class Steganography {
//    public static void main(String[] args) {
//        encodeNewImage("2.png", "new2.png", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25");
//        System.out.println(decodeImage("new2.png"));
//    }

    public static void encodeNewImage(String matrix, String newPath) {
        String currentPath = "ADMIN.png";
        Convert c = new Convert();
        ImageProcess impro = new ImageProcess();
        byte[] txtBytes = c.txtToByte(matrix);
        BufferedImage img = null;
        try {
            img = impro.fetchImage(currentPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        img = impro.hideText(img, txtBytes);
        new File(currentPath).delete();
        File output = new File(newPath);
        try {
            ImageIO.write(img, "png", output);
            Files.move(output.toPath(), output.toPath().resolveSibling("ADMIN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decodeImage(String path) {
        int msgLen = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25".length();
        int offset = 0;
        BufferedImage img = ImageProcess.newImageFetch(path);
        byte[] msgBytes = ImageProcess.extractHiddenBytes(img, msgLen, offset);
        if (msgBytes == null)
            return null;
        return new String(msgBytes);
    }
}

class Convert {
    byte[] txtToByte(String s) {
        byte[] arr = s.getBytes(Charset.forName("UTF-8"));
        return arr;
    }
}


class ImageProcess {
    BufferedImage fetchImage(String path) throws Exception {
        File f = new File(path);
        BufferedImage img = ImageIO.read(f);
        return img;
    }

    BufferedImage hideText(BufferedImage img, byte[] txt) {
        int i = 0;
        int j = 0;
        for (byte b : txt) {
            for (int k = 7; k >= 0; k--) {
                Color c = new Color(img.getRGB(j, i));
                byte blue = (byte) c.getBlue();
                int red = c.getRed();
                int green = c.getGreen();
                int bitVal = (b >>> k) & 1;
                blue = (byte) ((blue & 0xFE) | bitVal);
                Color newColor = new Color(red,
                        green, (blue & 0xFF));
                img.setRGB(j, i, newColor.getRGB());
                j++;
            }
            i++;
        }
        return img;
    }

    static BufferedImage newImageFetch(String path) {
        File f = new File(path);
        BufferedImage img = null;
        try {
            img = ImageIO.read(f);
        } catch (Exception ex) {
        }
        return img;
    }

    static byte[] extractHiddenBytes(BufferedImage img, int size, int offset) {

        int i = 0;
        int j = 0;
        byte[] hiddenBytes = new byte[size];

        for (int l = 0; l < size; l++) {
            for (int k = 0; k < 8; k++) {
                Color c = new Color(img.getRGB(j, i));
                byte blue = (byte) c.getBlue();
                hiddenBytes[l] = (byte) ((hiddenBytes[l] << 1) | (blue & 1));
                j++;
            }
            i++;
        }
        return hiddenBytes;

    }
}