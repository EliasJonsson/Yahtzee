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
 * ToppListi útfærir topp10 listann.
 * 
 */
public class ToppListi {
    int rank;
    String name;
    int points;
    /**
     * ToppListi er upphafstilltur
     * @param rank sæti aðila á topp10 listanum
     * @param name nafn aðila á topp10 listanum.
     * @param points stig aðila á topp10 listanum.
     */
    public ToppListi(int rank,String name, int points)
    {
	this.rank = rank;
	this.name = name;
	this.points = points;
    }
    /**
     * Til þess að breyta skori á topp10 listanum.
     * @param rank sæti aðila á topp10 listanum
     * @param name nafn aðila á topp10 listanum.
     * @param points stig aðila á topp10 listanum.
     */
    public void setAttribute(int rank, String name, int points)
    {
	this.rank = rank;
	this.name = name;
	this.points = points;

    }
    /**
     * Fá sæti leikmanns á topp10 lista
     * @return sæti leikmanns á topp10 lista.
     */
    public int getRank()
    {
	return rank;
    }
     /**
     * Fá nafn leikmanns á topp10 lista
     * @return nafn leikmanns á topp10 lista.
     */   
    public String getName()
    {
	return name;
    }
    /**
     * Fá stig leikmanns á topp10 lista
     * @return stig leikmanns á topp10 lista.
     */
    public int getPoints()
    {
	return points;
    }
}
