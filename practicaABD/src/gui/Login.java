package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controllers.UsuarioController;

public class Login  extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsuarioController controller;
	

	JButton blogin = new JButton("Aceptar");
	JButton bnuevoUsuario = new JButton("Nuevo Usuario");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);

	public Login(){
		super("LOG");
		setSize(300,200);
		setLocation(500,280);
		panel.setLayout (null);


		txuser.setBounds(70,30,150,20);
		pass.setBounds(70,65,150,20);
		//x y width height 
		
		blogin.setBounds(5,100,120,20);
		bnuevoUsuario.setBounds(160, 100, 120, 20);
		
		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		panel.add(bnuevoUsuario);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
		controller=new UsuarioController(this);
	}
	public void sacaPanel(String msg){
		JOptionPane.showMessageDialog(null,msg);
		//this.setVisible(false);
	}
	public void actionlogin(){
		blogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				@SuppressWarnings("deprecation")
				String ppaswd = pass.getText();
				if(controller.logarse(puname, ppaswd)){
					
					UsuariosHome regFace =new UsuariosHome(controller);
					regFace.setVisible(true);
					dispose();
				}
				else{
					sacaPanel("Datos incorrectos");
				}
			}
		});
		bnuevoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String puname = txuser.getText();
				@SuppressWarnings("deprecation")
				String ppaswd = pass.getText();
				if(controller.newUser(puname, ppaswd)){
					sacaPanel("Usuario creado logate");
					txuser.setText("");
					pass.setText("");
				}
				else{
					sacaPanel("El usuario ya existe en la bbdd");
				}
			}
		});
	}

}
/*if(puname.equals("test") && ppaswd.equals("12345")) {
UsuariosHome regFace =new UsuariosHome();
regFace.setVisible(true);
dispose();
} 
else{
JOptionPane.showMessageDialog(null,"Wrong Password / Username");
txuser.setText("");
pass.setText("");
txuser.requestFocus();
}*/
