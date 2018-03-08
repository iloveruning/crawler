package com.cll.crawler;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author chenliangliang
 * @date 2018/1/13
 */
public class TestPdfToImage {

    @Test
    public void test() throws IOException {

        PDDocument document = PDDocument.load(new File("F:/test/微信营销.pdf"));
        int pages = document.getNumberOfPages();
        System.out.println(pages);

        //PDPage page = document.getPage(0);
        PDFRenderer renderer = new PDFRenderer(document);
        //renderer.
        BufferedImage image = renderer.renderImage(0);

        ImageIO.write(image,"jpg",new File("F:/test/666666.jpg"));

        document.close();
    }
}
