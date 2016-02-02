package com.cimpApps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



@SuppressWarnings("serial")
public class KeysInput extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	
	private JTextField textFieldUp1;
	private JTextField textFieldDown1;
	private JTextField textFieldLeft1;
	private JTextField textFieldRight1;
	private JTextField textFieldShoot1;
	
	private JTextField textFieldUp2;
	private JTextField textFieldDown2;
	private JTextField textFieldLeft2;
	private JTextField textFieldRight2;
	private JTextField textFieldShoot2;
	
	private int keyUp1, keyDown1, keyLeft1, keyRight1, keyShoot1;
	private int keyUp2, keyDown2, keyLeft2, keyRight2, keyShoot2;
	
	//private JTextField[] textFields = {textFieldUp1, textFieldDown1, textFieldLeft1, textFieldRight1, textFieldShoot1,
			//textFieldUp2, textFieldDown2, textFieldLeft2, textFieldRight2, textFieldShoot2};
	//private int [] keyCodes = {keyUp1, keyDown1, keyLeft1, keyRight1, keyShoot1,
			//keyUp2, keyDown2, keyLeft2, keyRight2, keyShoot2};
	private String key;
	
	private final class FL implements FocusListener {
		Color background = Color.LIGHT_GRAY;
		@Override
		public void focusGained(FocusEvent fo) {
			background = fo.getComponent().getBackground();
			fo.getComponent().setBackground(Color.ORANGE);
		}
		public void focusLost(FocusEvent arg0) {
			arg0.getComponent().setBackground(background);
		}
	}

	private final class KeyCommandListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			
			key = KeyEvent.getKeyText(e.getKeyCode());
			if(e.getSource() instanceof JTextField){
				JTextField jtf = (JTextField)e.getComponent();
				jtf.setText(key);
				int code = e.getKeyCode();
//				for (int i = 0; i < textFields.length; i++) {
//					if (textFields[i] == e.getSource()) {
//						keyCodes[i] = code;
//						System.out.println(keyCodes[i]);
//						return;
//					}
//				
//				}//end of for
				switch(code){
					case 
				}
				if (jtf == textFieldUp1){
					setKeyUp1(code);
					return;
				}
				else if ( jtf == textFieldDown1){
					setKeyDown1(code);
					return;
				}
				else if (jtf == textFieldLeft1) {
					setKeyLeft1(code);
				}
				else if (jtf == textFieldRight1){
					setKeyRight1(code);
					return;
				}
				else if (jtf == textFieldShoot1) {
					setKeyShoot1(code);
					return;
				}
				else if (jtf == textFieldUp2){
					setKeyUp2(code);
					return;
				}
				else if ( jtf == textFieldDown2){
					setKeyDown2(code);
				}
				else if (jtf == textFieldLeft2) {
					setKeyLeft2(code);
					return;
				}
				else if (jtf == textFieldRight2){
					setKeyRight2(code);
				}
				else if (jtf == textFieldShoot2) {
					setKeyShoot2(code);
					return;
				}
			}//end of if
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KeysInput dialog = new KeysInput();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	



	/**
	 * Create the dialog.
	 */
	public KeysInput() {
		setTitle("Keysboard Setup");
		setBounds(100, 100, 450, 312);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setFocusable(true);
		FL fl = new FL();
	
		keyUp1 = KeyEvent.VK_UP;
		keyDown1 = KeyEvent.VK_DOWN;
		keyLeft1 = KeyEvent.VK_LEFT;
		keyRight1 = KeyEvent.VK_RIGHT;
		keyShoot1 = KeyEvent.VK_SPACE;
		keyUp2 = KeyEvent.VK_W;
		keyDown2 = KeyEvent.VK_S;
		keyLeft2 = KeyEvent.VK_A;
		keyRight2 = KeyEvent.VK_D;
		keyShoot2 = KeyEvent.VK_SHIFT;
	
		JLabel lblGoup = new JLabel("Go Up");
		lblGoup.setForeground(Color.RED);
		lblGoup.setBounds(5, 74, 42, 15);
		contentPanel.add(lblGoup);
	
		textFieldUp1 = new JTextField();
		textFieldUp1.addFocusListener(fl);
		textFieldUp1.setForeground(Color.RED);
		textFieldUp1.setEditable(false);
		textFieldUp1.setBounds(110, 72, 71, 19);
		contentPanel.add(textFieldUp1);
		textFieldUp1.setColumns(10);
		textFieldUp1.addKeyListener(new KeyCommandListener());
	
		JLabel lblGoDown = new JLabel("Go Down");
		lblGoDown.setForeground(Color.RED);
		lblGoDown.setBounds(5, 115, 76, 15);
		contentPanel.add(lblGoDown);
	
		JLabel lblGoLeft = new JLabel("Go Left");
		lblGoLeft.setForeground(Color.RED);
		lblGoLeft.setBounds(5, 163, 51, 15);
		contentPanel.add(lblGoLeft);
		
		textFieldDown1 = new JTextField();
		textFieldDown1.addFocusListener(fl);
		textFieldDown1.setForeground(Color.RED);
		textFieldDown1.setEditable(false);
		textFieldDown1.addKeyListener(new KeyCommandListener());
		textFieldDown1.setColumns(10);
		textFieldDown1.setBounds(110, 113, 71, 19);
		contentPanel.add(textFieldDown1);
		
		textFieldLeft1 = new JTextField();
		textFieldLeft1.addFocusListener(fl);
		textFieldLeft1.setForeground(Color.RED);
		textFieldLeft1.setEditable(false);
		textFieldLeft1.setColumns(10);
		textFieldLeft1.setBounds(110, 161, 71, 19);
		contentPanel.add(textFieldLeft1);
		textFieldLeft1.addKeyListener(new KeyCommandListener());
		
		JLabel lblGoRight = new JLabel("Go Right");
		lblGoRight.setForeground(Color.RED);
		lblGoRight.setBounds(5, 208, 76, 15);
		contentPanel.add(lblGoRight);
		
		textFieldRight1 = new JTextField();
		textFieldRight1.addFocusListener(fl);
		textFieldRight1.setForeground(Color.RED);
		textFieldRight1.setEditable(false);
		textFieldRight1.setColumns(10);
		textFieldRight1.setBounds(110, 206, 71, 19);
		contentPanel.add(textFieldRight1);
		textFieldRight1.addKeyListener(new KeyCommandListener());
		
		JLabel label_1 = new JLabel("Go Down");
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(262, 113, 76, 15);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Go Left");
		label_2.setForeground(Color.BLUE);
		label_2.setBounds(262, 161, 51, 15);
		contentPanel.add(label_2);
		
		JLabel lblGoRight_1 = new JLabel("Go Right");
		lblGoRight_1.setForeground(Color.BLUE);
		lblGoRight_1.setBounds(262, 206, 76, 15);
		contentPanel.add(lblGoRight_1);
		
		JLabel label_4 = new JLabel("Go Up");
		label_4.setForeground(Color.BLUE);
		label_4.setBounds(262, 72, 42, 15);
		contentPanel.add(label_4);
		
		textFieldUp2 = new JTextField();
		textFieldUp2.addFocusListener(fl);
		textFieldUp2.setForeground(Color.BLUE);
		textFieldUp2.setEditable(false);
		textFieldUp2.setColumns(10);
		textFieldUp2.setBounds(367, 70, 71, 19);
		textFieldUp2.addKeyListener(new KeyCommandListener());
		contentPanel.add(textFieldUp2);
		
		textFieldDown2 = new JTextField();
		textFieldDown2.addFocusListener(fl);
		textFieldDown2.setForeground(Color.BLUE);
		textFieldDown2.setEditable(false);
		textFieldDown2.setColumns(10);
		textFieldDown2.setBounds(367, 111, 71, 19);
		textFieldDown2.addKeyListener(new KeyCommandListener());
		contentPanel.add(textFieldDown2);
		
		textFieldLeft2 = new JTextField();
		textFieldLeft2.addFocusListener(fl);
		textFieldLeft2.setForeground(Color.BLUE);
		textFieldLeft2.setEditable(false);
		textFieldLeft2.setColumns(10);
		textFieldLeft2.setBounds(367, 159, 71, 19);
		textFieldLeft2.addKeyListener(new KeyCommandListener());
		contentPanel.add(textFieldLeft2);
		
		textFieldRight2 = new JTextField();
		textFieldRight2.addFocusListener(fl);
		textFieldRight2.setForeground(Color.BLUE);
		textFieldRight2.setEditable(false);
		textFieldRight2.setColumns(10);
		textFieldRight2.setBounds(367, 204, 71, 19);
		textFieldRight2.addKeyListener(new KeyCommandListener());
		contentPanel.add(textFieldRight2);
		
		JLabel lblNewLabel = new JLabel("Player1");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(5, 28, 176, 30);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPlayer = new JLabel("Player2");
		lblPlayer.setForeground(Color.BLUE);
		lblPlayer.setBounds(262, 28, 176, 30);
		contentPanel.add(lblPlayer);
		
		textFieldShoot1 = new JTextField();
		textFieldShoot1.addFocusListener(fl);
		textFieldShoot1.setForeground(Color.RED);
		textFieldShoot1.setEditable(false);
		textFieldShoot1.setColumns(10);
		textFieldShoot1.setBounds(110, 248, 71, 19);
		textFieldShoot1.addKeyListener(new KeyCommandListener());
		contentPanel.add(textFieldShoot1);
		
		JLabel lblShoot = new JLabel("Shoot");
		lblShoot.setForeground(Color.BLUE);
		lblShoot.setBounds(262, 248, 51, 15);
		contentPanel.add(lblShoot);
		
		textFieldShoot2 = new JTextField();
		textFieldShoot2.addFocusListener(fl);
		textFieldShoot2.setForeground(Color.BLUE);
		textFieldShoot2.setEditable(false);
		textFieldShoot2.setColumns(10);
		textFieldShoot2.setBounds(367, 246, 71, 19);
		textFieldShoot2.addKeyListener(new KeyCommandListener());
		contentPanel.add(textFieldShoot2);
		
		JLabel Shoot = new JLabel("Shoot");
		Shoot.setForeground(Color.RED);
		Shoot.setBounds(5, 250, 76, 15);
		contentPanel.add(Shoot);
		
	}

	public int getKeyDown1() {
		return keyDown1;
	}

	public int getKeyDown2() {
		return keyDown2;
	}

	public int getKeyLeft1() {
		return keyLeft1;
	}

	public int getKeyLeft2() {
		return keyLeft2;
	}

	public int getKeyRight1() {
		return keyRight1;
	}

	public int getKeyRight2() {
		return keyRight2;
	}

	public int getKeyUp1() {
		return keyUp1;
	}

	public int getKeyUp2() {
		return keyUp2;
	}

	public int getShoot1() {
		return keyShoot1;
	}

	public int getShoot2() {
		return keyShoot2;
	}

	public void setKeyDown1(int keyDown1) {
		this.keyDown1 = keyDown1;
	}

	public void setKeyDown2(int keyDown2) {
		this.keyDown2 = keyDown2;
	}

	public void setKeyLeft1(int keyLeft1) {
		this.keyLeft1 = keyLeft1;
	}
	public void setKeyLeft2(int keyLeft2) {
		this.keyLeft2 = keyLeft2;
	}
	public void setKeyRight1(int keyRight1) {
		this.keyRight1 = keyRight1;
	}
	public void setKeyRight2(int keyRight2) {
		this.keyRight2 = keyRight2;
	}
	
	public void setKeyUp1(int keyUp1) {
		this.keyUp1 = keyUp1;
	}

	public void setKeyUp2(int keyUp2) {
		this.keyUp2 = keyUp2;
	}

	public void setKeyShoot1(int shoot1) {
		this.keyShoot1 = shoot1;
	}

	public void setKeyShoot2(int shoot2) {
		this.keyShoot2 = shoot2;
	}
}//en5d of class
