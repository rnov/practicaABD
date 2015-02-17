package gui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import gui.*;

import model.Episodio;
import model.Serie;
import controllers.AdminController;
import controllers.UsuarioController;

public class UsuariosSeries extends JFrame{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public UsuarioController controller;
	public AdminController controller2;
	JPanel panel = new JPanel();
	JLabel titulo=new JLabel("xxxx");
	JTextField info=new JTextField();
	JLabel genero=new JLabel("Genero:     Undefined");
	JLabel sinopsis=new JLabel("Sinopsis:");
	public long idEpisodio;
	//JTextField textScroll=new JTextField("aki la sinopsis");
	JTextArea textArea = new JTextArea(5, 475);
	JPanel subpanel=new JPanel(null);
	JScrollPane scroll=new JScrollPane(textArea);
	public long idSerie;
	JButton bFolow= new JButton("Seguir Serie");
	JButton bActualizar= new JButton("Actualizar Serie");
	JButton bClose= new JButton("Cerrar");
	JLabel lMedia= new JLabel("Media de votos: ");
	static final int FPS_MIN = 0;
	static final int FPS_MAX = 10;
	static final int FPS_INIT = 5;
	JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
            FPS_MIN, FPS_MAX, FPS_INIT);
	JLabel lVotar= new JLabel("Votar serie");
	JButton bVotar= new JButton("VOTAR");
	DefaultListModel<String> listModel=new DefaultListModel<String>();
	JList lista= new JList();
	JButton bAddComent= new JButton("Add comentario");
	JButton bEditEpisodio= new JButton("Editar Episodio");
	JTextField fielComentario=new JTextField();
	JLabel Comentarios=new JLabel("Comentarios");
	JLabel Coment=new JLabel("Comenta:");
	
	private static final String[] COL_NAME = { "Num", "Temp","Titulo","Fecha" };
	MyTableModel model=new MyTableModel(null,COL_NAME);
	JTable table = new JTable(model);
	JLabel lSeries= new JLabel("Lista Episodios:");
	JButton bAddEpisodio= new JButton("Nuevo Episodio");
	JButton bDelEpisodio=new JButton("Borrar Episodio");
	int tipo;
	Serie serie;
	List<Episodio> episodios;
	public UsuariosSeries(AdminController control, Serie serieB) {
		super("Admin Series");
		this.controller2=control;
		this.idSerie=serieB.getId_serie();
		titulo.setText(serieB.getNombre());
		tipo=0;
		serie=serieB;
		setWindow();
	}
	public UsuariosSeries(UsuarioController control, Serie serieB) {
		super("Usuarios Series");
		this.controller=control;
		this.idSerie=serieB.getId_serie();
		titulo.setText(serieB.getNombre());
		tipo=1;
		serie=serieB;
		setWindow1();
	}
	public void setWindow1(){
		info.setText(serie.getSinopsis());
		textArea.setText(serie.getTitular());
		setSize(1100,700);
		setLocation(100,50);
		panel.setLayout(null);
		subpanel.setLayout(null);
		
		titulo.setBounds(5, 0, 475, 20);
		
		info.setBounds(5, 25, 475, 40);
		
		genero.setBounds(5, 60, 470, 30);
		lMedia.setBounds(150, 60, 250, 30);
		sinopsis.setBounds(5, 95, 470, 30);
		lVotar.setBounds(100, 90, 150, 30);
		framesPerSecond.setBounds(190, 90, 200, 40);
		scroll.setBounds(5, 130,475, 250);
		bFolow.setBounds(50,390, 200, 20);
		bClose.setBounds(255, 390, 200, 20);
		bVotar.setBounds(50, 420, 200, 20);
		lista.setBounds(550, 50, 400, 400);
		Comentarios.setBounds(550, 20, 100, 20);
		Coment.setBounds(550, 460, 100, 20);
		fielComentario.setBounds(550, 480, 200, 150);
		bAddComent.setBounds(780,500,200,20);
		//scroll.add(textScroll);
		
		panel.add(titulo);
		panel.add(info);
		panel.add(genero);
		panel.add(lMedia);
		panel.add(lVotar);
		panel.add(bVotar);
		panel.add(framesPerSecond);
		panel.add(sinopsis);
		panel.add(scroll);
		panel.add(bFolow);
		panel.add(bClose);
		panel.add(lista);
		panel.add(Comentarios);
		panel.add(Coment);
		panel.add(fielComentario);
		panel.add(bAddComent);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setData();
		actionSeries();
	}
	public void setWindow(){
		info.setText(serie.getSinopsis());
		textArea.setText(serie.getTitular());
		setSize(800,700);
		setLocation(200,50);
		panel.setLayout(null);
		subpanel.setLayout(null);
		titulo.setBounds(5, 0, 475, 20);
		info.setBounds(5, 25, 475, 40);
		genero.setBounds(5, 60, 470, 30);
		sinopsis.setBounds(5, 95, 470, 30);
		scroll.setBounds(5, 130,475, 100);
		bActualizar.setBounds(120,550, 100, 20);
	    table.setBounds(10, 260, 455, 250);
	    lSeries.setBounds(10, 240, 200, 20);
		bClose.setBounds(10, 550, 100, 20);
		bAddEpisodio.setBounds(10, 580, 150, 20);
		bDelEpisodio.setBounds(180, 580, 150, 20);
		bEditEpisodio.setBounds(10, 610, 150, 20);
		panel.add(titulo);
		panel.add(info);
		panel.add(genero);
		panel.add(sinopsis);
		panel.add(scroll);
		panel.add(lSeries);
		panel.add(table);
		panel.add(bActualizar);
		panel.add(bClose);
		panel.add(bAddEpisodio);
		panel.add(bDelEpisodio);
		panel.add(bEditEpisodio);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setData2();
		actionSeriesAdmin();
	}
	public void setData(){
		int yearI;
		int yearF;
		String temp = "( ";
		if(serie.getFech_Est()==null) {
			temp+="¿";
		}
		else{
			yearI=serie.getFech_Est().getYear();
			temp+=yearI;
		}
		if(serie.getFech_fin()==null){
			temp+="?";
		}
		else{
			yearF=serie.getFech_fin().getYear();
			temp+=yearF;
		}
		titulo.setText(serie.getNombre()+'('+temp+')');
		float votos=controller.mediaSerieVotos(serie.getId_serie());
		lMedia.setText("Media de Votos:   "+votos);
		List<Episodio> series;	
		series=controller.episodiosXserie(this.idSerie);
		String[][] newInventory=null;
		newInventory=new String[series.size()][4];
		int i=0;
		while(i<series.size()){
			newInventory[i][0]= String.valueOf(series.get(i).getNum_orden());
			newInventory[i][1]=String.valueOf(series.get(i).getNum_temp());
			newInventory[i][2]=series.get(i).getTitulo();
			newInventory[i][3]=String.valueOf(series.get(i).getId_episodio());
			idEpisodio=series.get(i).getId_episodio();
			i++;
		}
		listModel.clear();
		List<String> ser=controller.listaComentsSerie(serie.getId_serie());
		
		for(i=0;i<ser.size();i++){
			listModel.add(i, ser.get(i).toString());
			
		}
		lista.setModel(listModel);
		model= new MyTableModel(newInventory, COL_NAME);
		table.setModel(model);
			
	}
	public void setData2(){
		int yearI;
		int yearF;
		String temp = "( ";
		if(serie.getFech_Est()==null) {
			temp+="¿";
		}
		else{
			yearI=serie.getFech_Est().getYear();
			temp+=yearI;
		}
		if(serie.getFech_fin()==null){
			temp+="?";
		}
		else{
			yearF=serie.getFech_fin().getYear();
			temp+=yearF;
		}
		titulo.setText(serie.getNombre()+'('+temp+')');
		
		float votos=controller2.mediaSerieVotos(serie.getId_serie());
		lMedia.setText("Media de Votos:   "+votos);
		
		
		episodios=controller2.episodiosXserie(this.idSerie);
		String[][] newInventory=null;
		newInventory=new String[episodios.size()][4];
		int i=0;
		while(i<episodios.size()){
			newInventory[i][0]= String.valueOf(episodios.get(i).getNum_orden());
			newInventory[i][1]=String.valueOf(episodios.get(i).getNum_temp());
			newInventory[i][2]=episodios.get(i).getTitulo();
			newInventory[i][3]=String.valueOf(episodios.get(i).getId_episodio());
			idEpisodio=episodios.get(i).getId_episodio();
			i++;
		}
		listModel.clear();
		List<String> ser=controller2.listaComentsSerie(serie.getId_serie());
		
		for(i=0;i<ser.size();i++){
			listModel.add(i, ser.get(i));
			
		}
		lista.setModel(listModel);
		model= new MyTableModel(newInventory, COL_NAME);
		table.setModel(model);
		
	}
	
	public void actionSeriesAdmin(){
		bActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(controller2.actualizarSerie(info.getText(), textArea.getText(), serie.getId_serie(),serie.getNombre(), serie.getFech_Est(),serie.getFech_fin())){
					JOptionPane.showMessageDialog(null,"Actualizada Serie");
				}
				else{
					JOptionPane.showMessageDialog(null,"Serie no actualizada");
				}
				AdminHome us= new AdminHome();
				dispose();
			}
		});
		bClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AdminHome us= new AdminHome();
				dispose();
			}
		});
		bDelEpisodio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				long x=Long.parseLong( model.getValueAt(row, 3).toString());
				if(controller2.delEpisodio(x)){
					JOptionPane.showMessageDialog(null,"Episodio Borrado");
				}
				else{
					JOptionPane.showMessageDialog(null,"Episodio No borrado");
				}
				AdminHome us= new AdminHome();
				dispose();
				
			}
		});
		bEditEpisodio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				long x=Long.parseLong( model.getValueAt(row, 3).toString());
				Episodio epi=null;
				boolean ok=false;
				for (int i = 0; i < episodios.size(); i++) {
					if(x==episodios.get(i).getId_episodio()){
						epi=episodios.get(i);
						ok=true;
					}
				}
				if(ok){
					EpisodiosInfo epi2=new EpisodiosInfo(epi, controller2, serie.getId_serie());
					dispose();
				}else{
					AdminHome us= new AdminHome();
					dispose();
				}
				
				
			}
		});
		bAddEpisodio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NuevoEpisodio nuev=new NuevoEpisodio(controller2, idSerie);
				dispose();
				
			}
		});
	}
	public void actionSeries(){
		bFolow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int rs=controller.altaBajaUsuarioSerie(serie.getId_serie());
				if(rs==1){
					JOptionPane.showMessageDialog(null,"Alta Serie");
				}
				else{
					JOptionPane.showMessageDialog(null,"Baja Serie");
				}
			}
		});
		bClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UsuariosHome us= new UsuariosHome(controller);
				dispose();
			}
		});
		bVotar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int valor=framesPerSecond.getValue();
				if(controller.votarSerie(serie.getId_serie(),valor)){
					JOptionPane.showMessageDialog(null,"Serie Votada:   "+valor);
				}
				else{
					JOptionPane.showMessageDialog(null,"Has votado ya a esta serie");
				}
				UsuariosHome us= new UsuariosHome(controller);
				dispose();
				
			}
		});
		bAddComent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String coment=fielComentario.getText();
				if(coment!=""){
					if(controller.comentarSerie(serie.getId_serie(),coment)){
						JOptionPane.showMessageDialog(null,"Serie Comentada");
					}
					else{
						JOptionPane.showMessageDialog(null,"Serie no Comentada");
					}
					UsuariosHome us= new UsuariosHome(controller);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Debes Rellenar el comentario");
				}
				
				
			}
		});
	}
}
