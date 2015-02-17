package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Actor;
import model.Episodio;
import model.Personaje;
import model.PersonajeActor;
import controllers.AdminController;
import controllers.UsuarioController;

public class EpisodiosInfo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsuarioController controller;
	public AdminController controller2;
	JLabel lNom=new JLabel("Orden:");
	JTextField tNom=new JTextField();
	JLabel lTitular = new JLabel("Titulo");
	JTextField tTitular=new JTextField();
	JLabel lSin=new JLabel("Sinopsis");
	JTextField tSin=new JTextField();
	JLabel lFe=new JLabel("Fecha:");
	JTextField tFe=new JTextField();
	//botones
	
	JButton bKO=new JButton("Cerrar");
	JPanel panel = new JPanel();
	static final int FPS_MIN = 0;
	static final int FPS_MAX = 10;
	static final int FPS_INIT = 5;
	JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
            FPS_MIN, FPS_MAX, FPS_INIT);
	JLabel lVotar= new JLabel("Episodio");
	JButton bVotar= new JButton("VOTAR");
	JLabel lMedia= new JLabel("Media de votos: ");
	//EPISODIOS 
	private static final String[] COL_NAME = { "Nombre", "Temp","Titulo","Fecha" };
	MyTableModel model=new MyTableModel(null,COL_NAME);
	JTable table = new JTable(model);
	JLabel lAct= new JLabel("Actores:");
	//comentarios
	JButton bAddComent= new JButton("Add comentario");
	JButton bEditar=new JButton("Actualizar info");
	JTextField fielComentario=new JTextField();
	JLabel Comentarios=new JLabel("Comentarios:");
	JLabel Coment=new JLabel("Comenta:");
	DefaultListModel<String> listModel=new DefaultListModel<String>();
	JList lista= new JList();
	Episodio episodio;
	long idSerie;
	public EpisodiosInfo(Episodio epi, UsuarioController control){
		super("Episodio");
		this.controller=control;
		setSize(1100,700);
		setLocation(50,50);
		episodio=epi;
		
		panel.setLayout(null);
		lNom.setBounds(10, 10, 100, 30);
		tNom.setBounds(115, 10, 100, 50);
		tNom.setText(String.valueOf(epi.getNum_orden()));
		lTitular.setBounds(10, 120, 100, 30);
		tTitular.setBounds(115, 120, 300, 100);
		tTitular.setText(epi.getTitulo());
		lSin.setBounds(10,230, 100, 30);
		tSin.setBounds(115, 230, 300, 100);
		tSin.setText(epi.getSinopsis());
		lFe.setBounds(10, 340, 100, 30);
		tFe.setBounds(115, 340, 200, 30);
		tFe.setText(epi.getFech().toString());
		bKO.setBounds(150, 380, 200, 20);
		lVotar.setBounds(250, 20, 150, 30);
		framesPerSecond.setBounds(250, 50, 200, 40);
		bVotar.setBounds(150, 420, 200, 20);
		lMedia.setBounds(10, 60, 250, 30);
		table.setBounds(500, 50, 250, 200);
		lAct.setBounds(500, 30, 100, 20);
		lista.setBounds(500, 270,250, 150);
		Comentarios.setBounds(500, 250, 100, 20);
		Coment.setBounds(500, 460, 100, 20);
		fielComentario.setBounds(500, 480, 200, 150);
		bAddComent.setBounds(780,500,200,20);
		panel.add(lNom);
		panel.add(tNom);
		panel.add(lTitular);
		panel.add(tTitular);
		panel.add(lSin);
		panel.add(tSin);
		panel.add(lFe);
		panel.add(tFe);
		panel.add(bKO);
		panel.add(lVotar);
		panel.add(bVotar);
		panel.add(framesPerSecond);
		panel.add(lMedia);
		panel.add(table);
		panel.add(lista);
		panel.add(Comentarios);
		panel.add(Coment);
		panel.add(lAct);
		panel.add(fielComentario);
		panel.add(bAddComent);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionInfo();
		setData();
	}
	
	String[] petStrings = { "---"};
	JComboBox comboAct = new JComboBox(petStrings);
	JComboBox comboPer = new JComboBox(petStrings);
	List<Actor> listAct;
	List<Personaje> listPer;
	JLabel lTitulo= new JLabel("Actores / Personajes / Episodio");
	JLabel lSelAct= new JLabel("Selecciona Actor");
	JButton aniadeActor= new JButton("ADD ACT/PER TO EPI");
	JLabel lSelPer= new JLabel("Selecciona Personaje");
	public EpisodiosInfo(Episodio epi, AdminController controller2, long idSeriev) {
		super("Episodio");
		this.controller2=controller2;
		setSize(1100,700);
		setLocation(50,50);
		episodio=epi;
		idSerie=idSeriev;
		panel.setLayout(null);
		lNom.setBounds(10, 10, 100, 30);
		tNom.setBounds(115, 10, 100, 50);
		tNom.setText(String.valueOf(epi.getNum_orden()));
		lTitular.setBounds(10, 120, 100, 30);
		tTitular.setBounds(115, 120, 300, 100);
		tTitular.setText(epi.getTitulo());
		lSin.setBounds(10,230, 100, 30);
		tSin.setBounds(115, 230, 300, 100);
		tSin.setText(epi.getSinopsis());
		lFe.setBounds(10, 340, 100, 30);
		tFe.setBounds(115, 340, 200, 30);
		tFe.setText(epi.getFech().toString());
		bKO.setBounds(150, 380, 200, 20);
		bEditar.setBounds(150, 410, 200, 20);
		//framesPerSecond.setBounds(250, 50, 200, 40);
		
		lMedia.setBounds(10, 60, 250, 30);
		table.setBounds(500, 50, 250, 200);
		lAct.setBounds(500, 30, 100, 20);
		lista.setBounds(500, 270,250, 150);
		Comentarios.setBounds(500, 250, 100, 20);
		Coment.setBounds(500, 460, 100, 20);
		fielComentario.setBounds(500, 480, 200, 150);
		bAddComent.setBounds(780,500,200,20);
		comboAct.setBounds(900,100,150,20);
		comboPer.setBounds(900,150, 150, 20);
		lTitulo.setBounds(850,40,250,20);
		lSelAct.setBounds(770, 100, 120, 20);
		lSelPer.setBounds(770, 150, 140, 20);
		aniadeActor.setBounds(800, 300, 250, 30);
		panel.add(aniadeActor);
		panel.add(lSelAct);
		panel.add(lSelPer);
		panel.add(lTitulo);
		panel.add(comboAct);
		panel.add(comboPer);
		panel.add(lNom);
		panel.add(tNom);
		panel.add(lTitular);
		panel.add(tTitular);
		panel.add(lSin);
		panel.add(tSin);
		panel.add(lFe);
		panel.add(tFe);
		panel.add(bKO);
		panel.add(bEditar);
		//panel.add(framesPerSecond);
		panel.add(lMedia);
		panel.add(table);
		panel.add(lista);
		panel.add(Comentarios);
		panel.add(Coment);
		panel.add(lAct);
		panel.add(fielComentario);
		panel.add(bAddComent);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionInfo2();
		setData2();
	}
	public void setData2(){
		float votos=controller2.mediaEpisodioVotos(episodio.getId_episodio());
		lMedia.setText("Media de Votos:   "+votos);
		String[][] newInventory=null;
		List<PersonajeActor> act=this.controller2.listaActores(episodio.getId_episodio());
		newInventory=new String[act.size()][2];
		int i=0;
		while(i<act.size()){
			newInventory[i][0]= String.valueOf(act.get(i).getNomArt());
			newInventory[i][1]=String.valueOf(act.get(i).getNomPers());
			i++;
		}
		model= new MyTableModel(newInventory, COL_NAME);
		table.setModel(model);
		listModel.clear();
		List<String> ser=controller2.listaComentsEpisodio(episodio.getId_episodio());
		if(ser.size()!=0){
			for(int n=0;n<ser.size();n++){
				listModel.add(n, ser.get(n).toString());
				
			}
			lista.setModel(listModel);
		}else{
			JOptionPane.showMessageDialog(null,"El episodio no tiene comentarios");
		}
		listAct=controller2.listaTodosActores();
		for (int j = 0; j < listAct.size(); j++) {
			comboAct.addItem(listAct.get(j).getNom_artistico());
		}
		listPer=controller2.listaTodosPersonajes();
		for (int j = 0; j < listPer.size(); j++) {
			comboPer.addItem(listPer.get(j).getNombre());
		}
		
	}
	public void setData(){
		float votos=controller.mediaEpisodioVotos(episodio.getId_episodio());
		lMedia.setText("Media de Votos:   "+votos);
		String[][] newInventory=null;
		List<PersonajeActor> act=this.controller.listaActores(episodio.getId_episodio());
		newInventory=new String[act.size()][2];
		int i=0;
		while(i<act.size()){
			newInventory[i][0]= String.valueOf(act.get(i).getNomArt());
			newInventory[i][1]=String.valueOf(act.get(i).getNomPers());
			i++;
		}
		model= new MyTableModel(newInventory, COL_NAME);
		table.setModel(model);
		listModel.clear();
		List<String> ser=controller.listaComentsEpisodio(episodio.getId_episodio());
		
		if(ser.size()!=0){
			for(int n=0;n<ser.size();n++){
				listModel.add(n, ser.get(n).toString());
				
			}
			lista.setModel(listModel);
		}else{
			JOptionPane.showMessageDialog(null,"El episodio no tiene comentarios");
		}
	}
	public void actionInfo2(){
		bKO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AdminHome home= new AdminHome();
				dispose();
			}
		});
		
		aniadeActor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String actName=(String) comboAct.getSelectedItem();
				String perName=(String) comboPer.getSelectedItem();
				if(!actName.equals("---") && !perName.equals("---")){
					long idAct = 0;
					long idPer = 0;
					
					for (int i = 0; i < listAct.size(); i++) {
						if(listAct.get(i).getNom_artistico().equalsIgnoreCase(actName)){
							idAct=listAct.get(i).getId_actor();
						}
					}
					for (int i = 0; i < listPer.size(); i++) {
						if(listPer.get(i).getNombre().equalsIgnoreCase(actName)){
							idPer=listPer.get(i).getId_personaje();
						}
					}
					if(controller2.insertaEpiActPer(episodio.getId_episodio(), idAct, idPer)){
						JOptionPane.showMessageDialog(null,"Alta Correcta Per / Act/ Epi añadido");
						AdminHome home= new AdminHome();
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null,"Ya actua en este episodio");
					}
				}else{
					JOptionPane.showMessageDialog(null,"Selecciona un actor y un personaje para la serie");
				}
			}
		});
		bEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int orden = 0;
				try{
					orden=Integer.parseInt(tNom.getText());
					if(controller2.actualizarEpisodio(idSerie, episodio.getId_episodio(),
							orden, episodio.getNum_temp(), tSin.getText(), tTitular.getText(), episodio.getFech())){
						JOptionPane.showMessageDialog(null,"Episodio Actualizado");
					}else{
						JOptionPane.showMessageDialog(null,"Episodio no Actualizado");
					}
					
				}catch(NumberFormatException ef){
					JOptionPane.showMessageDialog(null,"El orden tiene que ser un número");
				}
				AdminHome home= new AdminHome();
				dispose();
				
			}
		});
	}
	public void actionInfo(){
		bKO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UsuariosHome home= new UsuariosHome(controller);
				dispose();
			}
		});
		bVotar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int valor=framesPerSecond.getValue();
				if(controller.votarEpisodio(episodio.getId_episodio(),valor)){
					JOptionPane.showMessageDialog(null,"Episodio Votado:   "+valor);
				}
				else{
					JOptionPane.showMessageDialog(null,"Episodio No votado");
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
					if(controller.comentarEpisodio(episodio.getId_episodio(),coment)){
						JOptionPane.showMessageDialog(null,"Episodio Comentada");
					}
					else{
						JOptionPane.showMessageDialog(null,"Episodio no Comentada");
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
