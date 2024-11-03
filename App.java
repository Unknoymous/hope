import javax.swing.*;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("We will win");
        int brdwd= 560;
        int brdht= 640 ;

        JFrame fr= new JFrame(" Angry flap");
        // fr.setVisible(true);
        fr.setSize(brdwd, brdht);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    fp myFp= new fp();
    fr.add(myFp);
    fr.pack();
    myFp.requestFocus();
    fr.setVisible(true);

    }

}
