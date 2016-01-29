/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hbv201G.yatzeeVirkni;

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
 * Hlutverk YatzeeBlad er að halda utan
 * um stigafjölda leikmanns.
 * 
 */
public class YatzeeBlad {
    public int[] yatzeeStigatafla = new int[18];
    /*
    * yatzeeStigatafla heldur utan um stigin fyrir
    * hvert skrásett skor leikmanns.
    */
    
    
    /**
     * Fallið setur skrásettan stigafjölda
     * leikmanns í stigatöfluna.
     * @param reitur segir til um hvar í 
     * töfluna á að setja stigin.
     * @param tala segir til um stigafjöldann
     * sem á að skrá inn.
     */
    public void setjaToluIReit (int reitur, int tala)
    {
	yatzeeStigatafla[reitur] = tala;
    }
    /**
     * Uppfaerir samtals stigafjölda leikmannsins ásamt
     * bónusstigum og samanlögðum stigum fyrri hluta
     * Yatzee.

     */
    public void uppfaerirNidurstodur()
    {
        int stigafjoldiFyrriHelmingur = 0;
        int i;
        for(i = 0; i<6 ; i++)
        {
            stigafjoldiFyrriHelmingur += yatzeeStigatafla[i];
        }
        yatzeeStigatafla[yatzeeStigatafla.length-2] = stigafjoldiFyrriHelmingur;
        if(stigafjoldiFyrriHelmingur>=63)
        {
            yatzeeStigatafla[yatzeeStigatafla.length-3] = 50;
        }
        int stigafjoldiUrYatzee = 0;
        
        for(int j = i; j<yatzeeStigatafla.length-1 ; j++)
        {
            stigafjoldiUrYatzee += yatzeeStigatafla[j];
        }
        yatzeeStigatafla[yatzeeStigatafla.length-1] = stigafjoldiUrYatzee;
    }
    
}
