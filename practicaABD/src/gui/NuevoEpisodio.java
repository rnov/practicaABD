package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import controllers.AdminController;

public class NuevoEpisodio extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminController controller;
	JLabel lNom=new JLabel("Orden:");
	JTextField tNom=new JTextField();
	JLabel lTitular = new JLabel("Temporada:");
	JTextField tTitular=new JTextField();
	JLabel lSin=new JLabel("Sinopsis:");
	JTextField tSin=new JTextField();
	JLabel lFe=new JLabel("Fecha:");
	JTextField tFe=new JTextField();
	JPanel panel = new JPanel();
	JButton bOK=new JButton("Crear");
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	JLabel lF1=new JLabel("Año Estreno");
	public long idSerie;
	JLabel lTit=new JLabel("Titulo:");
	JTextField tTit=new JTextField();
	public NuevoEpisodio(AdminController control, Long idSerie){
		
		super("Nuevo episodio");
		this.idSerie=idSerie;
		this.controller=control;
		setSize(500,600);
		setLocation(200,50);
		panel.setLayout(null);
		lNom.setBounds(10, 10, 100, 30);
		tNom.setBounds(115, 10, 100, 50);
		lTitular.setBounds(10, 120, 100, 30);
		tTitular.setBounds(115, 120, 300, 100);
		lSin.setBounds(10,230, 100, 30);
		tSin.setBounds(115, 230, 300, 100);
		lTit.setBounds(10, 340, 150, 20);
		tTit.setBounds(10, 370, 300, 100);
		datePicker.setBounds(115, 480, 200, 30);
		lF1.setBounds(10, 480, 100, 30);
		bOK.setBounds(150, 530, 100, 30);
	    
		panel.add(lNom);
		panel.add(tNom);
		panel.add(lTitular);
		panel.add(tTitular);
		panel.add(lSin);
		panel.add(tSin);
		panel.add(lTit);
		panel.add(tTit);
		panel.add(lF1);
		panel.add(datePicker);
		
		panel.add(bOK);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionNuevoEpisodio();
	}
	public void actionNuevoEpisodio(){
		bOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Date fecha =model.getValue();
				if(fecha!=null && tNom.getText()!="" && tTitular.getText()!="" && tSin.getText()!=""){
					java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
					try{
						if(controller.addEpisodio(idSerie, Integer.parseInt(tNom.getText()), Integer.parseInt(tTitular.getText()), tSin.getText(), tTit.getText(), sqlDate)){
							JOptionPane.showMessageDialog(null,"Episodio añadido");
						}
						else{
							JOptionPane.showMessageDialog(null,"Episodio no añadido");
						}
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null,"Orden y temporada must be a number");
					}
					
					AdminHome home=new AdminHome();
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Fecha inicio, Nombre, Titular, Sinopsis son obligatorios");
				}
				
				
			}
		});
	}
}
