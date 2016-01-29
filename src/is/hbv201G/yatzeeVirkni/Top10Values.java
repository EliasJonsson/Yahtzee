/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hbv201G.yatzeeVirkni;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


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
 * Top10Values sér um að viðhalda Topp10listanum í Topp10.dat
 */
public class Top10Values {
    /**
     * Vistar topp10 listann í Topp10.dat.
     * @param topp10 er hlutur af taginu Topplisti sem inniheldur
     * allar helstu upplýsingarnar um topp10 listann.
     * @throws IOException 
     */
    public static void saveTopp10(ToppListi[] topp10) throws IOException
    {
        //String a = this.getClass().getResource("Myndir/QuestionDice.png");
        File newFile = new File("Topp10.dat");
        boolean b = newFile.createNewFile();
        //Create writer object
        PrintWriter writer = new PrintWriter(new FileWriter("Topp10.dat"));
        for (int i = 0; i < topp10.length; i++) {
            if(topp10[i].name != null)
            	writer.write(Integer.toString(topp10[i].rank) + ";"+ topp10[i].name + ";" + Integer.toString(topp10[i].points) + "\n");
            else
            	writer.write(Integer.toString(i+1) + ";"+ "" + ";" + "0" + "\n");
        }
        //Close writer object.
        writer.close();
    }
    /**
     * Fallið nær í topp10 listann úr Topp10.dat.
     * @return topp10 listanum.
     * @throws IOException 
     */
    public static ToppListi[] getTopp10() throws IOException
    {
        String[] listi = new String[10];
        FileInputStream fileStream = new FileInputStream("Topp10.dat");
        DataInputStream in = new DataInputStream(fileStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String strLine;
        int i = 0;
        while ((strLine = reader.readLine()) != null) {
        	listi[i] = strLine;
                i++;
        }
        reader.close();
        return stringToTopp10Listi(listi);
    }
    /**
     * Tekur við strengjum úr topp10.dat og býr til topp10 listi
     * af taginu ToppListi[]
     * @param topp10Listi er hlutur af taginu Topplisti sem inniheldur
     * allar helstu upplýsingarnar um topp10 listann.
     * @return topp 10 listi.
     */
    public static ToppListi[] stringToTopp10Listi(String[] topp10Listi)
    {
	ToppListi[] topp10 = new ToppListi[10];
	int i = 0;
	String[] ithBest;
	while(i<10)
	{
            if(topp10Listi[i]!= null)
            {
		ithBest = topp10Listi[i].split(";");	
		topp10[i] = new ToppListi(Integer.parseInt(ithBest[0]),ithBest[1],Integer.parseInt(ithBest[2]));
            }
            else
                topp10[i] = new ToppListi(i+1,"",0);

            i++;
        }
        return topp10;
    }
}
