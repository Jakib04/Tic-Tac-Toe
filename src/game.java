import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.GridLayout;



public class game extends JPanel {

	char playerMark = 'x';
	JButton[] buttons = new JButton[9];
	
	public game() {
		setLayout(new GridLayout(3,3));
		initButtons();
		
	}
	
	public void initButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText("-");
            buttons[i].setBackground(Color.white);
            buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent a) {
					JButton buttonClicked = (JButton) a.getSource(); 
			        buttonClicked.setText(String.valueOf(playerMark));
			        
			        
			        if(playerMark == 'x') {
			        	playerMark = 'o';
			        	buttonClicked.setBackground(Color.RED);
			        }
			        else {
			        	playerMark ='x';
			        	buttonClicked.setBackground(Color.GREEN);
			        }
			        winner();
			        
					
				}
			});
            
            add(buttons[i]); //adds this button to JPanel        
        }
    }
public void winner() {
		
		if(checkWinner() == true) {
			
			
			if(playerMark == 'x') playerMark = 'o';
	        else playerMark ='x';
			
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, playerMark + " wins. Play again?","Game Finished.",
					JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION) resetButtons();
			else System.exit(0);
		}
		
		else if(checkDraw()) {
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Play again?","Game over.", JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION)  resetButtons();
			else System.exit(0);
		}
	}
	
	
	
	// method used to reset the buttons
	// so you can play again
	private void resetButtons() {
		playerMark = 'x';
		for(int i =0;i<9;i++) {
			  
			  buttons[i].setText("-");
			  buttons[i].setBackground(Color.white);
			  
		  }	
	}

	// checks for draw
	
	public boolean checkDraw() {
		boolean full = true;
		for(int i = 0 ; i<9;i++) {
			if(buttons[i].getText().charAt(0) == '-') {
				full = false;
			}
		}
		return full;
	}
	
	// checks for a winner
		public boolean checkWinner() {
			if(checkRows() == true || checkColumns() == true || checkDiagonals() == true) return true;
			else return false;
		}
		
		
		public boolean checkRows() {
			int i = 0;
			for(int j = 0;j<3;j++) {
			if( buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) 
					&& buttons[i].getText().charAt(0) != '-') {
				return true;
			}
			i = i+3;
			
		}
			return false;
	}
		
		
		
		public boolean checkColumns() {
			
			int i = 0;
			for(int j = 0;j<3;j++) {
			if( buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())
					&& buttons[i].getText().charAt(0) != '-') 
			{
				return true;
			}
			i++;
			}
			return false;	
		}
		

		public boolean checkDiagonals() {
			if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
					&& buttons[0].getText().charAt(0) !='-')
				return true;
			else if(buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
					&& buttons[2].getText().charAt(0) !='-') return true;
				
			else return false;
		}
	
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new game()); 
        window.setBounds(1000,1000,1000,1000); 
        window.setVisible(true); 
        window.setLocationRelativeTo(null); 

	}

}
