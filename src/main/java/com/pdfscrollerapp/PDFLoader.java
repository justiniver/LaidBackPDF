package com.pdfscrollerapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Loads and renders all pages of a given PDF.
 */
public class PDFLoader {

  public List<ImageIcon> loadPDF(String filePath) {
    List<ImageIcon> images = new ArrayList<>();

    try {
      PDDocument document = PDDocument.load(new File(filePath));
      PDFRenderer pdfRenderer = new PDFRenderer(document);

      for (int i = 0; i < document.getNumberOfPages(); i++) {
        BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150);
        images.add(new ImageIcon(image));
      }

      document.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return images;
  }

  public int[] getFirstPageDimensions(String filePath) {
    try (PDDocument document = PDDocument.load(new File(filePath))) {
      PDFRenderer pdfRenderer = new PDFRenderer(document);
      BufferedImage firstPageImage = pdfRenderer.renderImageWithDPI(0, 150);
      return new int[]{firstPageImage.getWidth(), firstPageImage.getHeight()};
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new int[]{600, 800};
  }
}
