


import java.io.IOException;
import javax.swing.JOptionPane;
/*
 * Author: Lucy Vass
 * GUI code generated using NetBeans IDE 8.2 and edited in Eclipse
 */

public class NetworkViewGUI extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L; //creating a serial number as JDialog is a serialisable class
	private static Network networkID; //Initialising a network ID variable so it can be referred to within this JDialog
	
	
    public NetworkViewGUI(java.awt.Frame parent, boolean modal, Network network) { //creates a new JDialog for each network which is built/opened
        super(parent, modal);
        
        initComponents(); //calling the constructor to create the components
        networkID = network;	//giving the network an id so it can be referred to within this JDialog
        NetworkName.setText(networkID.networkName); //naming the network so users know which network's JDialog they have open
        
        String numNodes = Integer.toString(networkID.nodes.size()); //calculating initial network statistics
        String numEdges = Integer.toString(networkID.edges.size());
        Double numAv = Network.averageDegree(networkID);
        String numAvF = String.format("%.2f", numAv);
        String hubs = Network.findHubs(networkID);
        //setting initial summary message which gives useful network statistics
        SummaryMessages.setText("Network has: " + numNodes + " nodes, " + numEdges + " edges, and an average degree of "
        		+ numAvF + "\n"+ hubs);
    }

    private void initComponents() { //constructor which creates components of the JDialog

        NetworkName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SummaryMessages = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Node1Input = new javax.swing.JTextField();
        Node2Input = new javax.swing.JTextField();
        AddInteractionButton = new javax.swing.JButton();
        NameofNode = new javax.swing.JTextField();
        FindDegreeButton = new javax.swing.JButton();
        CalculateDegreeDis = new javax.swing.JButton();
        SaveAsTxt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); //closes the window
        
        //Setting the text for the JTextArea and JTextFields
        jLabel2.setText("Network summary:");
        SummaryMessages.setColumns(20);
        SummaryMessages.setRows(5);
        jScrollPane1.setViewportView(SummaryMessages);
        jLabel3.setText("Add interaction:");
        jLabel4.setText("Find node degree:");
        Node1Input.setText("Name of node 1");
        Node2Input.setText("Name of node 2");
        AddInteractionButton.setText("Add");
        NameofNode.setText("Name of node");
        FindDegreeButton.setText("Find degree");
        CalculateDegreeDis.setText("Calculate degree distrubution");
        SaveAsTxt.setText("Save network as text");
        
        //Adding ActionListeners for all interactive parts of the JDialog -ie buttons

        AddInteractionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddInteractionButtonActionPerformed(evt);
            }
        });
        FindDegreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindDegreeButtonActionPerformed(evt);
            }
        });
        CalculateDegreeDis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculateDegreeDisActionPerformed(evt);
            }
        });
        SaveAsTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsTxtActionPerformed(evt);
            }
        });
        
        //Layout - code generated by NetBeans IDE 8.2
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(NetworkName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NameofNode))
                            .addComponent(Node2Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(29, 29, 29)
                                .addComponent(Node1Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FindDegreeButton)
                            .addComponent(AddInteractionButton)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(SaveAsTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CalculateDegreeDis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(NetworkName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Node1Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Node2Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddInteractionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameofNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(FindDegreeButton))
                .addGap(18, 18, 18)
                .addComponent(CalculateDegreeDis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SaveAsTxt)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }                        

    //Performing Network methods when the user clicks a button (triggering an action event)                        

    private void AddInteractionButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        /*
         * This action takes the 2 node names entered in the Node#Input JTextFields
         * It then adds an interaction to the network and prints out a confirmation message
         */
    	String node1 = Node1Input.getText();
    	String node2 = Node2Input.getText();
    	String message = Network.addInteraction(networkID, node1, node2);
    	//Confirmation message of adding the interaction
    	SummaryMessages.append(message + "\n");
    	
    	//Recalculating network statistics
    	String numNodes = Integer.toString(networkID.nodes.size());
        String numEdges = Integer.toString(networkID.edges.size());
        Double numAv = Network.averageDegree(networkID);
        String numAvF = String.format("%.2f", numAv);
        String hubs = Network.findHubs(networkID);
        //setting initial summary message which gives useful network statistics
        SummaryMessages.append("Network now has: " + numNodes + " nodes, " + numEdges + " edges, and an average degree of "
        		+ numAvF + "\n"+ hubs);
    }                                                    

    private void FindDegreeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        //This action takes the text entered in the NameofNode JTextField and uses it to find the node by its name and return its degree 
    	String nodeName = NameofNode.getText();
    	String message = "";
    	int degree = Network.nodeDegree(networkID, nodeName);
    	if (degree == 0){
    		message = "Node doesn't exist";
    	}
    	else {
    		message = Integer.toString(degree);
    	}
    	SummaryMessages.append("Degree of node " + nodeName + " is " + message + "\n");
    }                                                

    private void CalculateDegreeDisActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	//This action calculates the degree distribution and outputs it in a text file
    	String message = "";
    	try {
			message = Network.degreeDistrubution(networkID);
		} catch (IOException e) { //if file can't be creating, catches the exception and creates a pop-up to inform the user
			JOptionPane.showMessageDialog(null, "Can't create file", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	//Message confirms the action and tells the user the filename of the distribution
    	SummaryMessages.append(message + "\n"); 
    }                                                  

    private void SaveAsTxtActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //This action saves the nodes in the network in a text file, allowing the user to easily load up the network they created again
    	String message = "";
    	try {
			message = Network.saveNetwork(networkID);
		} catch (IOException e) { //if file can't be creating, catches the exception and creates a pop-up to inform the user
			JOptionPane.showMessageDialog(null, "Can't create file", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	//Message confirms the action and tells the user the filename of the network data
    	SummaryMessages.append(message + "\n");
    }                                         

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Setting the 'Nimbus' look and feel - code generated by Netbeans IDE 8.2
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NetworkViewGUI. class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NetworkViewGUI. class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NetworkViewGUI. class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetworkViewGUI. class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NetworkViewGUI dialog = new NetworkViewGUI( new javax.swing.JFrame(), true, networkID);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration                  
    private javax.swing.JButton AddInteractionButton;
    private javax.swing.JButton CalculateDegreeDis;
    private javax.swing.JButton FindDegreeButton;
    private javax.swing.JTextField NameofNode;
    private javax.swing.JLabel NetworkName;
    private javax.swing.JTextField Node1Input;
    private javax.swing.JTextField Node2Input;
    private javax.swing.JButton SaveAsTxt;
    private javax.swing.JTextArea SummaryMessages;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
                 
}
