package skeleton;

/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class UndoGUI {
	private DefaultListModel listModel;
	private JTextField text;
	private JList list;
	
	private ActionAnnuler annuler;
	private ActionAdd add;
	
	
	UndoGUI() {
		// JFrame
		JFrame fen = new JFrame("Undo/Redo");
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Actions
		annuler = new ActionAnnuler("Annuler");
		annuler.setEnabled(false);
		add = new ActionAdd("Ajouter");
		
		// Liste
		listModel = new DefaultListModel();
		listModel.addElement("Universite Lille 1");
		listModel.addElement("Master informatique");
		listModel.addElement("IHM");
		list = new JList(listModel);
		JScrollPane listScroller = new JScrollPane(list);
		text = new JTextField();
		text.setAction(add);
		JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.setAction(add);
		
		
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Edition");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Supprimer");
		menu.add(menuItem);
		menuItem = new JMenuItem("Annuler");
		menuItem.setAction(annuler);
		menu.add(menuItem);
		menuItem = new JMenuItem("Refaire");
		menu.add(menuItem);
		
		// ToolBar
		JToolBar toolBar = new JToolBar("Barre d'outils");
		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int index = list.getSelectedIndex();
			    if (index != -1) listModel.remove(index);
			} 
		});
		
		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setAction(annuler);
		

		JButton boutonRefaire = new JButton("Refaire");
		boutonRefaire.setAction(annuler);

		toolBar.add(boutonSupprimer);
		toolBar.add(boutonAnnuler);
		toolBar.add(boutonRefaire);
		
		fen.setJMenuBar(menuBar);
		fen.add(toolBar);
		fen.getContentPane().setLayout(new BoxLayout(fen.getContentPane(),BoxLayout.Y_AXIS));
		fen.getContentPane().add(listScroller);
		fen.getContentPane().add(text);
		fen.getContentPane().add(boutonAjouter);
		fen.pack();
		fen.setVisible(true);		
	}
	
	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			new UndoGUI();
		    }
		});
	}
	
	private class ActionAnnuler extends AbstractAction{
		
		public ActionAnnuler(String s){
			super(s);
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("annuler");
		}
	}
	

	private class ActionAdd extends AbstractAction{

		public ActionAdd(String s){
			super(s);
			this.putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_ENTER));
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("Ajouter");
			listModel.addElement(text.getText());
			text.setText("");
		}
		
	}

}
