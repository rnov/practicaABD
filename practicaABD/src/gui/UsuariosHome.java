package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import model.Episodio;
import model.Serie;

import org.apache.commons.io.IOUtils;

import com.mysql.jdbc.Util;

import controllers.UsuarioController;

public class UsuariosHome extends JFrame{
	/**
	 * 
	 */
	public UsuarioController controller;
	public long idEpisodio;
	private static final long serialVersionUID = 1L;
	JLabel labelNombre = new JLabel("Test User");
	JPanel panel = new JPanel();
	JLabel labelEdad = new JLabel("30 Years");
	JLabel labelFoto = new JLabel("No tienes Foto");
	JButton bInfo = new JButton("Ver información");
	JTabbedPane panelPest= new JTabbedPane();
	//SERIES BUSQUEDA
	JPanel p1=new JPanel(null); 
	
	
	JLabel labelBuscar= new JLabel("Buscar Series:");
	JTextField fieldSeries= new JTextField();
	JButton bSerie=new JButton("Buscar");
	//lista series
	JPanel p2=new JPanel(null); 
	String[] petStrings = { "---"};

	//Create the combo box, select item at index 4.
	//Indices start at 0, so 4 specifies the pig.
	JComboBox petList = new JComboBox(petStrings);
	JLabel labelSerie = new JLabel("Serie:");
	//Lista
    DefaultListModel<String> listModel=new DefaultListModel<String>();
	JList lista= new JList();
	//EPISODIOS 
	private static final String[] COL_NAME = { "Num", "Temp","Titulo","Fecha" };
	MyTableModel model=new MyTableModel(null,COL_NAME);
	JTable table = new JTable(model);
	//List
	ArrayList<String> listIds=new ArrayList<String>();
	List<Long> listaIds=new ArrayList<Long>();
	
	JCheckBox chekcVisto=new JCheckBox("Episodios no vistos");
	JButton visto=new JButton("Marcar visto");
	List<Serie> series;
	List<Serie> seriesF;
	List<Episodio> episodiosV;
	List<Episodio> episodiosNV;
	String[][] newInventory;
	public UsuariosHome(UsuarioController control){
		super("Seguidores de series");
		this.controller=control;
		setSize(700,700);
		setLocation(200,50);
		
		panel.setLayout (null);
			lista.setBounds(0,25,665,400);
		labelBuscar.setBounds(5, 5, 200, 20);
			labelBuscar.setHorizontalAlignment(JLabel.RIGHT);
			fieldSeries.setBounds(210, 5, 200, 20);
			bSerie.setBounds(420, 5, 100, 20);
			p1.add(bSerie);
			p1.add(labelBuscar);
			p1.add(fieldSeries);
			p1.add(lista); 
		panelPest.addTab("Buscar Series", null, p1, "Busqueda");
			labelSerie.setBounds(5, 10, 340, 20);
			labelSerie.setHorizontalAlignment(JLabel.RIGHT);
			petList.setBounds(350, 10, 200, 20);
			table.setBounds(10,60,625,400);
			table.setCellSelectionEnabled(true);
			chekcVisto.setBounds(10, 30, 200, 30);
			visto.setBounds(150, 0, 150, 30);
			p2.add(labelSerie);
			p2.add(petList);
			p2.add(table);
			p2.add(chekcVisto);
			p2.add(visto);
		panelPest.addTab("Mis Series", null, p2, "Mis Series");
		
		
		labelFoto.setBounds(10, 0, 200, 200);
		labelNombre.setBounds(300,0,300,50);
		labelEdad.setBounds(300, 50, 300, 50);
		panelPest.setBounds(10, 220, 665, 400);
		bInfo.setBounds(200, 620, 200, 20);
		
		panel.add(labelFoto);
		panel.add(labelNombre);
		panel.add(labelEdad);
		panel.add(panelPest);
		panel.add(bInfo);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.actionHome();
		this.setUserData();
		this.setComboBox();
	}

	@SuppressWarnings("unchecked")
	public void setComboBox(){
		seriesF=this.controller.getListSeriesByUser();
		
		for (int i = 0; i < seriesF.size(); i++) {
			petList.addItem(seriesF.get(i).getNombre()+" - "+seriesF.get(i).getId_serie());
			
		}
	
	}
	public void setUserData(){
		
		this.labelNombre.setText(this.controller.getNombre());
		InputStream pic=this.controller.getFoto();
		if(pic!=null){
			try {
				this.labelFoto.setIcon(new ImageIcon(IOUtils.toByteArray(pic)));
			} catch (IOException e) {
			
				
			}
		}
		Date fecha=new Date();
        String t=String.valueOf(fecha.getYear()-this.controller.getEdadUsuario().getYear());
		this.labelEdad.setText(t+" years");
		
	}
	public void sacaPanel(String msg){
		JOptionPane.showMessageDialog(null,msg);
		//this.setVisible(false);
	}
	public void actionHome(){
		/**
		 * on click en ver información se abre un panel con 
		 */
		bInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String x=(String) lista.getSelectedValue();
				if(x!=null){
					int initInt=x.indexOf("-");
					int id=-1;
					x.replaceAll("\\s+","");
					String y=x.substring(initInt+1);
					try {
						id=Integer.parseInt(y.replaceAll("\\s+",""));
					} catch (Exception e) {
						id=-1;
					}
					if(id!=-1){
						Serie serieB = null;
						for (int i = 0; i < series.size(); i++) {
							if(series.get(i).getId_serie()==id){
								serieB=series.get(i);
							}
							
						}
						UsuariosSeries serie=new UsuariosSeries(controller, serieB);
						dispose();
					}
				}
				else{
					int[] selectedRow = table.getSelectedRows();
					long id=Long.parseLong((String) model.getValueAt(selectedRow[0],0));
					Episodio epi = null;
					if(selectedRow[0]!=-1){
						for (int i = 0; i < episodiosV.size(); i++) {
							if(episodiosV.get(i).getId_episodio()==id){
									epi=episodiosV.get(i);
							}
						}
					}
					dispose();
					EpisodiosInfo ep=new EpisodiosInfo(epi,controller);
				}
				
			}
				
		});
		bSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				listModel.clear();
				series=controller.getList(fieldSeries.getText());
				for(int i=0;i<series.size();i++){
					listModel.add(i, series.get(i).getNombre()+" - "+series.get(i).getId_serie());
					
				}
				lista.setModel(listModel);
			}
		});
		visto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int row=table.getSelectedColumn();
				String tituloE=(String) model.getValueAt(row, 2);
				long idEpi=-1;
				if(row!=-1){
					for (int i = 0; i < episodiosV.size(); i++) {
						if(episodiosV.get(i).getTitulo().equals(tituloE)){
								idEpi=episodiosV.get(i).getId_episodio();
						}
					}
					if(controller.marcarComoVisto(idEpi)){
						JOptionPane.showMessageDialog(null,"Episodio Visto");
					}
				}
				dispose();
				UsuariosHome home=new UsuariosHome(controller);
		        
			}
		});
		labelFoto.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			
				JOptionPane.showMessageDialog(null,"Modificación datos personales");
				UsuariosModifyData modi=new UsuariosModifyData(controller);
				dispose();
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
		petList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String x=(String) petList.getSelectedItem();
				
				int initInt=x.indexOf("-");
				int id=-1;
				x.replaceAll("\\s+","");
				String y=x.substring(initInt+1);
				try {
					id=Integer.parseInt(y.replaceAll("\\s+",""));
				} catch (Exception ef) {
					id=-1;
				}
				
				
				
				if(id!=-1){
					
					Serie serieB = null;
					for (int i = 0; i < seriesF.size(); i++) {
						if(seriesF.get(i).getId_serie()==id){
							serieB=seriesF.get(i);
							break;
						}
						
					}
					if(chekcVisto.isSelected()){
						episodiosV=controller.episodiosXserieV(serieB.getId_serie());
					}
					else{
						
						episodiosV=controller.episodiosXserie(serieB.getId_serie());
					}
					newInventory=new String[episodiosV.size()][4];
					int i=0;
					while(i<episodiosV.size()){
						newInventory[i][0]= String.valueOf(episodiosV.get(i).getId_episodio());
						newInventory[i][1]=String.valueOf(episodiosV.get(i).getNum_temp());
						newInventory[i][2]=episodiosV.get(i).getTitulo();
						newInventory[i][3]=episodiosV.get(i).getFech().toLocaleString();
						
						i++;
					}
					model= new MyTableModel(newInventory, COL_NAME);
					table.setModel(model);
				}
				
				
				
			}
		});
	}
}

