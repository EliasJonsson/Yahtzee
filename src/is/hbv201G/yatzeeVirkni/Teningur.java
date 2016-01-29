/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hbv201G.yatzeeVirkni;
import javax.swing.ImageIcon;
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
 * Klassinn Teningur inniheldur allar helstu
 * upplýsingarnar um tening.
 * 
 */
public class Teningur {

    /**
     * Fallið líkir eftir því þegar
     * einum tening er kastað.
     * @return gildi tenings er slembin tala
     * á milli 1 og 6.
     */
    public int kastaTening()
    {
        int hlidarTenings = 6;
	int gildiTenings = (int) Math.ceil(Math.random()*hlidarTenings);
	return gildiTenings;
    }
    /**
     * Fallið skilar réttri mynd af hlið tenings
     * @param teningur er id-ið fyrir teninginn.
     * @param utkoma gildið sem er á teningnum.
     * @return skilar slóð á mynd af hlið tenings
     * sem samsvarar breytunni utkoma.
     */
    public ImageIcon synaTening(int teningur, int utkoma, boolean frystur)
    {
        String filename;
        if(frystur == false)
        {
            filename = "Myndir/Dice" + utkoma + ".png" ;
        }
        else
        {
            filename = "Myndir/ClickDice" + utkoma + ".png" ;
        }
        return new ImageIcon(this.getClass().getResource(filename));  
    }
    
}
