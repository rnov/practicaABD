package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import org.apache.commons.io.IOUtils;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import controllers.UsuarioController;

public class UsuariosModifyData extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsuarioController controller;
	JPanel panel = new JPanel();
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	JPasswordField pass = new JPasswordField(15);
	JLabel labelFoto = new JLabel("No tienes Foto Click para añadirla");
	JLabel labelEdad = new JLabel("Fecha Nacimiento");
	JLabel labelPassword = new JLabel("Cambia tu password");
	JButton bnuevoUsuario = new JButton("Modificar Datos");
	JFileChooser chooser; 
	FileInputStream baos;
	public UsuariosModifyData(UsuarioController control){
		super("Modificación de datos");
		setSize(400,400);
		setLocation(300,300);
		this.controller=control;
		panel.setLayout (null);
		labelEdad.setBounds(200, 0, 150, 20);
		datePicker.setBounds(200, 20, 150, 100);
		labelPassword.setBounds(200, 120, 150, 20);
		pass.setBounds(200, 140,150, 20);
		labelFoto.setBounds(0, 0, 200, 200);
		bnuevoUsuario.setBounds(100, 300, 200, 20);
		panel.add(datePicker);
		panel.add(labelEdad);
		panel.add(labelPassword);
		panel.add(pass);
		panel.add(labelFoto);
		panel.add(bnuevoUsuario);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setDefaultData();
		actionModify();
	}
	private String setChooser(){ 
        chooser = new JFileChooser();  
        chooser.setCurrentDirectory(new java.io.File(".")); 
        chooser.setDialogTitle("Seleccione Imagen"); 
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
        chooser.setAcceptAllFileFilterUsed(false); 
          
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {  
            System.out.println("getCurrentDirectory(): "
               +  chooser.getCurrentDirectory()); 
            System.out.println("getSelectedFile() : "
               +  chooser.getSelectedFile()); 
        } 
        else { 
            System.out.println("No Selection "); 
        } 
        String ret = chooser.getSelectedFile().toString(); 
        return ret; 
      }
	public void setDefaultData(){
		InputStream pic=this.controller.getFoto();
		if(pic!=null){
			try {
				this.labelFoto.setIcon(new ImageIcon(IOUtils.toByteArray(pic)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}
		model.setValue(this.controller.getFechaNa());
	}
	public void actionModify(){
		labelFoto.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				 String newPath = setChooser();//ruta completa de la imagen 
	               
	                try {    
	                    File fnew=new File(newPath); 
	                    baos=new FileInputStream(fnew);
	                    
	                } catch (IOException e1) { 
	                    e1.printStackTrace(); 
	                } 
	                //hace update 
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		bnuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok=false;
				String ppaswd = pass.getText();
				if(!ppaswd.equals("")){
					Date fecha =model.getValue();
					java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
					if(baos!=null){
						if(controller.updateData(ppaswd, sqlDate, baos)){
							ok=true;
						}
						else{
							ok=false;
						}
					}else{
						if(controller.updateData(ppaswd, sqlDate)){
							ok=true;
						}
						else{
							ok=false;
						}
					}
					if(ok){
						JOptionPane.showMessageDialog(null,"actualizdo");
					}
				}else{
					JOptionPane.showMessageDialog(null,"El password no tiene que ser vacio");
				}
				
				dispose();
				Login frameTabel = new Login();
				
				
			}
		});
	}
}
