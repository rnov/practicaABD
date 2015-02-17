package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import model.Serie;
import controllers.AdminController;

public class AdminHome extends JFrame {
	public AdminController controller=new AdminController();
	JLabel labelTitu=new JLabel("Administrador");
	JPanel panel = new JPanel();
	JTabbedPane panelPest= new JTabbedPane();
	JPanel p1=new JPanel(null); 
	JPanel p2=new JPanel(null);
	JPanel p3=new JPanel(null);
	JButton bInfo=new JButton("Ver información");
	JButton bNuevaSerie=new JButton("Nueva Serie");
	JLabel buscarSerie=new JLabel("Buscar Serie:");
	JTextField fieldSeries= new JTextField();
	JButton bBuscarSerie=new JButton("Buscar");
	DefaultListModel<String> listModel=new DefaultListModel<String>();
	JList lista= new JList();
	JButton addAct= new JButton("Add Actor");
	
	JButton addPer= new JButton("Add Personaje");
	JTextField nombreActor=new JTextField();
	JTextField nombrePersonaje = new JTextField();
	JTextField descPer=new JTextField();
	JLabel desc=new JLabel("Descripción:");
	List<Serie> series;
	/*actores*/
	JLabel nombre=new JLabel("Nombre:");
	JLabel nombre2=new JLabel("Nombre:");
	JLabel fecha= new JLabel("Fecha:");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	JFileChooser chooser; 
	FileInputStream baos;
	JLabel foto= new JLabel("click para añadir foto");
	public AdminHome(){
		super("Admin");
		setSize(500,500);
		setLocation(200,50);
		panel.setLayout(null);
		labelTitu.setBounds(200, 10, 100, 40);
		panelPest.setBounds(5, 45, 475, 350);
		bInfo.setBounds(50, 400, 200, 20);
		bNuevaSerie.setBounds(255,400,200,20);
			
		
			buscarSerie.setBounds(130, 10, 100, 20);
			fieldSeries.setBounds(225, 10, 130, 20);
			bBuscarSerie.setBounds(360, 10, 100, 20);
			lista.setBounds(0,35,465,475);
			p1.add(buscarSerie);
			p1.add(fieldSeries);
			p1.add(bBuscarSerie);
			p1.add(lista);
		panelPest.addTab("Series", null, p1, "Busqueda");
			
			nombre.setBounds(20, 40, 100, 20);
			nombreActor.setBounds(140, 40, 150, 30);
			fecha.setBounds(20, 80, 100, 20);
			datePicker.setBounds(140,80,120,30);
			foto.setBounds(20,130,150,30);
			addAct.setBounds(180, 130, 100, 20);
			p2.add(nombre);
			p2.add(fecha);
			p2.add(datePicker);
			p2.add(foto);
			p2.add(addAct);
			p2.add(nombreActor);
		panelPest.addTab("Actores", null, p2, "Busqueda");
			
			nombre2.setBounds(20, 40, 100, 20);
			addPer.setBounds(180, 200, 150, 20);
			nombrePersonaje.setBounds(140, 40, 150, 20);
			desc.setBounds(20, 90, 100, 20);
			descPer.setBounds(160, 90, 200, 70);
			p3.add(nombre2);
			p3.add(addPer);
			p3.add(nombrePersonaje);
			p3.add(desc);
			p3.add(descPer);
		panelPest.addTab("Personajes", null, p3, "Busqueda");
		
		panel.add(panelPest);
		panel.add(labelTitu);
		panel.add(bInfo);
		panel.add(bNuevaSerie);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionAdminHome();
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
	public void actionAdminHome(){
		bInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String x=(String) lista.getSelectedValue();
				if(!x.equalsIgnoreCase("")){
					Serie serieB = null;
					for (int i = 0; i < series.size(); i++) {
						if(series.get(i).getNombre().equalsIgnoreCase(x)){
							serieB=series.get(i);
						}
						
					}
					UsuariosSeries serie=new UsuariosSeries(controller, serieB);
					dispose();
				}
			}
				
		});
		bBuscarSerie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				series=controller.getList(fieldSeries.getText());
				
				for(int i=0;i<series.size();i++){
					listModel.add(i, series.get(i).getNombre());
				}
				lista.setModel(listModel);
			}
		});
		bNuevaSerie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NuevaSerie serie= new NuevaSerie(controller);
				dispose();
			}
		});
		addAct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					Date fecha =model.getValue();
					java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
					if(nombreActor.getText()!="" && sqlDate!=null && baos!=null){
						if(controller.addActor(nombreActor.getText(),sqlDate,baos)){
							JOptionPane.showMessageDialog(null,"Actor Alta");
						}
						else{
							JOptionPane.showMessageDialog(null,"Actor no dado de alta");
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Campos no tienen que estar vacios");
					}
					AdminHome adHome=new AdminHome();
					dispose();
				}catch(NullPointerException f){
					JOptionPane.showMessageDialog(null,"Campos no tienen que estar vacios");
				}
				
			}
		});
		addPer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nombrePersonaje.getText()!="" && descPer.getText()!=""){
					if(controller.addPers(nombrePersonaje.getText(), descPer.getText())){
						JOptionPane.showMessageDialog(null,"Personaje Alta");
					}
					else{
						JOptionPane.showMessageDialog(null,"Personaje actor fallo");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Rellena El nombre, y la desc");
				}
				AdminHome adHome=new AdminHome();
				dispose();
			}
		});
		foto.addMouseListener(new MouseListener() {
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
	}
//	public static void main(String[] args) {
//		AdminHome adHome=new AdminHome();
//	}
}
