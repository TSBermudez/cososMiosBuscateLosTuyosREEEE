package com.orange.myPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.swing.*;

import com.orange.util.Constants;
import com.orange.util.Utils;

public class MyPanel extends JPanel {

	private static final long serialVersionUID = 187372061425930086L;
	private JComboBox items;
    private JTextField input;
    private JButton boton;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JTextArea salida;
    private JLabel about;
    
	public MyPanel() {
		//construct preComponents
        String[] itemsItems = {"IC", "XSD", "WSDL", "DIAGRAMA"};
        String[] itemsExt = {"doc","xsd","wsdl","vsd"};

        //construct components
        items = new JComboBox (itemsItems);
        input = new JTextField (5);
        boton = new JButton ("Descargar");
        jcomp4 = new JLabel ("   ");
        jcomp5 = new JLabel ("Fichero:");
        jcomp6 = new JLabel ("MDW:");
        salida = new JTextArea (5, 5);
        about = new JLabel ("THT MICROS - Buscador MDW");

		// set components properties
		input.setToolTipText("Número de MDW");
		about.setToolTipText ("@tbermudr");

		// adjust size and set layout
		setPreferredSize (new Dimension (362, 306));
        setLayout (null);

		// add components
        add (items);
        add (input);
        add (boton);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (salida);
        add (about);

		// set component bounds (only needed by Absolute Positioning)
        items.setBounds (15, 70, 100, 25);
        input.setBounds (130, 70, 100, 25);
        boton.setBounds (245, 70, 100, 25);
        jcomp4.setBounds (150, 15, 60, 25);
        jcomp5.setBounds (15, 45, 100, 25);
        jcomp6.setBounds (130, 45, 100, 25);
        salida.setBounds (15, 120, 330, 150);
        about.setBounds (10, 275, 175, 25);
        
		// listener
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = input.getText();
				try {
					boolean success = false;
					String urlString = Utils.mdwScraping(text,itemsItems[items.getSelectedIndex()], success);
					
					if (items.getSelectedIndex() == 1) {
						URL website = new URL(urlString);
						ReadableByteChannel rbc = Channels.newChannel(website.openStream());
						FileOutputStream fos = new FileOutputStream("Peticion_"+itemsItems[items.getSelectedIndex()] + text + "."+itemsExt[items.getSelectedIndex()], false);
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
						fos.close();
						URL website2 = new URL(urlString.replaceAll("Peticion", "Respuesta"));
						ReadableByteChannel rbc2 = Channels.newChannel(website2.openStream());
						FileOutputStream fos2 = new FileOutputStream("Respuesta_"+itemsItems[items.getSelectedIndex()] + text + "."+itemsExt[items.getSelectedIndex()], false);
						fos2.getChannel().transferFrom(rbc2, 0, Long.MAX_VALUE);
						fos2.close();
					} else {
						URL website = new URL(urlString);
						ReadableByteChannel rbc = Channels.newChannel(website.openStream());
						FileOutputStream fos = new FileOutputStream(itemsItems[items.getSelectedIndex()] + text + "."+itemsExt[items.getSelectedIndex()], false);
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
						fos.close();
					}
					salida.setLineWrap(true);
					salida.setText("Documento "+itemsItems[items.getSelectedIndex()]+" generado...");
					
				} catch (Exception e1) {
					salida.setLineWrap(true);
					salida.setText("No existe documento "+itemsItems[items.getSelectedIndex()]+" para el MDW " + text);
					if (text.equalsIgnoreCase(Constants.EASTEREGG)) {
						salida.setText(Constants.EASTEREGG_RESPONSE);
					}
				}
			}
		});
	}
}
