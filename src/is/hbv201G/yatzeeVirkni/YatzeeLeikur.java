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
 * Klassinn inniheldur allar helstu
 * upplýsingarnar um leikinn sjálfan.
 * 
 */
public class YatzeeLeikur {
    private final YatzeeBlad vyatzeeBladLeikmadur;
    /**
     * vyatzeeBlad er hlutur af taginnu YatzeeBlad fylki
     * sem inniheldur allar upplýsingarnar um stigagjöfina
     * hjá leikmanni.
     */
    
    private final YatzeeBlad vyatzeeBladTolva;
    /**
     * vyatzeeBladTolva er hlutur af taginnu YatzeeBlad
     * sem inniheldur allar upplýsingarnar um stigagjöfina
     * hjá tölvu.
     */
    
    private final Teningur vteningur;
     /**
     * vteningur er hlutur af taginnu Teningur
     * sem inniheldur allar upplýsingarnar um teninginn.
     */
    private int veljaLeik;
     /**
     * veljaLeik heldur utan um hvaða valmöguleiki
     * hefur verið valið að safna stigum fyrir.
     */
    final private int[] utkomaEftirTeningakast;
     /**
     * utkomaEftirTeningakast heldur utan um 
     * útkomurnar frá teningunum sex.
     */
    private int fjoldiKasta;
    /**
     * fjoldiKasta er heiltala sem heldur utan um fjölda kasta sem
     * leikmaður á eftir að kasta í hverri umferð leiksins.
     */
    
    private int heildarfjoldiUmferda;
    /**
     * heildarfjoldiUmferda er heiltala sem heldur utan um fjölda umferða
     * sem hafa verið spilaðar.
     */
    private boolean[] frystirTeningar;
    /**
     * frystirTeningar er boolean fylki sem heldur utan um hvaða
     * teningar eru frystir og hvaða teningar eru ekki frystir.
     * true ef teningar eru frystir en false annars.
     */
    
    private final boolean[] leikirSpiladirComp;
    /**
     * leikirSpiladirComp er boolean fylki sem heldur utan um hvaða
     * leiki tölvan hefur spilað. En leikirnir í Yatzee eru 15.
     */
    
    private boolean leikmadurALeik;
    /**
     * Segir til um hvort leikmadur eigi leik eða ekki.
     */
    
    final private boolean multiPlayer;
    /**
     * Segir til um hvort notandi er að spila gegn tölvu eða
     * einn, satt ef leikmaður er að spila gegn tölvu annars ósatt.
     */
            
    /**
     * YatzeeLeikur upphafstillir yatzeinn
     * @param multiPlayer Segir til um hvort notandi er að spila gegn tölvu eða
     * einn, satt ef leikmaður er að spila gegn tölvu annars ósatt.
     */
    public YatzeeLeikur(boolean multiPlayer)
    {
        this.fjoldiKasta = 3;
        this.multiPlayer = multiPlayer;
        this.leikmadurALeik = true;
        this.heildarfjoldiUmferda = -1;
        this.vyatzeeBladLeikmadur = new YatzeeBlad();
        this.vyatzeeBladTolva = new YatzeeBlad();
	this.utkomaEftirTeningakast = new int[5];
        this.veljaLeik = 0;
	this.vteningur = new Teningur();
        this.frystirTeningar = new boolean[5];
        this.leikirSpiladirComp = new boolean[15];
    }
    /**
     * Tilkynnir hvaða leik notandi ætlar að leika.
     * @param leikur heiltala sem táknar (ID) fyrir valinn leik
     * hjá notandi, þ.e. hverju hann ákvað að safna.
     */
    public void setValinnLeik(int leikur)
    {
        this.veljaLeik = leikur;
    }
    /**
     * Skilar leiknum sem notandi valdi síðast þegar hann gerði.
     * @return leiknum sem notandi valdi síðast
     */
    public int getValinnLeik()
    {
        return this.veljaLeik;
    }
    /**
     * Skilar fjölda kasta í hverri umferð.
     * @return fjöldi kasta í umferð
     */
    public int getFjoldiKasta()
    {
        return this.fjoldiKasta;
    }
    /**
     * Skilar heildarfjöldaumferða sem búnar eru í yatzee leiknum sem
     * verið er að spila.
     * @return heildarfjöldaumferða sem búnar eru í yatzee leiknum.
     */
    public int getFjoldiUmferda()
    {
        return this.heildarfjoldiUmferda;
    }
    /**
     * Skilar því hver á leik.
     * @return satt ef notandi á leik, ósatt ef tölvan á leik.
     */
    public boolean getLeikmaðurALeik()
    {
        return this.leikmadurALeik;
    }
    
    /**
     * Gefur hvaða teningar eru fyrstir og hverjir ekki.
     * @return true í sæti i ef teningur með ID i+1 er satt
     * annars false;
     */
    public boolean[] frystirTeningar()
    {
        return this.frystirTeningar;
    }

    /**
     * Skráir skorið inní stigatöfluna
     * @param tala stigafjoldinn sem fékkst
     * @param leikmadur er true ef leikmadur
     * átti leik annars false.
     */
    public void setjaToluIReit (int tala, boolean leikmadur)
    {
        if(leikmadur)
            vyatzeeBladLeikmadur.setjaToluIReit(veljaLeik,tala);
        else
            vyatzeeBladTolva.setjaToluIReit(veljaLeik,tala);
    }
    /**
     * Fallið kastar teningunum
     */
    public void kastaTeningum()
    {
    	int fjoldiTeninga = 5;
    	for(int i = 0; i<fjoldiTeninga; i++)
    	{
            if(frystirTeningar[i] == false)
            {
               utkomaEftirTeningakast[i] = vteningur.kastaTening(); 
            }     
    	}
        fjoldiKasta--;
    }
    
    
    /**
     * Fallið kallar á annað fall sem skilar slóð
     * á mynd af teningnum.
     * @param teningur er id fyrir teninginn.
     * @return slóð á mynd af tening sem táknar
     * útkomu teningsinsins
     */
    public ImageIcon synaTening(int teningur)
    {
        return vteningur.synaTening(teningur, utkomaEftirTeningakast[teningur]
                ,frystirTeningar[teningur]);
    }
    
    /**
     * Skilar samtals stigafjölda leikmannsins ásamt
     * bónusstigum og samanlögðum stigum fyrri hluta
     * Yatzee.
     * @param leikmadur er satt ef notandi á leik en 0 ef tölva
     * á leik.
     * @return  stigafjoldiUrYatzee er listi sem inniheldur 
     * heildarstigafjöldi sem leikmaður hefur safnað sér
     * inn, samanlögð stig fyrri hluta Yatzee, bónusstig og 
     * samanlögð stig fyrri hluta.
     * 
     * 
     */
    public int[] nidurstodur(boolean leikmadur)
    {
        if(leikmadur)
        {
            vyatzeeBladLeikmadur.uppfaerirNidurstodur();
            heildarfjoldiUmferda++;
        }
        else
            vyatzeeBladTolva.uppfaerirNidurstodur();
        int[] nidurstada = new int[3];
        for(int i = 1; i<4; i++)
        {
            if(leikmadur)
                nidurstada[i-1] = vyatzeeBladLeikmadur.yatzeeStigatafla[vyatzeeBladLeikmadur.yatzeeStigatafla.length-i];
            else
                nidurstada[i-1] = vyatzeeBladTolva.yatzeeStigatafla[vyatzeeBladTolva.yatzeeStigatafla.length-i];
        }
    	return nidurstada;
    }
    /**
     * fallið skilar heildarstigaskori bæði leikmanns og tölvunnar.
     * @return skilar heildarstigaskori leikmanns og tölvu.
     */
    public int[] heildarStigKeppenda()
    {
        YatzeeBlad[] vYatzeeBlad = new YatzeeBlad[]{vyatzeeBladLeikmadur,vyatzeeBladTolva};
        int[] stig = new int[2];       
        for(int i = 0; i<2;i++)
        {
            // heildarSkorið er geymt í sæti 17.
            stig[i] = vYatzeeBlad[i].yatzeeStigatafla[17];
        }
        
        return stig;
       
    }
    /**
     * Fallið frystir eða affrystir tening i.
     * @param i er númer (ID) tenings.
     */
    public void setAstandTeningur(int i)
    {
        frystirTeningar[i] = !frystirTeningar[i];
    }
    /**
     * Fallið affrystir alla teningana.
     */
    public void affrystaTeninga()
    {
        for(int j = 0; j<frystirTeningar.length;j++)
        {
            frystirTeningar[j] = false;
        }
        
    }
    
    /**
     * Skilar stigafjölda fyrir valinn leik.
     * @return stigafjöldi fyrir valinn leik.
     */
    public int reiknaUtStig()
    {
        int stig = 0;
        fjoldiKasta = 3;
        if(veljaLeik < 6)
        {
            stig = YatzeeReiknir.reiknaUpperSection(veljaLeik+1,utkomaEftirTeningakast);
        }
        else if(veljaLeik == 6)
        {
            stig = YatzeeReiknir.reiknaEittPar(utkomaEftirTeningakast);
        }
        else if(veljaLeik == 7)
        {
            stig = YatzeeReiknir.reiknaTvoPor(utkomaEftirTeningakast);
        }
        else if(veljaLeik == 8)
        {
            stig = YatzeeReiknir.reiknaThrirEins(utkomaEftirTeningakast);
        }
        else if(veljaLeik == 9)
        {
            stig = YatzeeReiknir.reiknaFjorirEins(utkomaEftirTeningakast);
        }
        else if(veljaLeik == 10)
        {
            stig = YatzeeReiknir.reiknaRod(veljaLeik,utkomaEftirTeningakast);
        }
        else if(veljaLeik == 11)
        {
            stig = YatzeeReiknir.reiknaRod(veljaLeik,utkomaEftirTeningakast);
        }
        else if(veljaLeik == 12)
        {
            stig = YatzeeReiknir.reiknaFulltHus(utkomaEftirTeningakast);
        }
        else if(veljaLeik == 13)
        {
            stig = YatzeeReiknir.reiknaTakaSensinn(utkomaEftirTeningakast);
        }
        else if(veljaLeik == 14)
        {
            stig = YatzeeReiknir.reiknaYatzee(utkomaEftirTeningakast);
        }
        if(multiPlayer)
            leikmadurALeik = !leikmadurALeik;
        return stig;
    }
    
    /**
     * Reiknar út fyrir tölvuna hvaða teninga er best að frysta.
     */
    public void bestAdFrysta()
    {
        frystirTeningar = YatzeeReiknir.akvedaBestuFrystuTeninga(utkomaEftirTeningakast, 4-fjoldiKasta, leikirSpiladirComp);
    }
    /**
     * Velur besta mögulegan leik eftir þrjú k0st fyrir tölvuna.
     */
    public void veljaBestaLeik()
    {
        veljaLeik = YatzeeReiknir.veljaBestaKostinn(utkomaEftirTeningakast, leikirSpiladirComp);
        leikirSpiladirComp[veljaLeik]= true;
        
    }
    
    

    
    
    
    
}
