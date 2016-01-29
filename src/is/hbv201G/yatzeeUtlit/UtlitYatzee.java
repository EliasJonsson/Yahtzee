/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hbv201G.yatzeeUtlit;
import is.hbv201G.yatzeeVirkni.YatzeeLeikur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 * UtlitYatzee sýnir notendaviðmót fyrir yatzee leikinn.
 * @author Elías Jónsson, Háskóli Íslands, elj31@hi.is.
 * @version 4
 * 
 * Keyrsla: ég bjó til .jar skrá sem er í dist með 
 * Clean og build og síðan exportaði úr Netbeans.
 * Notast við OS X Yosemite stýrikerfi og 
 * Java útgáfu 1.7.0_51
 * 
 * Dagsetning: 17/04/2015
 * 
 * Forritið: Sígildi leikurinn, Yatzee.
 * 
 */

/**
 * 
 * Hlutverk UtlitYatzee er að sjá um notendaviðmót
 * spilunarhluta leiksins. En klasinn sér um allt 
 * það sem notandinn sér á meðan að hann spilar Yatzee.
 * Inniheldur JFrame.
 * 
 */
public class UtlitYatzee extends javax.swing.JFrame {
    YatzeeLeikur vYatzeeLeikur;
    /**
    * vYatzeeLeikur er hlutur af taginnu YatzeeLeikur
    * sem inniheldur allar upplýsingarnar um leikinn.
    */
    
    
    JButton[] skorKortHnappar;
    /**
     * skorKortHnappar er hlutur af taginu  JButton sem innheldur
     * alla hnappana sem við kemur skorkortinu eða stigatöflunni.
     */
    
    Timer myTimer;
    /**
     * 
     * myTimer er breyta af taginu Timer sem heldur utan um hversu
     * fljótt tölvan framkvæmir leik sinn.
     * 
     */
    
    
    JLabel[] skorkortLeikmadur;
    /**
     * skorkortLeikmadur er hlutur af taginu JLabel sem inniheldur alla miða
     * sem birta skor á skorkorti yatzee notanda.
     */
    
    JLabel[] skorkortTolva;
    /**
     * skorkortTolva er hlutur af taginu JLabel sem inniheldur alla miða
     * sem birta skor á skorkorti tölvu þegar hún spila yatzee.
     */
    
    Stack valdirLeikir;
    /**
     * valdirLeikir er hlaði sem heldur utan um þá
     * leiki sem spilari hefur spilað.
     */
    
    boolean jMultiPlayer;
    /**
     * jMultiplayer er satt ef spilað er við tölvu en ósatt
     * ef spilað er einn.
     */
    
    
    /**
     * Creates new form UtlitYatzee
     */
    /**
     * 
     * @param multiPlayer heldur utan um það hvort að spilað er við tölvuna
     * eða einn. Ef spilað er við tölvuna er multiPlayer satt annars ósatt.
     */
    public UtlitYatzee(boolean multiPlayer) {
        jMultiPlayer = multiPlayer;
        initVinnsla();
        initComponents();
        initGroupComponents();
        initMyndir();
        initValmyndir();
        
    }
    /**
     * Upphafsstillir aðalValmyndina
     */
    private void initValmyndir(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2,
                dim.height/2-this.getHeight()/2);
        this.setVisible(false);
        if(jMultiPlayer == false)
        {
            strokaUtJon();
        }
    }
    
    
    /**
     * Býr til nýjan leik 
     */  
    private void initVinnsla() {
        vYatzeeLeikur = new YatzeeLeikur(jMultiPlayer);
    }
    
    /**
     * Upphafsstillir viðmótið á skorkortinu hjá leikmanni ef
     * leikmadur er 1 annars hjá tölvu.
     * @param leikmadur 
     */
    private void initSkorkort(boolean leikmadur){
        uppfaeraSkorKort(leikmadur);
        if(leikmadur)
        {
            for (JLabel skor : skorkortLeikmadur) {
                skor.setText("-");
                skor.setForeground(Color.black);
            }
        }
        else
        {
            for (JLabel skor : skorkortTolva) {
                skor.setText("-");
                skor.setForeground(Color.black);
            }
        }
        for (JButton hnappar : skorKortHnappar) {
            hnappar.setEnabled(false);   
        }

    }
    
    /**
     * 
     * Setur myndir af teningunum þegar að nýr leikur er spilaður.
     */
    private void initMyndir(){
        JLabel[] jTeningur = new JLabel[]{jTeningur1,jTeningur2, jTeningur3, jTeningur4, jTeningur5};
        for (JLabel teningur : jTeningur) {;
            teningur.setIcon(new ImageIcon(this.getClass().getResource(
                "Myndir/QuestionDice.png")));
        }
        
    }
    
    /**
     * Hópar tengda viðmótshluti saman 
     */  
    private void initGroupComponents() {
        skorKortHnappar = new JButton[]{jVeljaAsa, jVeljaTvista,jVeljaThrista,
            jVeljaFjarka,jVeljaFimmur,jVeljaSexur,jVeljaEittPar,jVeljaTvoPor,
            jVeljaThreeOfaKind,jVeljaFourOfaKind,jVeljaSmallRow,jVeljaBigRow,jVeljaFullHouse,
            jVeljaTakaSens,jVeljaYatzee};
        skorkortLeikmadur = new JLabel[]{jPlayerBirtaAsa, jPlayerBirtaTvista,jPlayerBirtaThrista,
            jPlayerBirtaFjarka,jPlayerBirtaFimmur,jPlayerBirtaSexur,jPlayerBirtaEittPar,
            jPlayerBirtaTvoPor,jPlayerBirtaThreeOfaKind,jPlayerBirtaFourOfaKind,
            jPlayerBirtaSmallRow,jPlayerBirtaBigRow,jPlayerBirtaFullHouse,
            jPlayerBirtaTakaSensinn,jPlayerBirtaYatzee};
        skorkortTolva = new JLabel[]{jCompBirtaAsa, jCompBirtaTvista,jCompBirtaThrista,
            jCompBirtaFjarka,jCompBirtaFimmur,jCompBirtaSexur,jCompBirtaEittPar,
            jCompBirtaTvoPor,jCompBirtaThreeOfaKind,jCompBirtaFourOfaKind,
            jCompBirtaSmallRow,jCompBirtaBigRow,jCompBirtaFullHouse,
            jCompBirtaTakaSensinn,jCompBirtaYatzee};
        valdirLeikir = new Stack();
        for (JButton skorKortHnappar1 : skorKortHnappar) {
            valdirLeikir.push(skorKortHnappar1);
        }
    }
    /**
     * Gerir skorkortið eða stigatöfluna að einliðaleik ef notandi bað um að
     * fá að spila einliðaleik.
     */
    private void strokaUtJon()
    {
        JLabel[] labelTolva = new JLabel[]{jCompBirtaAsa, jCompBirtaTvista,jCompBirtaThrista,
            jCompBirtaFjarka,jCompBirtaFimmur,jCompBirtaSexur,jCompBirtaEittPar,
            jCompBirtaTvoPor,jCompBirtaThreeOfaKind,jCompBirtaFourOfaKind,
            jCompBirtaSmallRow,jCompBirtaBigRow,jCompBirtaFullHouse,
            jCompBirtaTakaSensinn,jCompBirtaYatzee,userLabel1,userLabel2,
            jCompBirtaSumPart,jCompBirtaBonus,jCompBirtaHeildarSkor};
        for(JLabel tolva: labelTolva)
        {
            tolva.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jNyrLeikur = new javax.swing.JButton();
        jKastaTeningum = new javax.swing.JButton();
        jPlayerBirtaAsa = new javax.swing.JLabel();
        jPlayerBirtaTvista = new javax.swing.JLabel();
        jPlayerBirtaThrista = new javax.swing.JLabel();
        jPlayerBirtaFjarka = new javax.swing.JLabel();
        jPlayerBirtaFimmur = new javax.swing.JLabel();
        jPlayerBirtaSexur = new javax.swing.JLabel();
        jPlayerBirtaTakaSensinn = new javax.swing.JLabel();
        jPlayerBirtaYatzee = new javax.swing.JLabel();
        jTeningur1 = new javax.swing.JLabel();
        jTeningur2 = new javax.swing.JLabel();
        jTeningur3 = new javax.swing.JLabel();
        playerLabel1 = new javax.swing.JLabel();
        userLabel1 = new javax.swing.JLabel();
        jTeningur4 = new javax.swing.JLabel();
        jTeningur5 = new javax.swing.JLabel();
        sumPart = new javax.swing.JLabel();
        bonus = new javax.swing.JLabel();
        jPlayerBirtaBonus = new javax.swing.JLabel();
        jPlayerBirtaSumPart = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        playerLabel2 = new javax.swing.JLabel();
        userLabel2 = new javax.swing.JLabel();
        totalScore = new javax.swing.JLabel();
        jPlayerBirtaHeildarSkor = new javax.swing.JLabel();
        jPlayerBirtaThreeOfaKind = new javax.swing.JLabel();
        jPlayerBirtaFourOfaKind = new javax.swing.JLabel();
        jPlayerBirtaSmallRow = new javax.swing.JLabel();
        jPlayerBirtaBigRow = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPlayerBirtaFullHouse = new javax.swing.JLabel();
        jPlayerBirtaEittPar = new javax.swing.JLabel();
        jPlayerBirtaTvoPor = new javax.swing.JLabel();
        jFjoldiKastaLabel = new javax.swing.JLabel();
        jCompBirtaAsa = new javax.swing.JLabel();
        jCompBirtaTvista = new javax.swing.JLabel();
        jCompBirtaThrista = new javax.swing.JLabel();
        jCompBirtaFjarka = new javax.swing.JLabel();
        jCompBirtaFimmur = new javax.swing.JLabel();
        jCompBirtaSexur = new javax.swing.JLabel();
        jCompBirtaEittPar = new javax.swing.JLabel();
        jCompBirtaTvoPor = new javax.swing.JLabel();
        jCompBirtaThreeOfaKind = new javax.swing.JLabel();
        jCompBirtaFourOfaKind = new javax.swing.JLabel();
        jCompBirtaSmallRow = new javax.swing.JLabel();
        jCompBirtaBigRow = new javax.swing.JLabel();
        jCompBirtaFullHouse = new javax.swing.JLabel();
        jCompBirtaTakaSensinn = new javax.swing.JLabel();
        jCompBirtaYatzee = new javax.swing.JLabel();
        jCompBirtaSumPart = new javax.swing.JLabel();
        jCompBirtaBonus = new javax.swing.JLabel();
        jCompBirtaHeildarSkor = new javax.swing.JLabel();
        jVeljaAsa = new javax.swing.JButton();
        jVeljaTvista = new javax.swing.JButton();
        jVeljaThrista = new javax.swing.JButton();
        jVeljaFjarka = new javax.swing.JButton();
        jVeljaFimmur = new javax.swing.JButton();
        jVeljaSexur = new javax.swing.JButton();
        jVeljaEittPar = new javax.swing.JButton();
        jVeljaTvoPor = new javax.swing.JButton();
        jVeljaThreeOfaKind = new javax.swing.JButton();
        jVeljaFourOfaKind = new javax.swing.JButton();
        jVeljaSmallRow = new javax.swing.JButton();
        jVeljaBigRow = new javax.swing.JButton();
        jVeljaFullHouse = new javax.swing.JButton();
        jVeljaTakaSens = new javax.swing.JButton();
        jVeljaYatzee = new javax.swing.JButton();
        jMenu = new javax.swing.JButton();
        jQuit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jFile = new javax.swing.JMenu();
        jNewGameMenu = new javax.swing.JMenuItem();
        jMainMenu = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jQuitMenu = new javax.swing.JMenuItem();
        jAdgerdir = new javax.swing.JMenu();
        jKastaMenu = new javax.swing.JMenuItem();
        jFrystaTeningaMenu = new javax.swing.JMenu();
        jTeningurMenu1 = new javax.swing.JCheckBoxMenuItem();
        jTeningurMenu2 = new javax.swing.JCheckBoxMenuItem();
        jTeningurMenu3 = new javax.swing.JCheckBoxMenuItem();
        jTeningurMenu4 = new javax.swing.JCheckBoxMenuItem();
        jTeningurMenu5 = new javax.swing.JCheckBoxMenuItem();
        jHelpMenu = new javax.swing.JMenu();
        jRulesMenu = new javax.swing.JMenuItem();
        jHowToPlayMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatzee");

        jNyrLeikur.setText("Byrja nýjan leik");
        jNyrLeikur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNyrLeikurActionPerformed(evt);
            }
        });

        jKastaTeningum.setText("Kasta Teningum");
        jKastaTeningum.setEnabled(false);
        jKastaTeningum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKastaTeningumActionPerformed(evt);
            }
        });

        jPlayerBirtaAsa.setText("-");

        jPlayerBirtaTvista.setText("-");

        jPlayerBirtaThrista.setText("-");

        jPlayerBirtaFjarka.setText("-");

        jPlayerBirtaFimmur.setText("-");

        jPlayerBirtaSexur.setText("-");

        jPlayerBirtaTakaSensinn.setText("-");

        jPlayerBirtaYatzee.setText("-");

        jTeningur1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTeningur1MousePressed(evt);
            }
        });

        jTeningur2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTeningur2MousePressed(evt);
            }
        });

        jTeningur3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTeningur3MousePressed(evt);
            }
        });

        playerLabel1.setText("Þú");

        userLabel1.setText("Jón");

        jTeningur4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTeningur4MousePressed(evt);
            }
        });

        jTeningur5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTeningur5MousePressed(evt);
            }
        });

        sumPart.setForeground(new java.awt.Color(255, 51, 51));
        sumPart.setText("Summa");

        bonus.setForeground(new java.awt.Color(0, 204, 0));
        bonus.setText("Bónus");

        jPlayerBirtaBonus.setForeground(new java.awt.Color(0, 204, 0));
        jPlayerBirtaBonus.setText("0");

        jPlayerBirtaSumPart.setForeground(new java.awt.Color(255, 51, 51));
        jPlayerBirtaSumPart.setText("0");

        playerLabel2.setText("Þú");

        userLabel2.setText("Jón");

        totalScore.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        totalScore.setForeground(new java.awt.Color(0, 51, 255));
        totalScore.setText("Heildarskor");

        jPlayerBirtaHeildarSkor.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jPlayerBirtaHeildarSkor.setForeground(new java.awt.Color(0, 51, 255));
        jPlayerBirtaHeildarSkor.setText("0");

        jPlayerBirtaThreeOfaKind.setText("-");

        jPlayerBirtaFourOfaKind.setText("-");

        jPlayerBirtaSmallRow.setText("-");

        jPlayerBirtaBigRow.setText("-");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPlayerBirtaFullHouse.setText("-");

        jPlayerBirtaEittPar.setText("-");

        jPlayerBirtaTvoPor.setText("-");

        jFjoldiKastaLabel.setText("Fjöldi kasta eftir: 3 ");

        jCompBirtaAsa.setText("-");

        jCompBirtaTvista.setText("-");

        jCompBirtaThrista.setText("-");

        jCompBirtaFjarka.setText("-");

        jCompBirtaFimmur.setText("-");

        jCompBirtaSexur.setText("-");

        jCompBirtaEittPar.setText("-");

        jCompBirtaTvoPor.setText("-");

        jCompBirtaThreeOfaKind.setText("-");

        jCompBirtaFourOfaKind.setText("-");

        jCompBirtaSmallRow.setText("-");

        jCompBirtaBigRow.setText("-");

        jCompBirtaFullHouse.setText("-");

        jCompBirtaTakaSensinn.setText("-");

        jCompBirtaYatzee.setText("-");

        jCompBirtaSumPart.setForeground(new java.awt.Color(255, 51, 51));
        jCompBirtaSumPart.setText("0");

        jCompBirtaBonus.setForeground(new java.awt.Color(0, 204, 0));
        jCompBirtaBonus.setText("0");

        jCompBirtaHeildarSkor.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jCompBirtaHeildarSkor.setForeground(new java.awt.Color(0, 51, 255));
        jCompBirtaHeildarSkor.setText("0");

        jVeljaAsa.setText("Ásar");
        jVeljaAsa.setEnabled(false);
        jVeljaAsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaAsaActionPerformed(evt);
            }
        });

        jVeljaTvista.setText("Tvistar");
        jVeljaTvista.setEnabled(false);
        jVeljaTvista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaTvistaActionPerformed(evt);
            }
        });

        jVeljaThrista.setText("Þristar");
        jVeljaThrista.setEnabled(false);
        jVeljaThrista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaThristaActionPerformed(evt);
            }
        });

        jVeljaFjarka.setText("Fjarkar");
        jVeljaFjarka.setEnabled(false);
        jVeljaFjarka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaFjarkaActionPerformed(evt);
            }
        });

        jVeljaFimmur.setText("Fimmur");
        jVeljaFimmur.setEnabled(false);
        jVeljaFimmur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaFimmurActionPerformed(evt);
            }
        });

        jVeljaSexur.setText("Sexur");
        jVeljaSexur.setEnabled(false);
        jVeljaSexur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaSexurActionPerformed(evt);
            }
        });

        jVeljaEittPar.setText("Eitt par");
        jVeljaEittPar.setEnabled(false);
        jVeljaEittPar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaEittParActionPerformed(evt);
            }
        });

        jVeljaTvoPor.setText("Tvö Pör");
        jVeljaTvoPor.setEnabled(false);
        jVeljaTvoPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaTvoPorActionPerformed(evt);
            }
        });

        jVeljaThreeOfaKind.setText("Þrír eins");
        jVeljaThreeOfaKind.setEnabled(false);
        jVeljaThreeOfaKind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaThreeOfaKindActionPerformed(evt);
            }
        });

        jVeljaFourOfaKind.setText("Fjórir eins");
        jVeljaFourOfaKind.setEnabled(false);
        jVeljaFourOfaKind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaFourOfaKindActionPerformed(evt);
            }
        });

        jVeljaSmallRow.setText("Lítil röð");
        jVeljaSmallRow.setEnabled(false);
        jVeljaSmallRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaSmallRowActionPerformed(evt);
            }
        });

        jVeljaBigRow.setText("Stór röð");
        jVeljaBigRow.setEnabled(false);
        jVeljaBigRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaBigRowActionPerformed(evt);
            }
        });

        jVeljaFullHouse.setText("Fullt hús");
        jVeljaFullHouse.setEnabled(false);
        jVeljaFullHouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaFullHouseActionPerformed(evt);
            }
        });

        jVeljaTakaSens.setText("Taka sénsinn");
        jVeljaTakaSens.setEnabled(false);
        jVeljaTakaSens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaTakaSensActionPerformed(evt);
            }
        });

        jVeljaYatzee.setText("Yatzee");
        jVeljaYatzee.setEnabled(false);
        jVeljaYatzee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVeljaYatzeeActionPerformed(evt);
            }
        });

        jMenu.setText("Aðalvalmynd");
        jMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActionPerformed(evt);
            }
        });

        jQuit.setBackground(new java.awt.Color(153, 153, 153));
        jQuit.setText("Hætta í Yatzee");
        jQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuitActionPerformed(evt);
            }
        });

        jFile.setText("Skrá");

        jNewGameMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jNewGameMenu.setText("Nýr leikur");
        jNewGameMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewGameMenuActionPerformed(evt);
            }
        });
        jFile.add(jNewGameMenu);

        jMainMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMainMenu.setText("Aðalvalmynd");
        jMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMainMenuActionPerformed(evt);
            }
        });
        jFile.add(jMainMenu);
        jFile.add(jSeparator4);

        jQuitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jQuitMenu.setText("Hætta leik");
        jQuitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuitMenuActionPerformed(evt);
            }
        });
        jFile.add(jQuitMenu);

        jMenuBar1.add(jFile);

        jAdgerdir.setText("Aðgerðir");

        jKastaMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jKastaMenu.setText("Kasta Teningum");
        jKastaMenu.setEnabled(false);
        jKastaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKastaMenuActionPerformed(evt);
            }
        });
        jAdgerdir.add(jKastaMenu);

        jFrystaTeningaMenu.setText("Frysta Teninga");

        jTeningurMenu1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jTeningurMenu1.setText("Teningur 1");
        jTeningurMenu1.setEnabled(false);
        jTeningurMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTeningurMenu1ActionPerformed(evt);
            }
        });
        jFrystaTeningaMenu.add(jTeningurMenu1);

        jTeningurMenu2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jTeningurMenu2.setText("Teningur 2");
        jTeningurMenu2.setEnabled(false);
        jTeningurMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTeningurMenu2ActionPerformed(evt);
            }
        });
        jFrystaTeningaMenu.add(jTeningurMenu2);

        jTeningurMenu3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jTeningurMenu3.setText("Teningur 3");
        jTeningurMenu3.setEnabled(false);
        jTeningurMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTeningurMenu3ActionPerformed(evt);
            }
        });
        jFrystaTeningaMenu.add(jTeningurMenu3);

        jTeningurMenu4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jTeningurMenu4.setText("Teningur 4");
        jTeningurMenu4.setEnabled(false);
        jTeningurMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTeningurMenu4ActionPerformed(evt);
            }
        });
        jFrystaTeningaMenu.add(jTeningurMenu4);

        jTeningurMenu5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        jTeningurMenu5.setText("Teningur 5");
        jTeningurMenu5.setEnabled(false);
        jTeningurMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTeningurMenu5ActionPerformed(evt);
            }
        });
        jFrystaTeningaMenu.add(jTeningurMenu5);

        jAdgerdir.add(jFrystaTeningaMenu);

        jMenuBar1.add(jAdgerdir);

        jHelpMenu.setText("Hjálp");

        jRulesMenu.setText("Leikreglur");
        jRulesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRulesMenuActionPerformed(evt);
            }
        });
        jHelpMenu.add(jRulesMenu);

        jHowToPlayMenu.setText("Hvernig á að spila");
        jHowToPlayMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHowToPlayMenuActionPerformed(evt);
            }
        });
        jHelpMenu.add(jHowToPlayMenu);

        jMenuBar1.add(jHelpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTeningur1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTeningur2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTeningur3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTeningur4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTeningur5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jVeljaFjarka, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPlayerBirtaFjarka))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jVeljaThrista, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jVeljaTvista, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jVeljaAsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(6, 6, 6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bonus, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sumPart))
                                        .addGap(79, 79, 79)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(playerLabel1)
                                        .addGap(33, 33, 33)
                                        .addComponent(userLabel1))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jPlayerBirtaBonus)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCompBirtaBonus))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jPlayerBirtaSumPart)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCompBirtaSumPart))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jPlayerBirtaAsa)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCompBirtaAsa))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jCompBirtaSexur)
                                                .addComponent(jCompBirtaFimmur)
                                                .addComponent(jCompBirtaFjarka)
                                                .addComponent(jCompBirtaThrista)
                                                .addComponent(jCompBirtaTvista))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPlayerBirtaTvista)
                                    .addComponent(jPlayerBirtaThrista)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jVeljaFimmur, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(jVeljaSexur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPlayerBirtaSexur, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPlayerBirtaFimmur))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(totalScore)
                                .addGap(34, 34, 34)
                                .addComponent(jPlayerBirtaHeildarSkor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jVeljaTakaSens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaFullHouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaEittPar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaTvoPor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaThreeOfaKind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaFourOfaKind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaSmallRow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaBigRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jVeljaYatzee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPlayerBirtaThreeOfaKind)
                                    .addComponent(jPlayerBirtaFourOfaKind)
                                    .addComponent(jPlayerBirtaSmallRow)
                                    .addComponent(jPlayerBirtaBigRow)
                                    .addComponent(jPlayerBirtaTvoPor)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(playerLabel2)
                                            .addComponent(jPlayerBirtaEittPar)
                                            .addComponent(jPlayerBirtaFullHouse)
                                            .addComponent(jPlayerBirtaYatzee, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPlayerBirtaTakaSensinn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(userLabel2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jCompBirtaTvoPor)
                                                        .addComponent(jCompBirtaEittPar)
                                                        .addComponent(jCompBirtaThreeOfaKind)
                                                        .addComponent(jCompBirtaFourOfaKind)
                                                        .addComponent(jCompBirtaSmallRow)
                                                        .addComponent(jCompBirtaBigRow)
                                                        .addComponent(jCompBirtaFullHouse)
                                                        .addComponent(jCompBirtaTakaSensinn)
                                                        .addComponent(jCompBirtaYatzee))
                                                    .addComponent(jCompBirtaHeildarSkor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFjoldiKastaLabel)
                            .addComponent(jKastaTeningum, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jNyrLeikur, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTeningur1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTeningur3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTeningur4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTeningur5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTeningur2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jPlayerBirtaEittPar)
                                                    .addComponent(jCompBirtaAsa)
                                                    .addComponent(jVeljaEittPar))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jPlayerBirtaTvoPor)
                                                    .addComponent(jCompBirtaTvista)
                                                    .addComponent(jCompBirtaTvoPor)
                                                    .addComponent(jVeljaTvoPor))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jPlayerBirtaThreeOfaKind)
                                                    .addComponent(jCompBirtaThrista)
                                                    .addComponent(jCompBirtaThreeOfaKind)
                                                    .addComponent(jVeljaThreeOfaKind)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(playerLabel2)
                                                    .addComponent(userLabel2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCompBirtaEittPar)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaFourOfaKind)
                                            .addComponent(jCompBirtaFjarka)
                                            .addComponent(jCompBirtaFourOfaKind)
                                            .addComponent(jVeljaFourOfaKind))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaSmallRow)
                                            .addComponent(jCompBirtaFimmur)
                                            .addComponent(jCompBirtaSmallRow)
                                            .addComponent(jVeljaSmallRow))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaBigRow)
                                            .addComponent(jCompBirtaSexur)
                                            .addComponent(jCompBirtaBigRow)
                                            .addComponent(jVeljaBigRow))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaFullHouse)
                                            .addComponent(jCompBirtaFullHouse)
                                            .addComponent(jVeljaFullHouse))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaTakaSensinn)
                                            .addComponent(jCompBirtaTakaSensinn)
                                            .addComponent(jVeljaTakaSens))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaYatzee)
                                            .addComponent(jCompBirtaYatzee)
                                            .addComponent(jVeljaYatzee)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(playerLabel1)
                                            .addComponent(userLabel1))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaAsa)
                                            .addComponent(jVeljaAsa))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaTvista)
                                            .addComponent(jVeljaTvista))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaThrista)
                                            .addComponent(jVeljaThrista))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaFjarka)
                                            .addComponent(jVeljaFjarka))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaFimmur)
                                            .addComponent(jVeljaFimmur))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaSexur)
                                            .addComponent(jVeljaSexur))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jPlayerBirtaSumPart)
                                            .addComponent(sumPart)
                                            .addComponent(jCompBirtaSumPart))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(totalScore)
                                            .addComponent(jPlayerBirtaHeildarSkor)
                                            .addComponent(jCompBirtaBonus)
                                            .addComponent(jPlayerBirtaBonus)
                                            .addComponent(jCompBirtaHeildarSkor)
                                            .addComponent(bonus)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jKastaTeningum, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFjoldiKastaLabel)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jNyrLeikur, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
   
    /**
     * Fallið kallar á annað fall sem kastar teningunum. ásamt
     * því að sýna gildin úr teningunum í viðmótinu. Fallið skilur
     * einnig þannig við alla hnappa (e. button) og miða(e. label)
     * að þeir séu rétt stilltir miðað við framvindu leiksins.
     * @param evt er atburðurinn sem búinn er til.
     */

    private void jKastaTeningumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKastaTeningumActionPerformed
        kastaTeningum();
    }//GEN-LAST:event_jKastaTeningumActionPerformed
    
    /**
     * Fallið kastar teningunum ásamt því að sýna gildin úr teningunum í viðmótinu. Fallið skilur
     * einnig þannig við alla hnappa (e. button) og miða(e. label)
     * að þeir séu rétt stilltir miðað við framvindu leiksins.
     */
    private void kastaTeningum()
    {
        virkjaFrystaTeningaMenu(true);
        synaSidastaLeik(false);
        vYatzeeLeikur.kastaTeningum();
        int n = vYatzeeLeikur.getFjoldiKasta();
        if(n%3==0)
        {
            virkjaFrystaTeningaMenu(false);
            uppfaeraSkorKortHnappa(true);
            jKastaTeningum.setEnabled(false);
            jKastaMenu.setEnabled(false);
            vYatzeeLeikur.affrystaTeninga();
            uppfaeraFrystaTeningaMenu();
        }
        uppfaeraTeninga();
        jFjoldiKastaLabel.setText("Fjöldi kasta eftir: " + Integer.toString(n));        
    }

    /**
     * Fallið virkjar möguleikann á að frysta teninga á menu slánni
     * ef leyfilegt er að virkja þá annars óvirkjar hann þá.
     * @param b er booleanbreyta sem er satt ef virkja á frysta teninga
     * möguleikann á menu slánni, annars ósatt.
     */
    private void virkjaFrystaTeningaMenu(boolean b)
    {
        JMenuItem[] frystaTeninga = new JMenuItem[]{jTeningurMenu1,
            jTeningurMenu2,jTeningurMenu3,jTeningurMenu4,jTeningurMenu5};
        for(JMenuItem item: frystaTeninga)
        {
            item.setEnabled(b);
        }
    }
    
    /**
     * Fallið upphafsstillir notendaviðmót leiksins
     * Þegar smellt er á "Byrja nýjan leik" hnappinn.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jNyrLeikurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNyrLeikurActionPerformed
        byrjaNyjanLeik();
    }//GEN-LAST:event_jNyrLeikurActionPerformed
    /**
     * Fallið upphafsstillir leikinn.
     */
    private void byrjaNyjanLeik()
    {
        initVinnsla();
        initGroupComponents();
        initSkorkort(true);
        if(jMultiPlayer == true)
            initSkorkort(false);
        initMyndir();
        jKastaTeningum.setEnabled(true);
        jKastaMenu.setEnabled(true);
        jFjoldiKastaLabel.setText("Fjöldi kasta eftir: " + Integer.toString(vYatzeeLeikur.getFjoldiKasta()));
    }
    /**
     * Fallið uppfærir skorkortið hjá leikmanni ef leikmadur er satt
     * annars hjá tölvu.
     * @param leikmadur er satt ef leikmadur á leik annars 
     * ósatt.
     */
    private void uppfaeraSkorKort(boolean leikmadur){
        int stig = vYatzeeLeikur.reiknaUtStig();
        vYatzeeLeikur.setjaToluIReit(stig, leikmadur);
        JLabel[] utreiknudGildi;
        int[] nidurstada = vYatzeeLeikur.nidurstodur(leikmadur);
        if(leikmadur)
        {
            skorkortLeikmadur[vYatzeeLeikur.getValinnLeik()].setText(String.valueOf(stig));
            utreiknudGildi = new JLabel[]{jPlayerBirtaHeildarSkor,jPlayerBirtaSumPart,jPlayerBirtaBonus};            
        }
        else
        {
            skorkortTolva[vYatzeeLeikur.getValinnLeik()].setText(String.valueOf(stig));
            utreiknudGildi = new JLabel[]{jCompBirtaHeildarSkor,jCompBirtaSumPart,jCompBirtaBonus};
        }
        for(int j = 0; j<nidurstada.length;j++)
        {
            utreiknudGildi[j].setText(String.valueOf(nidurstada[j]));
        }
    }
    /**
     * Fallið uppfærir myndirnar af teningunum.
     */
    private void uppfaeraTeninga(){
        JLabel[] jTeningur = new JLabel[] {jTeningur1,jTeningur2,jTeningur3,jTeningur4,jTeningur5};
        int i = 0;
        for (JLabel teningur : jTeningur) {
            teningur.setIcon(vYatzeeLeikur.synaTening(i));
            i++;
        }
 
    }
    
    
    /**
     * Fallið uppfærir stöðu hnappa við skorkortið, þ.e. virkjar og óvirkjar þá
     * eftir hentugleika og hendir þeim hnöppum sem er búið að skrá í valdirLeikir út úr
     * valdirLeikir.
     * @param b er satt ef það á að virkja hnappana, en ósatt ef það á að óvirkja þá.
     */
    private void uppfaeraSkorKortHnappa(boolean b){
        Stack tmp;
        tmp = valdirLeikir;
        Stack newValdirLeikir = new Stack();
        JButton nySkradurHnappur = skorKortHnappar[vYatzeeLeikur.getValinnLeik()];
        while(!tmp.empty())
        {
            JButton hnappur = (JButton) tmp.pop();
            hnappur.setEnabled(b);
            if((hnappur != nySkradurHnappur) || (b == true))
            {
                newValdirLeikir.push(hnappur);
            }
        }
        valdirLeikir = newValdirLeikir;  
    }
    
    /**
     * Fallið birtir leikmönnum leiksins stigin sem
     * hann fékk fyrir áðurvalinn leik.
     */
    private void skraSkor(){
        synaSidastaLeik(true);
        uppfaeraSkorKort(true);
        uppfaeraSkorKortHnappa(false);    
        jFjoldiKastaLabel.setText("Fjöldi kasta eftir: " + Integer.toString(vYatzeeLeikur.getFjoldiKasta()));
        if(jMultiPlayer == true)
        {
            jNyrLeikur.setEnabled(false);
            jNewGameMenu.setEnabled(false);
            multiPlayerSpilun();
        }
        else
        {
            jKastaTeningum.setEnabled(true);
            jKastaMenu.setEnabled(true);
            leikurBuinn();
        }
    }
    
    
    
    
    /**
     * Ef leikur er búinn sýnir fallið Topp10 listann strax ef að
     * spilað er einn en viðmót þar sem notandi er látinn vita hvort hann
     * hafi unnið tölvuna ef hann spilaði við tölvuna
     */
    private void leikurBuinn()
    {
        int n = vYatzeeLeikur.getFjoldiUmferda();
        // 15 umferðir spilaðar í Yatzee
        if(n == 15)
        {
            jKastaTeningum.enable(false);
            if(jMultiPlayer == true)
            {
                synaUrslit();
            }
            else
            {
                synaTopp10();
            }
        }
    }
    /**
     * Kallar á UtlitSigurvegari dialoginn
     */
    private void synaUrslit()
    {
        int[] stig = vYatzeeLeikur.heildarStigKeppenda();
        UtlitSigurvegari urslit = new UtlitSigurvegari(UtlitYatzee.this,false,stig[0],stig[1]);
        urslit.show();
        
    }
    /**
     * Fall sem kallar á topp 10 listann
     */
    private void synaTopp10()
    {
        int[] stig = vYatzeeLeikur.heildarStigKeppenda();
        Topp10Listi topp10Vidmot = new Topp10Listi(true, stig[0]);
        topp10Vidmot.setVisible(true);
        topp10Vidmot.setAlwaysOnTop (true);
        this.setVisible(false);
    }
    /**
     * Fallið litar síðasta leikinn leik á skorkortinu rauðan ef b er jafnt og false
     * annars litar fallið hann svartan. Gildir einungis fyrir multiplayer spilun.
     * @param b er booleanbreyta sem heldur utan um það hvort sýna þarf nýafstaðinn
     * leik eða ekki.
     */
    private void synaSidastaLeik(boolean b)
    {
        int leikur = vYatzeeLeikur.getValinnLeik();
        boolean leikmadurALeik = vYatzeeLeikur.getLeikmaðurALeik();
        if(jMultiPlayer)
        {
            if(b)
            {
                if(leikmadurALeik)
                    skorkortLeikmadur[leikur].setForeground(Color.red);
                else
                    skorkortTolva[leikur].setForeground(Color.red);
            }
            else
            {
                if(!leikmadurALeik)
                    skorkortLeikmadur[leikur].setForeground(Color.black);
                else
                    skorkortTolva[leikur].setForeground(Color.black);            
            }
        }
        
    }
    
    
    
    /**
     * Sér um allt sem gerist í viðmótinu þegar tölvan spilar.
     */
    private void multiPlayerSpilun()
    {
        kastaTeningumTolva();
    }
    
    /**
     * Sér um allt sem þarf að gera þegar síðasta kast tölvunnar er framkvæmt.
     */
    public void kastaSidastaKastiTolva()
    {
        synaSidastaLeik(false);
        vYatzeeLeikur.kastaTeningum();
        vYatzeeLeikur.veljaBestaLeik();
        vYatzeeLeikur.affrystaTeninga();
        uppfaeraTeninga();
        synaSidastaLeik(true);
        uppfaeraSkorKort(false);
        jFjoldiKastaLabel.setText("Fjöldi kasta eftir: " + Integer.toString(vYatzeeLeikur.getFjoldiKasta()));
        jKastaTeningum.setEnabled(true);
        jKastaMenu.setEnabled(true);
        jNyrLeikur.setEnabled(true);
        jNewGameMenu.setEnabled(true);
        leikurBuinn();

    }
    
    /**
     * 
     * Fall sem kastar teningunum fyrir tölvuna.
     * 
     */
    public void kastaTeningumTolva(){
        // Tíminn milli kasta tölvunnar
        int delayTime = 3 * 1000;        
        myTimer = new Timer(delayTime, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                if(vYatzeeLeikur.getFjoldiKasta() == 1)
                {
                    kastaSidastaKastiTolva();
                    myTimer.stop();
                    
                }
                else{
                    vYatzeeLeikur.kastaTeningum();
                    vYatzeeLeikur.bestAdFrysta();
                    uppfaeraTeninga();
                    jFjoldiKastaLabel.setText("Fjöldi kasta eftir: " + Integer.toString(vYatzeeLeikur.getFjoldiKasta()));
                }

                    
        }
        });
        if(vYatzeeLeikur.getFjoldiKasta() != 1)
            myTimer.start();        
    }

    /**
     * 
     * Fall sem setur ástand tenings (frysta eða affrysta) þegar smellt er á tening
     * @param teningur heldur utan um ID tenings sem smellt var á.
     */   
    private void smelltATening(int teningur)
    {
        if(vYatzeeLeikur.getFjoldiKasta()%3!=0 && vYatzeeLeikur.getLeikmaðurALeik())
        {
            vYatzeeLeikur.setAstandTeningur(teningur);
            uppfaeraTeninga();
            uppfaeraFrystaTeningaMenu();
        }
    }
    
    /**
     * Uppfærir hnappana sem frysta teninga á menu slánni eftir því hvort teningar
     * eru frystir eða ekki. Því ef leikmaður frystir tening með því að smella á tening
     * þá uppfærast check box takkarnir á menu slánni sjálfkrafa.
     */
    private void uppfaeraFrystaTeningaMenu()
    {
        boolean[] astandTeninga = vYatzeeLeikur.frystirTeningar();
        JMenuItem[] frystaTeninga = new JMenuItem[]{jTeningurMenu1,
            jTeningurMenu2,jTeningurMenu3,jTeningurMenu4,jTeningurMenu5};
        for(int i = 0; i<frystaTeninga.length;i++)
        {
            frystaTeninga[i].setSelected(astandTeninga[i]);
        }  
    }
   /**
     * Þegar smellt er á músina yfir tening 1 þá verður teningurinn frystur
     * ef hann var ekki áður frystur en hann er affrystur ef hann var frystur
     * fyrir.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jTeningur1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTeningur1MousePressed
        int teningur = 0;
        smelltATening(teningur);

    }//GEN-LAST:event_jTeningur1MousePressed
    /**
     * Þegar smellt er á músina yfir tening 2 þá verður teningurinn frystur
     * ef hann var ekki áður frystur en hann er affrystur ef hann var frystur
     * fyrir.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jTeningur2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTeningur2MousePressed
        int teningur = 1;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningur2MousePressed
    /**
     * Þegar smellt er á músina yfir tening 3 þá verður teningurinn frystur
     * ef hann var ekki áður frystur en hann er affrystur ef hann var frystur
     * fyrir.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jTeningur3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTeningur3MousePressed
        int teningur = 2;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningur3MousePressed
    /**
     * Þegar smellt er á músina yfir tening 4 þá verður teningurinn frystur
     * ef hann var ekki áður frystur en hann er affrystur ef hann var frystur
     * fyrir.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jTeningur4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTeningur4MousePressed
        int teningur = 3;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningur4MousePressed
    /**
     * Þegar smellt er á músina yfir tening 5 þá verður teningurinn frystur
     * ef hann var ekki áður frystur en hann er affrystur ef hann var frystur
     * fyrir.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jTeningur5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTeningur5MousePressed
        int teningur = 4;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningur5MousePressed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * ása.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaAsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaAsaActionPerformed
        vYatzeeLeikur.setValinnLeik(0);
        skraSkor();
    }//GEN-LAST:event_jVeljaAsaActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * tvista.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaTvistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaTvistaActionPerformed
        vYatzeeLeikur.setValinnLeik(1);
        skraSkor();
    }//GEN-LAST:event_jVeljaTvistaActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * þrista.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaThristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaThristaActionPerformed
        vYatzeeLeikur.setValinnLeik(2);
        skraSkor();
    }//GEN-LAST:event_jVeljaThristaActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * fjarka.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaFjarkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaFjarkaActionPerformed
        vYatzeeLeikur.setValinnLeik(3);
        skraSkor();
    }//GEN-LAST:event_jVeljaFjarkaActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * fimmur.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaFimmurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaFimmurActionPerformed
        vYatzeeLeikur.setValinnLeik(4);
        skraSkor();
    }//GEN-LAST:event_jVeljaFimmurActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * sexur.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaSexurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaSexurActionPerformed
        vYatzeeLeikur.setValinnLeik(5);
        skraSkor();
    }//GEN-LAST:event_jVeljaSexurActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * eitt par
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaEittParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaEittParActionPerformed
        vYatzeeLeikur.setValinnLeik(6);
        skraSkor();
    }//GEN-LAST:event_jVeljaEittParActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * tvö pör.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaTvoPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaTvoPorActionPerformed
       vYatzeeLeikur.setValinnLeik(7);
        skraSkor();
    }//GEN-LAST:event_jVeljaTvoPorActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * þrjá eins.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaThreeOfaKindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaThreeOfaKindActionPerformed
        vYatzeeLeikur.setValinnLeik(8);
        skraSkor();
    }//GEN-LAST:event_jVeljaThreeOfaKindActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * fjóra eins.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaFourOfaKindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaFourOfaKindActionPerformed
        vYatzeeLeikur.setValinnLeik(9);
        skraSkor();
    }//GEN-LAST:event_jVeljaFourOfaKindActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * litla röð.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaSmallRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaSmallRowActionPerformed
        vYatzeeLeikur.setValinnLeik(10);
        skraSkor();
    }//GEN-LAST:event_jVeljaSmallRowActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * stóra röð.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaBigRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaBigRowActionPerformed
        vYatzeeLeikur.setValinnLeik(11);
        skraSkor();
    }//GEN-LAST:event_jVeljaBigRowActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * fullt hús.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaFullHouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaFullHouseActionPerformed
        vYatzeeLeikur.setValinnLeik(12);
        skraSkor();
    }//GEN-LAST:event_jVeljaFullHouseActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið að taka
     * sénsinn.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaTakaSensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaTakaSensActionPerformed
        vYatzeeLeikur.setValinnLeik(13);
        skraSkor();
    }//GEN-LAST:event_jVeljaTakaSensActionPerformed
   /**
     * Fallið segir leiknum að leikmaður hafi valið
     * í yatzee.
     * @param evt er atburðurinn sem búinn er til.
     */
    private void jVeljaYatzeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVeljaYatzeeActionPerformed
        vYatzeeLeikur.setValinnLeik(14);
        skraSkor();
    }//GEN-LAST:event_jVeljaYatzeeActionPerformed
    /**
     * Þegar leikmaður ákveður að hætta að spila Yatzee kemur birtist
     * dialog sem spyr notanda hvort hann sé viss í sinni sök.
     */
    private void quitGame()
    {
        String[] options = new String[]{"Já", "Nei"};
        int result = JOptionPane.showOptionDialog(this,
                "Ertu viss um að þú viljir hætta?   ","Hætta",
                0,JOptionPane.QUESTION_MESSAGE,null,options,null);
        if(result == JOptionPane.YES_OPTION)
            this.dispatchEvent(new WindowEvent(this, 
                    WindowEvent.WINDOW_CLOSING));
    }
    /**
     * Kallar á mainMenu jFrame
     */
    private void mainMenu()
    {
        Upphafsvalmynd menu = new Upphafsvalmynd();
        setVisible(false);
        menu.setVisible(true);
    }
    /**
     * Kallar á mainMenu jFrame ef notandi smellir á "Aðalvalmynd" hnappinn
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActionPerformed
        mainMenu();
    }//GEN-LAST:event_jMenuActionPerformed
    /**
     * Hættir leik ef smellt er á "Hætta í yatzee" hnappinn.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuitActionPerformed
        quitGame();
    }//GEN-LAST:event_jQuitActionPerformed
    /**
     * Upphafsstillir stigatöfluna og útlit yatzee leiksins ef smellt er á
     * "Nýr leikur" í menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jNewGameMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewGameMenuActionPerformed
        byrjaNyjanLeik();
    }//GEN-LAST:event_jNewGameMenuActionPerformed
    /**
     * Hættir leik ef smellt er á "Hætta leik" hnappinn í menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jQuitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuitMenuActionPerformed
        quitGame();
    }//GEN-LAST:event_jQuitMenuActionPerformed
    /**
     * Frystir tening 1 ef smellt er á "Teningur 1" hnappinn í menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jTeningurMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTeningurMenu1ActionPerformed
        int teningur = 0;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningurMenu1ActionPerformed
    /**
     * Frystir tening 3 ef smellt er á "Teningur 3" hnappinn í menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jTeningurMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTeningurMenu3ActionPerformed
        int teningur = 2;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningurMenu3ActionPerformed
    /**
     * Frystir tening 4 ef smellt er á "Teningur 4" hnappinn í menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jTeningurMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTeningurMenu4ActionPerformed
        int teningur = 3;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningurMenu4ActionPerformed
    /**
     * Frystir tening 5 ef smellt er á "Teningur 5" hnappinn í menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jTeningurMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTeningurMenu5ActionPerformed
        int teningur = 4;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningurMenu5ActionPerformed
    /**
     * Sýnir UtlitHowToPlay jDialoginn og þar af leiðandi hvernig á að spila Yatzee
     * leikinn þegar smellt er á "Hvernig á að spila" hanppinn í menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jHowToPlayMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHowToPlayMenuActionPerformed
        UtlitHowToPlay howTo = new UtlitHowToPlay(UtlitYatzee.this,false);
	howTo.show();
    }//GEN-LAST:event_jHowToPlayMenuActionPerformed
    /**
     * Kallar á mainMenu jFrame ef notandi smellir á "Aðalvalmynd" hnappinn
     * á menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMainMenuActionPerformed
        mainMenu();
    }//GEN-LAST:event_jMainMenuActionPerformed
    /**
     * Kastar teningum ef notandi smelli á "Kasta teningum" hnappinn á 
     * menu slánni
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jKastaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKastaMenuActionPerformed
        kastaTeningum();
    }//GEN-LAST:event_jKastaMenuActionPerformed
    /**
     * Frystir tening 2 ef smellt er á "Teningur 2" hnappinn á menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jTeningurMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTeningurMenu2ActionPerformed
        int teningur = 1;
        smelltATening(teningur);
    }//GEN-LAST:event_jTeningurMenu2ActionPerformed
    /**
     * Kallar á UtlitLeikreglur jDialoginn þegar að leikmaður smellir á "Leikreglur"
     * hnapinn á menu slánni.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jRulesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRulesMenuActionPerformed
        UtlitLeikreglur rules = new UtlitLeikreglur(UtlitYatzee.this,false);
	rules.show();
    }//GEN-LAST:event_jRulesMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bonus;
    private javax.swing.JMenu jAdgerdir;
    private javax.swing.JLabel jCompBirtaAsa;
    private javax.swing.JLabel jCompBirtaBigRow;
    private javax.swing.JLabel jCompBirtaBonus;
    private javax.swing.JLabel jCompBirtaEittPar;
    private javax.swing.JLabel jCompBirtaFimmur;
    private javax.swing.JLabel jCompBirtaFjarka;
    private javax.swing.JLabel jCompBirtaFourOfaKind;
    private javax.swing.JLabel jCompBirtaFullHouse;
    private javax.swing.JLabel jCompBirtaHeildarSkor;
    private javax.swing.JLabel jCompBirtaSexur;
    private javax.swing.JLabel jCompBirtaSmallRow;
    private javax.swing.JLabel jCompBirtaSumPart;
    private javax.swing.JLabel jCompBirtaTakaSensinn;
    private javax.swing.JLabel jCompBirtaThreeOfaKind;
    private javax.swing.JLabel jCompBirtaThrista;
    private javax.swing.JLabel jCompBirtaTvista;
    private javax.swing.JLabel jCompBirtaTvoPor;
    private javax.swing.JLabel jCompBirtaYatzee;
    private javax.swing.JMenu jFile;
    private javax.swing.JLabel jFjoldiKastaLabel;
    private javax.swing.JMenu jFrystaTeningaMenu;
    private javax.swing.JMenu jHelpMenu;
    private javax.swing.JMenuItem jHowToPlayMenu;
    private javax.swing.JMenuItem jKastaMenu;
    private javax.swing.JButton jKastaTeningum;
    private javax.swing.JMenuItem jMainMenu;
    private javax.swing.JButton jMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jNewGameMenu;
    private javax.swing.JButton jNyrLeikur;
    private javax.swing.JLabel jPlayerBirtaAsa;
    private javax.swing.JLabel jPlayerBirtaBigRow;
    private javax.swing.JLabel jPlayerBirtaBonus;
    private javax.swing.JLabel jPlayerBirtaEittPar;
    private javax.swing.JLabel jPlayerBirtaFimmur;
    private javax.swing.JLabel jPlayerBirtaFjarka;
    private javax.swing.JLabel jPlayerBirtaFourOfaKind;
    private javax.swing.JLabel jPlayerBirtaFullHouse;
    private javax.swing.JLabel jPlayerBirtaHeildarSkor;
    private javax.swing.JLabel jPlayerBirtaSexur;
    private javax.swing.JLabel jPlayerBirtaSmallRow;
    private javax.swing.JLabel jPlayerBirtaSumPart;
    private javax.swing.JLabel jPlayerBirtaTakaSensinn;
    private javax.swing.JLabel jPlayerBirtaThreeOfaKind;
    private javax.swing.JLabel jPlayerBirtaThrista;
    private javax.swing.JLabel jPlayerBirtaTvista;
    private javax.swing.JLabel jPlayerBirtaTvoPor;
    private javax.swing.JLabel jPlayerBirtaYatzee;
    private javax.swing.JButton jQuit;
    private javax.swing.JMenuItem jQuitMenu;
    private javax.swing.JMenuItem jRulesMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel jTeningur1;
    private javax.swing.JLabel jTeningur2;
    private javax.swing.JLabel jTeningur3;
    private javax.swing.JLabel jTeningur4;
    private javax.swing.JLabel jTeningur5;
    private javax.swing.JCheckBoxMenuItem jTeningurMenu1;
    private javax.swing.JCheckBoxMenuItem jTeningurMenu2;
    private javax.swing.JCheckBoxMenuItem jTeningurMenu3;
    private javax.swing.JCheckBoxMenuItem jTeningurMenu4;
    private javax.swing.JCheckBoxMenuItem jTeningurMenu5;
    private javax.swing.JButton jVeljaAsa;
    private javax.swing.JButton jVeljaBigRow;
    private javax.swing.JButton jVeljaEittPar;
    private javax.swing.JButton jVeljaFimmur;
    private javax.swing.JButton jVeljaFjarka;
    private javax.swing.JButton jVeljaFourOfaKind;
    private javax.swing.JButton jVeljaFullHouse;
    private javax.swing.JButton jVeljaSexur;
    private javax.swing.JButton jVeljaSmallRow;
    private javax.swing.JButton jVeljaTakaSens;
    private javax.swing.JButton jVeljaThreeOfaKind;
    private javax.swing.JButton jVeljaThrista;
    private javax.swing.JButton jVeljaTvista;
    private javax.swing.JButton jVeljaTvoPor;
    private javax.swing.JButton jVeljaYatzee;
    private javax.swing.JLabel playerLabel1;
    private javax.swing.JLabel playerLabel2;
    private javax.swing.JLabel sumPart;
    private javax.swing.JLabel totalScore;
    private javax.swing.JLabel userLabel1;
    private javax.swing.JLabel userLabel2;
    // End of variables declaration//GEN-END:variables


}
