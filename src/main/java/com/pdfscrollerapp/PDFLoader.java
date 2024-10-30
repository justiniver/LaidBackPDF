package com.pdfscrollerapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

/**
 * Loads and renders the given PDF.
 */
public class PDFLoader {

  public ImageIcon loadPDF(String filePath) {
    try {
      PDDocument document = PDDocument.load(new File(filePath));
      PDFRenderer pdfRenderer = new PDFRenderer(document);

      BufferedImage image = pdfRenderer.renderImageWithDPI(0, 150);
      document.close();

      return new ImageIcon(image);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
