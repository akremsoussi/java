import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	Random random = new Random();

	JFrame frame = new JFrame();
	JPanel title = new JPanel();
	JPanel panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player1_turn;



	TicTacToe(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("TicTacToe");
		textfield.setOpaque(true);

		title.setLayout(new BorderLayout());
		title.setBounds(0, 0, 800, 100);

		panel.setLayout(new GridLayout(3,3));
		panel.setBackground(new Color(150, 150, 150));

		for(int i = 0;i<9;i++) {
			buttons[i] = new JButton();
			panel.add(buttons[i]);
			buttons[i].setFont(new Font("My Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);

		}


		title.add(textfield);
		frame.add(title, BorderLayout.NORTH);
		frame.add(panel);



		firstTurn();

	}

	public void actionPerformed(ActionEvent e) {


		for(int i=0;i<9;i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn=false;
						textfield.setText("O's turn");
						check();
					}
				}
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						player1_turn=true;
						textfield.setText("X's turn");
						check();
					}
				}
			}
		}

	}

	public void firstTurn() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		if(random.nextInt(2)==0) {
			player1_turn = true;
			textfield.setText("X's turn");
		}
		else {
			player1_turn = false;
			textfield.setText("O's turn");

		}
	}

	public void check() {
		if(
				(buttons[0].getText()=="X" ) &&
				(buttons[1].getText()=="X" ) &&
				(buttons[2].getText()=="X" )
				) {
			xWins(0,1,2);

		}
		if(
				(buttons[3].getText()=="X" ) &&
				(buttons[4].getText()=="X" ) &&
				(buttons[5].getText()=="X" )
				) {
			xWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="X" ) &&
				(buttons[7].getText()=="X" ) &&
				(buttons[8].getText()=="X" )
				) {
			xWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="X" ) &&
				(buttons[3].getText()=="X" ) &&
				(buttons[6].getText()=="X" )
				) {
			xWins(0,3,6);
		}
		if(
				(buttons[4].getText()=="X" ) &&
				(buttons[1].getText()=="X" ) &&
				(buttons[7].getText()=="X" )
				) {
			xWins(1,4,7);
		}
		if(
				(buttons[5].getText()=="X" ) &&
				(buttons[8].getText()=="X" ) &&
				(buttons[2].getText()=="X" )
				) {
			xWins(2,8,5);
		}
		if(
				(buttons[0].getText()=="X" ) &&
				(buttons[4].getText()=="X" ) &&
				(buttons[8].getText()=="X" )
				) {
			xWins(0,4,8);
		}
		if(
				(buttons[6].getText()=="X" ) &&
				(buttons[4].getText()=="X" ) &&
				(buttons[2].getText()=="X" )
				) {
			xWins(2,4,6);
		}
		if(
				(buttons[0].getText()=="O" ) &&
				(buttons[1].getText()=="O" ) &&
				(buttons[2].getText()=="O" )
				) {
			oWins(0,1,2);

		}
		if(
				(buttons[3].getText()=="O" ) &&
				(buttons[4].getText()=="O" ) &&
				(buttons[5].getText()=="O" )
				) {
			oWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="O" ) &&
				(buttons[7].getText()=="O" ) &&
				(buttons[8].getText()=="O" )
				) {
			xWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="O" ) &&
				(buttons[3].getText()=="O" ) &&
				(buttons[6].getText()=="O" )
				) {
			oWins(0,3,6);
		}
		if(
				(buttons[4].getText()=="O" ) &&
				(buttons[1].getText()=="O" ) &&
				(buttons[7].getText()=="O" )
				) {
			oWins(1,4,7);
		}
		if(
				(buttons[5].getText()=="O" ) &&
				(buttons[8].getText()=="O" ) &&
				(buttons[2].getText()=="O" )
				) {
			oWins(2,8,5);
		}
		if(
				(buttons[0].getText()=="O" ) &&
				(buttons[4].getText()=="O" ) &&
				(buttons[8].getText()=="O" )
				) {
			oWins(0,4,8);
		}
		if(
				(buttons[6].getText()=="O" ) &&
				(buttons[4].getText()=="O" ) &&
				(buttons[2].getText()=="O" )
				) {
			oWins(2,4,6);
		}

	}

	public void xWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		for(int i = 0; i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
	}

	public void oWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		for(int i = 0; i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");

	}
}
