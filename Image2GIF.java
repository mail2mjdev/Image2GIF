import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;


public class Image2GIF{

    public static void main(String[] args) throws Exception {

        //list all files in input folder
        File folder = new File("./input/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

        BufferedImage first = ImageIO.read(new File("./input/"+listOfFiles[0].getName()));
	
        ImageOutputStream output = new FileImageOutputStream(new File("./output/generated.gif"));

        GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 250, true);
        
        //writer.writeToSequence(first);
        File[] images = new File[50]; //increase no. of input Image
        for (int i=0; i < listOfFiles.length; i++) {
                images[i] =  new File(listOfFiles[i].toString().replace('\\', '/'));
                BufferedImage next = ImageIO.read(new File("./input/"+listOfFiles[i].getName()));
                writer.writeToSequence(next);
        }
        writer.close();
        output.close();	
    }


}