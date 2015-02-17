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

public class NuevaSerie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminController controller;
	JPanel panel = new JPanel();
	//fechas estreno-final
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	UtilDateModel model2 = new UtilDateModel();
	JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
	JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
	JLabel lF1=new JLabel("Año Estreno");
	JLabel lF2=new JLabel("Año Fin");
	//nombre, titular, sinopsis
	JLabel lNom=new JLabel("Nombre:");
	JTextField tNom=new JTextField();
	JLabel lTitular = new JLabel("Titular:");
	JTextField tTitular=new JTextField();
	JLabel lSin=new JLabel("Sinopsis:");
	JTextField tSin=new JTextField();
	//botones
	JButton bOK=new JButton("Crear");
	JButton bKO=new JButton("Cerrar");
	
	public NuevaSerie(AdminController control){
		super("Nueva Serie");
		this.controller=control;
		setSize(500,500);
		setLocation(200,50);
		panel.setLayout(null);
		lNom.setBounds(10, 10, 100, 30);
		tNom.setBounds(115, 10, 100, 50);
		lTitular.setBounds(10, 120, 100, 30);
		tTitular.setBounds(115, 120, 300, 100);
		lSin.setBounds(10,230, 100, 30);
		tSin.setBounds(115, 230, 300, 100);
		lF1.setBounds(10, 340, 100, 30);
		datePicker.setBounds(115, 340, 150, 30);
		lF2.setBounds(10, 380, 100, 30);
		datePicker2.setBounds(115, 380, 150, 30);
		bOK.setBounds(150, 420, 100, 30);
		bKO.setBounds(260, 420, 100, 30);
		panel.add(lNom);
		panel.add(tNom);
		panel.add(lTitular);
		panel.add(tTitular);
		panel.add(lSin);
		panel.add(tSin);
		panel.add(lF1);
		panel.add(datePicker);
		panel.add(lF2);
		panel.add(datePicker2);
		panel.add(bOK);
		panel.add(bKO);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionNuevaSerie();
	}
	public void actionNuevaSerie(){
		bOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Date fecha =model.getValue();
				if(fecha!=null && tNom.getText()!="" && tTitular.getText()!="" && tSin.getText()!=""){
					java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
					Date fecha2 =model2.getValue();
					java.sql.Date sqlDate2;
					boolean ok=false;
					if(fecha2!=null){
						sqlDate2= new java.sql.Date(fecha2.getTime());
						ok=controller.insertarSerie(tNom.getText(),  tSin.getText(), tTitular.getText(), sqlDate, sqlDate2);
					}
					else{
						ok=controller.insertarSerie(tNom.getText(),  tSin.getText(), tTitular.getText(), sqlDate, null);
					}
					if(ok){
						JOptionPane.showMessageDialog(null,"Alta Correcta");
					}
					else{
						JOptionPane.showMessageDialog(null,"Serie existente");
					}
					
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Fecha inicio, Nombre, Titular, Sinopsis son obligatorios");
				}
				AdminHome adHome=new AdminHome();
				
				
			}
		});
		bKO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AdminHome us= new AdminHome();
				dispose();
			}
		});
	}
	
}
