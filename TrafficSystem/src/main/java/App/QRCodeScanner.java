package App;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class QRCodeScanner {
    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();

        JFrame window = new JFrame("QR Code Scanner - License Plate");
        WebcamPanel panel = new WebcamPanel(webcam);

        // Add a close button so user can dip whenever
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                webcam.close();
                window.dispose();
            }
        });
        window.setLayout(new BorderLayout());
        window.add(panel, BorderLayout.CENTER);
        window.add(closeBtn, BorderLayout.SOUTH);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        // Scan loop
        while (window.isDisplayable()) {
            BufferedImage image = webcam.getImage();
            if (image != null) {
                String result = decodeQRCode(image);
                if (result != null) {
                    System.out.println("License Plate QR Code Detected: " + result);
                    JOptionPane.showMessageDialog(window, "License Plate: " + result);
                    break; // Stop after first detection
                }
            }
            try {
                Thread.sleep(200); // Reduce CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        webcam.close();
        window.dispose();
    }

    // Helper to scan QR code using webcam and return result
    public static String scanAndReturnResult() {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
        JFrame window = new JFrame("QR Code Scanner");
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setImageSizeDisplayed(true); // Show image size
        panel.setMirrored(true); // Mirror for natural webcam feel


        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                webcam.close();
                window.dispose();
            }
        });
        window.setLayout(new BorderLayout());
        window.add(panel, BorderLayout.CENTER);
        window.add(closeBtn, BorderLayout.SOUTH);
        window.pack();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Center the window on the screen
        window.setLocationRelativeTo(null);

        window.setVisible(true);
        String resultText = null;
        while (resultText == null && window.isDisplayable()) {
            BufferedImage image = webcam.getImage();
            if (image != null) {
                resultText = decodeQRCode(image);
                // Show preview (already handled by WebcamPanel)
                if (resultText != null) {
                    JOptionPane.showMessageDialog(window, "QR Code Detected: " + resultText);
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        webcam.close();
        window.dispose();
        return resultText;
    }

    private static String decodeQRCode(BufferedImage image) {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            return null; // No QR code found
        }
    }
}
