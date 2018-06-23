package ModulesPackage;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benshmuel on 10/06/2018.
 */
public class ImageCanvas implements Serializable {
    transient List<BufferedImage> images=new ArrayList<>();
    private static final long serialVersionUID = 1112L;


    public ImageCanvas() {
    }

    public ImageCanvas(List<BufferedImage> images) {
        this.images = images;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {

        //out.defaultWriteObject();
        out.writeInt(images.size()); // how many images are serialized?
        for (BufferedImage eachImage : images) {
            ImageIO.write(eachImage, "png", out); // png
        }
    }

    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        //in.defaultReadObject();
        final int imageCount = in.readInt();
        System.out.println("imageCount----->" + imageCount);

        images = new ArrayList<BufferedImage>(imageCount);

        for (int i=0; i<imageCount; i++) {

            BufferedImage bi = ImageIO.read(in);

            if(bi!=null)
                images.add(bi);
            else
                i--;
        }



        showResults();

    }

    public BufferedImage getImageByIndex(int i){

        return images.get(i);
    }



    private void showResults(){

        System.out.println("real size ----> " + images.size());


        for(BufferedImage image : images){

            System.out.println(image);

        }
    }
}