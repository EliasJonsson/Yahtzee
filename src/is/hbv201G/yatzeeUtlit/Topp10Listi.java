/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hbv201G.yatzeeUtlit;

import is.hbv201G.yatzeeVirkni.Top10Values;
import is.hbv201G.yatzeeVirkni.ToppListi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

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
 * Hlutverk Topp10Listi er að sjá um notendaviðmót
 * tengt topp 10 listanum. Inniheldur JFrame.
 * 
 */
public class Topp10Listi extends javax.swing.JFrame {

    /**
     * Creates new form Topp10Listi
     */
    ToppListi[] topp10;
    private JTextField[] rankTextField;
    private JTextField[] nafnTextField;
    private JTextField[] pointsTextField;
    final private boolean leikur;
    final private int stig;
    private boolean nyttMet;
    private int saetiMethafa;
    
    /**
     * 
     * @param leikur segir til um hvort verið var að klára leik
     * eða hvort ýtt var á topp 10 listann frá upphafsvalmyndinni.
     * @param stig sýnir stigafjölda notanda.
     */
    public Topp10Listi(boolean leikur, int stig) {
        this.leikur = leikur;
        this.stig = stig;
        initComponents();
        initValmynd();
        initMidjuStilla();
    }
    /**
     * Miðjustillir jFrame-inn á skjá notanda.
     */    
    private void initMidjuStilla()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2,
                dim.height/2-this.getHeight()/2);
    }
    /**
     * Upphafstillir útlitið á valmyndinni miðað við
     * hvenær og hvar topp10 listinn er opnaður.
     */
    private void initValmynd()
    {
        rankTextField = new JTextField[]{jRankNumber1,jRankNumber2,
            jRankNumber3,jRankNumber4,jRankNumber5,jRankNumber6,jRankNumber7,
            jRankNumber8,jRankNumber9,jRankNumber10};
        nafnTextField = new JTextField[]{jNafn1,jNafn2,
            jNafn3,jNafn4,jNafn5,jNafn6,
            jNafn7,jNafn8,jNafn9,jNafn10};
        pointsTextField = new JTextField[]{jPoints1,jPoints2,
            jPoints3,jPoints4,jPoints5,jPoints6,
            jPoints7,jPoints8,jPoints9,jPoints10};
        JTextField[][] topp10TextField = new JTextField[][]{rankTextField,nafnTextField,pointsTextField};
        for(int i = 0; i<rankTextField.length;i++)
        {
            for(int j = 0; j<3;j++)
            {
                topp10TextField[j][i].setBackground(new Color(0,0,0,0));
                topp10TextField[j][i].setOpaque(false);
            }
        }
        if(leikur == false)
            jScore.setText("");
        uppfaeraTopp10Lista();
        skrifaNiðurTopp10Lista();

        
    }
    /**
     * Uppfærir topp10 listann þannig að hann líti út eins
     * og hann á að gera þegar kallað er á fallið.
     */
    private void uppfaeraTopp10Lista()
    {
        if(leikur == false)
            hlaðaInnTopp10Lista();
        else
        {
            hlaðaInnTopp10Lista();
            reiknaUtTopp10Lista();
        }
    }
    /**
     * Vistar breyttan topp10 lista.
     */
    private void vistaTopp10Lista()
    {
        try{
            Top10Values.saveTopp10(topp10);
        }
        catch(IOException ex)
        {
            System.out.println (ex.toString());
        }       
        
    }
    /**
     * Reiknar út hvort að lekmaður hafi komist á topp 10 listann eða ekki.
     * 
     */
    private void reiknaUtTopp10Lista()
    {
        int n = topp10.length-1;
        if(topp10[n].getPoints() < stig)
        {
            nyttMet = true;
            for(int i = 0; i<=n; i++)
            {
                if(topp10[i].getPoints() < stig)
                {   
                    saetiMethafa = i;
                    endurradaTopp10Lista(i);
                    
                    nafnTextField[saetiMethafa].setBackground(Color.WHITE);
                    nafnTextField[saetiMethafa].setOpaque(true);
                    nafnTextField[saetiMethafa].setEditable(true);
                    topp10[saetiMethafa].setAttribute(topp10[saetiMethafa].getRank(),"Skrifaðu nafn þitt hér",stig);
                    break;
                }
            }
            
        }
            
        
    }
    /**
     * Býr til nýjan topp10 lista miðað við nýja skorið sem fékkst.
     * @param saeti er saeti notanda sem hefur nýlokið leik á topp10 listanum.
     */
    private void endurradaTopp10Lista(int saeti)
    { 
        for(int i = topp10.length-2; i>=saeti;i--)
        {
            topp10[i+1].setAttribute(topp10[i+1].getRank(),topp10[i].getName(),topp10[i].getPoints());
        }
        
    }
    
    
    private void hlaðaInnTopp10Lista()
    {
        try{
            topp10 = Top10Values.getTopp10();
        }
        catch(IOException ex)
        {
            System.out.println (ex.toString());
        }
        
    }
    /**
     * Topp 10 listinn er birtur eins og hann á að vera í viðmótið.
     */
    private void skrifaNiðurTopp10Lista()
    {
        for(int i = 0; i < topp10.length; i++)
        {
            rankTextField[i].setText(Integer.toString(topp10[i].getRank()));
            nafnTextField[i].setText(topp10[i].getName());
            pointsTextField[i].setText(Integer.toString(topp10[i].getPoints()));
        }
    }
    /**
     * Skráir nýjan meðlmið á topp 10 listann
     */
    private void skraInnNyjanMedlim()
    {
       if(nyttMet == true)
       {
           int rank = saetiMethafa+1;
           String name = nafnTextField[saetiMethafa].getText();
           topp10[saetiMethafa].setAttribute(rank,name,stig);
       }
        
    }
    /**
     * Athugar hvort um leyfilegt nafn sé að ræða á nýjan methafa.
     * @return sat ef nafnið er leyfileft annars ósatt.
     */
    private boolean loglegtNafn()
    {
        return !nafnTextField[saetiMethafa].getText().contains(";");
    }
    /**
     * Lokar topp 10 listanum, en áður vistar hann topp10 listann. Þegar listanum
     * er lokað fer notandi á upphafsvalmyndina.
     */
    private void lokaTopp10Lista()
    {
        if(loglegtNafn())
        {
            skraInnNyjanMedlim();
            vistaTopp10Lista();
            setVisible(false);
            Upphafsvalmynd s = new Upphafsvalmynd();
            s.setVisible(true);
        }
        else
        {
            jErrorFunction.setText("; er ekki leyfilegur stafur í nafni.");
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

        jTopp10ListiLabel = new javax.swing.JLabel();
        jRankNumber1 = new javax.swing.JTextField();
        jRankNumber2 = new javax.swing.JTextField();
        jRankNumber3 = new javax.swing.JTextField();
        jRankNumber4 = new javax.swing.JTextField();
        jRankNumber5 = new javax.swing.JTextField();
        jRankNumber6 = new javax.swing.JTextField();
        jRankNumber7 = new javax.swing.JTextField();
        jRankNumber8 = new javax.swing.JTextField();
        jRankNumber9 = new javax.swing.JTextField();
        jRankNumber10 = new javax.swing.JTextField();
        jNafn1 = new javax.swing.JTextField();
        jNafn2 = new javax.swing.JTextField();
        jNafn3 = new javax.swing.JTextField();
        jNafn4 = new javax.swing.JTextField();
        jNafn5 = new javax.swing.JTextField();
        jNafn6 = new javax.swing.JTextField();
        jNafn7 = new javax.swing.JTextField();
        jNafn8 = new javax.swing.JTextField();
        jNafn9 = new javax.swing.JTextField();
        jNafn10 = new javax.swing.JTextField();
        jRank = new javax.swing.JLabel();
        jNafn = new javax.swing.JLabel();
        jPoints = new javax.swing.JLabel();
        jPoints1 = new javax.swing.JTextField();
        jPoints2 = new javax.swing.JTextField();
        jPoints3 = new javax.swing.JTextField();
        jPoints4 = new javax.swing.JTextField();
        jPoints5 = new javax.swing.JTextField();
        jPoints6 = new javax.swing.JTextField();
        jPoints7 = new javax.swing.JTextField();
        jPoints8 = new javax.swing.JTextField();
        jPoints9 = new javax.swing.JTextField();
        jPoints10 = new javax.swing.JTextField();
        jUpphafsValmynd = new javax.swing.JButton();
        jErrorFunction = new javax.swing.JLabel();
        jScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Yatzee");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTopp10ListiLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jTopp10ListiLabel.setText("Topp 10 listi");

        jRankNumber1.setEditable(false);
        jRankNumber1.setText("1");
        jRankNumber1.setBorder(null);

        jRankNumber2.setEditable(false);
        jRankNumber2.setText("2");
        jRankNumber2.setBorder(null);

        jRankNumber3.setEditable(false);
        jRankNumber3.setText("3");
        jRankNumber3.setBorder(null);

        jRankNumber4.setEditable(false);
        jRankNumber4.setText("4");
        jRankNumber4.setBorder(null);

        jRankNumber5.setEditable(false);
        jRankNumber5.setText("5");
        jRankNumber5.setBorder(null);

        jRankNumber6.setEditable(false);
        jRankNumber6.setText("6");
        jRankNumber6.setBorder(null);

        jRankNumber7.setEditable(false);
        jRankNumber7.setText("7");
        jRankNumber7.setBorder(null);

        jRankNumber8.setEditable(false);
        jRankNumber8.setText("8");
        jRankNumber8.setBorder(null);

        jRankNumber9.setEditable(false);
        jRankNumber9.setText("9");
        jRankNumber9.setBorder(null);

        jRankNumber10.setEditable(false);
        jRankNumber10.setText("10");
        jRankNumber10.setBorder(null);

        jNafn1.setEditable(false);
        jNafn1.setBorder(null);

        jNafn2.setEditable(false);
        jNafn2.setBorder(null);

        jNafn3.setEditable(false);
        jNafn3.setBorder(null);

        jNafn4.setEditable(false);
        jNafn4.setBorder(null);

        jNafn5.setEditable(false);
        jNafn5.setBorder(null);

        jNafn6.setEditable(false);
        jNafn6.setBorder(null);

        jNafn7.setEditable(false);
        jNafn7.setBorder(null);

        jNafn8.setEditable(false);
        jNafn8.setBorder(null);

        jNafn9.setEditable(false);
        jNafn9.setBorder(null);

        jNafn10.setEditable(false);
        jNafn10.setBorder(null);

        jRank.setText("Sæti");

        jNafn.setText("Nafn");

        jPoints.setText("Stig");

        jPoints1.setEditable(false);
        jPoints1.setText("0");
        jPoints1.setBorder(null);

        jPoints2.setEditable(false);
        jPoints2.setText("0");
        jPoints2.setBorder(null);

        jPoints3.setEditable(false);
        jPoints3.setText("0");
        jPoints3.setBorder(null);

        jPoints4.setEditable(false);
        jPoints4.setText("0");
        jPoints4.setBorder(null);

        jPoints5.setEditable(false);
        jPoints5.setText("0");
        jPoints5.setBorder(null);

        jPoints6.setEditable(false);
        jPoints6.setText("0");
        jPoints6.setBorder(null);

        jPoints7.setEditable(false);
        jPoints7.setText("0");
        jPoints7.setBorder(null);

        jPoints8.setEditable(false);
        jPoints8.setText("0");
        jPoints8.setBorder(null);

        jPoints9.setEditable(false);
        jPoints9.setText("0");
        jPoints9.setBorder(null);

        jPoints10.setEditable(false);
        jPoints10.setText("0");
        jPoints10.setBorder(null);

        jUpphafsValmynd.setText("Halda áfram");
        jUpphafsValmynd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpphafsValmyndActionPerformed(evt);
            }
        });

        jErrorFunction.setForeground(new java.awt.Color(255, 0, 51));

        jScore.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScore.setText("Þú fékkst " + Integer.toString(stig) + " stig");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTopp10ListiLabel)
                .addGap(157, 157, 157))
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jUpphafsValmynd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jErrorFunction)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRankNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRankNumber10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRank))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(jPoints3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn10, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn6, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn7, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn8, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn9, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jNafn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jNafn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPoints1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTopp10ListiLabel)
                .addGap(18, 18, 18)
                .addComponent(jScore, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jErrorFunction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRank)
                    .addComponent(jNafn)
                    .addComponent(jPoints))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRankNumber10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNafn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPoints10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jUpphafsValmynd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Ef smellt er á "Halda áfram" er notandi sendur í upphafsvalmyndina.
     * @param evt er atburðurinn sem var búinn til. 
     */
    private void jUpphafsValmyndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpphafsValmyndActionPerformed
        lokaTopp10Lista();
    }//GEN-LAST:event_jUpphafsValmyndActionPerformed
    /**
     * Gripinn er atburðurnn þegar topp10 listinn er lokaður og
     * topp10 listinn visatður áður en notandi er sendur aftur á 
     * upphafsvalmyndina.
     * @param evt er atburðurinn sem var búinn til. 
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        lokaTopp10Lista();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Topp10Listi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Topp10Listi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Topp10Listi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Topp10Listi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Topp10Listi(true,0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jErrorFunction;
    private javax.swing.JLabel jNafn;
    private javax.swing.JTextField jNafn1;
    private javax.swing.JTextField jNafn10;
    private javax.swing.JTextField jNafn2;
    private javax.swing.JTextField jNafn3;
    private javax.swing.JTextField jNafn4;
    private javax.swing.JTextField jNafn5;
    private javax.swing.JTextField jNafn6;
    private javax.swing.JTextField jNafn7;
    private javax.swing.JTextField jNafn8;
    private javax.swing.JTextField jNafn9;
    private javax.swing.JLabel jPoints;
    private javax.swing.JTextField jPoints1;
    private javax.swing.JTextField jPoints10;
    private javax.swing.JTextField jPoints2;
    private javax.swing.JTextField jPoints3;
    private javax.swing.JTextField jPoints4;
    private javax.swing.JTextField jPoints5;
    private javax.swing.JTextField jPoints6;
    private javax.swing.JTextField jPoints7;
    private javax.swing.JTextField jPoints8;
    private javax.swing.JTextField jPoints9;
    private javax.swing.JLabel jRank;
    private javax.swing.JTextField jRankNumber1;
    private javax.swing.JTextField jRankNumber10;
    private javax.swing.JTextField jRankNumber2;
    private javax.swing.JTextField jRankNumber3;
    private javax.swing.JTextField jRankNumber4;
    private javax.swing.JTextField jRankNumber5;
    private javax.swing.JTextField jRankNumber6;
    private javax.swing.JTextField jRankNumber7;
    private javax.swing.JTextField jRankNumber8;
    private javax.swing.JTextField jRankNumber9;
    private javax.swing.JLabel jScore;
    private javax.swing.JLabel jTopp10ListiLabel;
    private javax.swing.JButton jUpphafsValmynd;
    // End of variables declaration//GEN-END:variables
}
