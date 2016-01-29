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
 * @version 2
 * 
 * Keyrsla: ég bjó til .jar skrá sem er í dist með 
 * Clean og build og síðan exportaði úr Netbeans.
 * Notast við OS X Yosemite stýrikerfi og 
 * Java útgáfu 1.7.0_51
 * 
 * Dagsetning: 29/03/2015
 * 
 * Forritið: Sígildi leikurinn, Yatzee. Þar sem
 * bæði er hægt að fara eftir röð og ekki eftir röð.
 * 
 */

/**
 * 
 * Hlutverk YatzeeReiknir er að sjá um stigaútreikninga
 * fyrir Yatzee leikinn.
 * 
 */
public class YatzeeReiknir {
    
    

    /**
     * Fallið reiknar út stigafjöldann ef leikur er valinn
     * í efri helming (safna ásum - safna sexum) Yatzee leiksins
     * @param leikur segir til um hvaða leik í efri helmingnum á
     * að reikna stiginn fyrir.
     * @param teningar gefur til kynni gildin á teningunum.
     * @return samanlögð gildi á teningunum sem eru með gildi jöfn leikur.
     */
    public static int reiknaUpperSection(int leikur, int[] teningar){
        int stig = 0;
        for(int i = 0; i<teningar.length; i++)
        {
            if(teningar[i] == leikur)
                stig += teningar[i];
        }
        return stig;
    }
    /**
     * Reiknar bestu útkomuna fyrir eitt par miðað við gefna teninga.
     * @param teningar útkoman úr loka teningakastinu
     * @return hæsta summa gilda á teningunum sem bera eitta par ef það
     * er eitt par á teningunum, annars skilar fallið 0.
     */
    public static int reiknaEittPar(int[] teningar){
        int stig = 0;
        int[] pairIndices = findPairs(teningar);
        if(pairIndices[3]!=0)
        {
            int[] pair = finnaStaerraPar(pairIndices, teningar);
            stig = 2*teningar[pair[0]];
        }
        else if(pairIndices[1] != 0)
            stig = 2*teningar[pairIndices[1]];
        else
            stig = 0;
        return stig;
    }
    /**
     * Reiknar stigafjöldann fyrir tvö pör miðað við gefna teninga.
     * @param teningar útkoman úr loka teningakastinu
     * @return summa gilda á teningunum sem bera tvö pör ef það
     * eru tvö pör á teningunum, annar skilar fallið 0.
     */
    public static int reiknaTvoPor(int[] teningar)
    {
        int stig = 0;
        int[] pairIndices = findPairs(teningar);
        if(pairIndices[3]!=0)
        {
            stig = 2*(teningar[pairIndices[0]]+teningar[pairIndices[3]]);
        }
        else
            stig = 0;
        return stig;
    }
    /**
     * Reiknar stigafjöldann fyrir þrír eins miðað við gefna teninga.
     * @param teningar útkoman úr loka teningakastinu
     * @return summu teninganna þriggja sem eru eins ef það
     * eru þrír teningar eins, annars skilar fallið 0.
     */
    public static int reiknaThrirEins(int[] teningar)
    {
        int stig = 0;
        int[] threeOfAKindInd = finnaVisiTypeOfAKind(teningar,3);
        if(threeOfAKindInd[2]!=0)
        {
            stig = 3*teningar[threeOfAKindInd[2]];
        }
        return stig;     
    }
    
    /**
     * Reiknar stigafjöldann fyrir fjórir eins miðað við gefna teninga.
     * @param teningar útkoman úr loka teningakastinu
     * @return summu teninganna fjögurra sem eru eins ef það
     * eru fjórir teningar eins, annars skilar fallið 0.
     */
    public static int reiknaFjorirEins(int[] teningar)
    {
        int stig = 0;
        int[] fourOfAKindInd = finnaVisiTypeOfAKind(teningar,4);
        if(fourOfAKindInd[3]!=0)
        {
            stig = 4*teningar[fourOfAKindInd[2]];
        }
        return stig;  
    }
    /**
     * Reiknar stigafjöldann fyrir raðirnar eftir því hvora
     * röðina leikmaðurinn valdi.
     * @param leikur gefur til kynna um hvort sé að ræða stóra röð
     * eða litla röð.
     * @param teningar útkomurnar úr síðasta teningakastinu.
     * @return Ef lítil röð kom uppá teningunum og leikur = 10 þá skilar fallið
     * summu teninganna, annars 0. Ef stór röð kom uppá teningunum og 
     * leikur = 11 þá skilar fallið summu teninganna, annars 0.
     */
    public static int reiknaRod(int leikur, int[] teningar)
    {
        int stig = 0;
        int rodTypa = 0;
        if (leikur == 10)
            rodTypa = 1;
        else if(leikur == 11)
            rodTypa = 2;
        else
            System.out.println("Inntakið er ekki lítil eða stór röð");
        int[] teningarIRod = teningar.clone();
        java.util.Arrays.sort(teningarIRod);
        for(int i = 0; i < teningar.length;i++)
        {
            if(teningarIRod[i] != i+rodTypa)
                return 0;
            stig += teningarIRod[i];
            
        }
        return stig;
    }
    /**
     * Reiknar stigafjöldann fyrir fullt hús miðað við gefna teninga.
     * @param teningar útkoman úr loka teningakastinu
     * @return summa gildanna á teningunum ef upp kom fullt hús í
     * teningunum annars 0.
     */
    public static int reiknaFulltHus(int[] teningar)
    {
        int stig = 0;
        int[] threeOfAKindInd = finnaVisiTypeOfAKind(teningar,3);
        int[] pairIndices = findPairs(teningar);
        int[] fourOfAKindInd = finnaVisiTypeOfAKind(teningar,4);
        int[] fiveOfAKindInd = finnaVisiTypeOfAKind(teningar,5);
        if(threeOfAKindInd[2]!=0 &&pairIndices[3]!=0 && (fourOfAKindInd[3]==0||fiveOfAKindInd[4]!=0))
        {
            if(teningar[threeOfAKindInd[2]] != teningar[pairIndices[3]])
                stig = 3*teningar[threeOfAKindInd[2]]+2*teningar[pairIndices[3]];
            else
                stig = 3*teningar[threeOfAKindInd[2]]+2*teningar[pairIndices[0]];
        }
        return stig;
    }
    
    /**
     * Reiknar stigafjöldann fyrir að taka sénsinn miðað við gefna teninga.
     * @param teningar útkoman úr loka teningakastinu
     * @return summa gildanna á teningunum.
     */
    public static int reiknaTakaSensinn(int[] teningar)
    {
        int stig = 0;
        for(int teningur:teningar )
        {
            stig += teningur;
        }
        return stig;
    }
    /**
     * Reiknar stigafjöldann fyrir yatzee miðað við gefna teninga. 
     * @param teningar útkoman úr loka teningakastinu
     * @return 50 ef leikmaður fékk yatzee annars 0.
     */
    public static int reiknaYatzee(int[] teningar)
    {
        int stig = 50;
        for(int i = 1; i<teningar.length ;i++)
        {
            if(teningar[i]!=teningar[i-1])
            {
                return 0;
            }
        }
        return stig; 
    }
    
    /**
     * 
     * @param values heldur utan um frysta teninga auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga, er af lengd 6. 
     * |ástand tenings 1|ástand tenings 2|ástand tenings 3|ástand tenings 4|ástand tenings 5|væntanlegt gildi|
     * @param frystirTeningar heldur utan um núverandi frysta teninga.
     */
    public static void frystaTeninga(double[] values, boolean[] frystirTeningar)
    {
        for(int i = 0; i<5; i++)
        {
            frystirTeningar[i] = (int) values[i] == 1;
        }
         
    }
    /**
     * Fallið sklar haestaVæntigildinu hingað til, það er af þeim leikjum sem
     * búið er að prufa.
     * @param values heldur utan um frysta teninga auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga, er af lengd 6. 
     * @param leikur hvaða leik er verið að skoða hvort sé bestur hingað til.
     * @param leikirSpiladir hvert hólf táknar ákveðinn leik og er true í 
     * þeim hólfum sem búið er að leika leik (samsetningu), annars false.
     * @param haestaVaentiGildi hæsta væntanlega gildið úr teningakasti hingað til, þegar 
     * ekki er búið að skoða leik með ID-ið leikur.
     * @param frystirTeningar segir til um hvort teningar séu frystir eða ekki, t.d. 
     * er true í hólfi 1 ef teningur 1 er frystur, false í hólfi 2 ef teningur 2 er 
     * ekki frystur o.s.frv.
     * @return hæsta væntanlega gildið úr teningakasti hingað til, þegar búið er að skoða
     * leik með ID-ið leikur.
     */
    public static double frystaEfBestiLeikur(double[] values, int leikur, boolean[] leikirSpiladir, double haestaVaentiGildi, boolean[] frystirTeningar)
    {
        if(leikirSpiladir[leikur] == false && values[5]>haestaVaentiGildi)
        {
            haestaVaentiGildi = values[5];
            frystaTeninga(values, frystirTeningar);
        }
        return haestaVaentiGildi;
    }
    /**
     * Athugar hvort þessi leikur er besti leikurinn hingað til og ef svo er þá
     * skráir það leikinn niður.
     * @param leikirSpiladir hvert hólf táknar ákveðinn leik og er true í 
     * þeim hólfum sem búið er að leika leik (samsetningu), annars false.
     * @param stig stig leikmanns fyrir gefinn leik
     * @param bestiLeikurinn í hólfi 1 er besti leikurinn hingað til og í hólfi 2
     * fjöldi stiga fyrir þann leik.
     * @param leikur hvaða leik er verið að skoða hvort sé bestur hingað til.
     * @param aukaSkorda ef nauðsynlegt er að hafa aukaskorðu fyrir mikilvægan leik,
     * til að mynda ef tölvan á ekki að velja leikinn nema að skila X mörgum stigum.
     */
    public static void skraEfBestiLeikur(boolean[] leikirSpiladir, int stig, int[] bestiLeikurinn, int leikur, boolean aukaSkorda)
    {
        if(leikirSpiladir[leikur]==false && stig> bestiLeikurinn[1] && aukaSkorda)
        {
            bestiLeikurinn[0] = leikur;
            bestiLeikurinn[1] = stig;
        }
        
    }
    
    
    /**
     * Skilar hagstæðasta leiknum fyrir tölvuna miðað við ákveðið reiknirit.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param leikirSpiladir hvert hólf táknar ákveðinn leik og er true í 
     * þeim hólfum sem búið er að leika leik (samsetningu), annars false.
     * @return skilar hagstæðasta leiknum fyrir tölvuna miðað við ákveðið reiknirit.
     */
    public static int veljaBestaKostinn(int[] teningar, boolean[] leikirSpiladir)
    {
        int haestiStigafjoldi = -1;
        int skastiLeikur = -1;
        int[] bestiLeikurinn = new int[]{skastiLeikur, haestiStigafjoldi};
        int stig;
        int leikur = leikirSpiladir.length;
        boolean aukaSkorda = true;
        // Reikna útkomu fyrir Yatzee.
        stig = reiknaYatzee(teningar);
        skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, --leikur, aukaSkorda);
        // Tökum ekki sénsinn nema í ítrustu nauðsyn.
        leikur--;
        // Reikna útkomu fyrir fullt hús.
        aukaSkorda = true;
        stig = reiknaFulltHus(teningar);
        skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, --leikur, aukaSkorda);
        // Reikna útkomu fyrir röð.
        for(int rod = 11; rod >=10; rod--)
        {
            stig = reiknaRod(rod,teningar);
            skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, rod, aukaSkorda);
            leikur--;
        }
        // Reikna útkomu fyrir fjóra eins.
        stig = reiknaFjorirEins(teningar);
        skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, --leikur, aukaSkorda);
        // Reikna útkomu fyrir þrír eins.
        stig = reiknaThrirEins(teningar);
        skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, --leikur, aukaSkorda);
        // Reikna útkomu fyrir tvö pör.
        stig = reiknaTvoPor(teningar);
        skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, --leikur, aukaSkorda);
        // Reikna útkomu fyrir eitt par.
        stig = reiknaEittPar(teningar);
        skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, --leikur, aukaSkorda);
        // // Reikna útkomurnar þegar safnað er ákveðnum gildum.
        for(int leikurUS = 6; leikurUS>=1; leikurUS--)
        {
            stig = reiknaUpperSection(leikurUS, teningar);
            if(leikurUS >3)
                aukaSkorda = stig >= 2*leikurUS;
            else
                aukaSkorda = stig >= 3*leikurUS;
            skraEfBestiLeikur(leikirSpiladir, stig, bestiLeikurinn, --leikur, aukaSkorda);
        }
        // Ef enginn ásættanlegur kostur kom upp.
        if(bestiLeikurinn[1] <= 0)
        {
            veljaSkastaKostinn(leikirSpiladir,bestiLeikurinn, teningar);
        }
        return bestiLeikurinn[0];
        
        
        
   
    }
    
    /**
     * Finnur skásta kostinn ef enginn góður er mögulegur.
     * @param leikirSpiladir hvert hólf táknar ákveðinn leik og er true í 
     * þeim hólfum sem búið er að leika leik (samsetningu), annars false.
     * @param bestiLeikurinn í hólfi 1 er besti leikurinn hingað til og í hólfi 2
     * fjöldi stiga fyrir þann leik.
     * @param teningar er útkoman úr síðasta teningakasti.
     */
    public static void veljaSkastaKostinn(boolean[] leikirSpiladir, int[] bestiLeikurinn, int[] teningar)
    {
        int[] leikirMedAukaSkordu = new int[]{0,1,2,3,4,5,13};
        int haestiFjoldi = 0;
        int stig = reiknaTakaSensinn(teningar);
        if(stig >5*4.8 && !leikirSpiladir[13])
        {
            bestiLeikurinn[0] = 13;            
        }

        else
        {
            for(int leikir: leikirMedAukaSkordu)
            {
                if(leikir < 6)
                {
                    int fjoldi = reiknaUpperSection(leikir+1, teningar)/(leikir+1);
                    if(!leikirSpiladir[leikir] && fjoldi>haestiFjoldi)
                    {
                        haestiFjoldi = fjoldi;
                        bestiLeikurinn[0] = leikir;
                    }
                }
                if(leikir == 13 && haestiFjoldi ==0 && !leikirSpiladir[leikir])
                {
                    bestiLeikurinn[0] = leikir;
                }
            }
        }
      
                
    }
    
    
    /**
     * Finnur hvaða teninga er hagstæðast að frysta miðað við gefið reiknirit.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @param leikirSpiladir hvert hólf táknar ákveðinn leik og er true í 
     * þeim hólfum sem búið er að leika leik (samsetningu), annars false.
     * @return fylki með true í þeim reitum hjá teningum sem er hagstæðast að frysta
     * annars false.
     */
    public static boolean[] akvedaBestuFrystuTeninga(int[] teningar, int numerKasts, boolean[] leikirSpiladir)
    {
        boolean frystirTeningar[] = new boolean[5];
        double haestaVaentiGildi = -1.0;
        double values[] = new double[6];
        int leikur = leikirSpiladir.length;
        // Er Yatzee besta niðurstaðan hingað til?
        values = estimatedValueYatzee(teningar, numerKasts);
        haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        // Er Taka Sensinn besta niðurstaðan hingað til?
        values = estimatedValueTakaSensinn(teningar, numerKasts);
        //kemur í veg fyrir að valið sé alltaf taka sénsinn.
        values[5] = 7;
        haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        // Er fullt hús besta niðurstaðan hingað til?
        values = estimatedValueFulltHus(teningar, numerKasts);
        haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        // Er önnur hvor röðin besta niðurstaðan hingað til? rod == 0 litilrod en rod == 1 stór.
        for(int rod = 1; rod >=0; rod--)
        {
            values = estimatedValueRod(teningar, numerKasts,rod);
            haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);             
        }
        // Eru fjórir eins besta niðurstaðan hingað til?
        values = estimatedValueOfFourOfAKind(teningar, numerKasts);
        haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        // Eru þrír eins besta niðurstaðan hingað til?
        values = estimatedValueOfThreeOfAKind(teningar, numerKasts);
        haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        // Eru tvö pör besta niðurstaðan hingað til?
        values = estimatedValueOfTwoPair(teningar, numerKasts);
        haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        // Er eitt par besta niðurstaðan hingað til?
        values = estimatedValueOfOnePair(teningar, numerKasts);
        haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        // Er best að safna ákveðinni sort? leikurUS == i þýðir að safna i.
        for(int leikurUS = 6; leikurUS>0; leikurUS--)
        {
            values = estimatedValueOfUpperSection(teningar, leikurUS, numerKasts);
            haestaVaentiGildi = frystaEfBestiLeikur( values, --leikur, leikirSpiladir, haestaVaentiGildi, frystirTeningar);
        }
        return frystirTeningar;
    }
    /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er gildum á bilinu 1-6.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param leikur ID-ið fyrir leikinn
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */
    public static double[] estimatedValueOfUpperSection(int[] teningar, int leikur, int numerKasts)
    {
        double[] values = new double[6];
        double prob = (1.0-Math.pow(5.0/6.0, (3-numerKasts)));
        values[5] = 0;
        for(int i = 0; i<teningar.length; i++)
        {
            if(teningar[i] == leikur)
            {
                values[5] += leikur;
                values[i] = 1;
            }
            else
            {
                values[5] += prob*leikur;
                values[i] = 0;
            }
        }
        return values;
    }
    /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er einu pari.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */
    public static double[] estimatedValueOfOnePair(int[] teningar, int numerKasts)
    {
        double[] values = new double[6];
        int[] pairIndices = findPairs(teningar);
        int[] StaerstInd = finnaVisaIStaerdarrod(teningar);
        int[] merking;
        // Ef upp komu tvö pör
        if(pairIndices[2] != 0)
        {
            merking = finnaStaerraPar(pairIndices, teningar);
            values[5] = 2*teningar[merking[0]];
            merkjaTeninga(values,merking);  
        }
        double prob = probabilityOfMoreThenXIdenticalValues(1,numerKasts,4);
        double estimatedValue = 2*StaerstInd[0]*prob;
        merking = new int[]{StaerstInd[0]};
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values;
    }
    /**
     * Reiknar líkurnar á meira en x fjölda teninga með gildið y þegar eftir
     * er að kasta ákveðið mörgum teningum
     * @param x fjöldi útkoma
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @param outOfDices fjöldi teninga sem eru ekki frystir.
     * @return  líkunum á meiri en x fjölda teninga með samskonar gildi
     * þegar eftir er að kasta outOfDices mörgum teningum í numerKast köstum.
     */
    public static double probabilityOfMoreThenXIdenticalValues(int x,int numerKasts, int outOfDices)
    {
        double prob = 0;
        for(int i = x;i <= outOfDices;i++ )
        {
            prob += probabiltyOfXIdenticalValues(i,numerKasts, outOfDices);
        }
        return prob;
        
    }
    

    /**
     * Skilar uppfærðu gildi á best frystu teningunum hingað til ásamt væntigildi
     * þess.
     * @param values heldur utan um frysta teninga auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga, er af lengd 6. 
     * @param merking heldur utan um frysta teninga.
     * @param estValue er væntanlegt gildi fyrir leik sem var verið að reikna
     * væntigildið fyrir
     */
    public static void uppfaeraVaentanlegtGildi(double[] values, int[] merking, double estValue)
    {
        if(estValue > values[5])
        {
            values[5] = estValue;
            merkjaTeninga(values,merking);
        }
        
    }
    
    /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er tveimur pörum.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */
    public static double[] estimatedValueOfTwoPair(int[] teningar, int numerKasts)
    {
        double[] values = new double[6];
        values[5] = 0;
        int[] pairIndices = findPairs(teningar);
        boolean tvoPor = pairIndices[3] !=0;
        boolean eittPar = pairIndices[1] !=0 && !tvoPor;
        double estimatedValue;
        // Væntanlegt gildi teninga miðað við að velja hæstu tvö gildin
        int[] StaerstInd = finnaVisaIStaerdarrod(teningar);
        //Stig fyrir leik ef tvö pör hafa þegar komið upp og ákveðið er að velja þau
        if(tvoPor)
        {
            values[5] = teningar[pairIndices[0]]*2+
                    teningar[pairIndices[2]]*2;
            merkjaTeninga(values,pairIndices);    
        }
        // Væntanleg stig fyrir leik ef eitt par hefur þegar komið upp og 
        //ákveðið er að velja það
        if(eittPar)
        {
            int[] par = new int[2];
            System.arraycopy(pairIndices,0,par,0,2);
            int haestaGildiEkkiPar = highestValueIndexNotIn( par, StaerstInd );
            double prob = probabiltyOfXIdenticalValues(1,numerKasts,2);
            estimatedValue = (2*teningar[pairIndices[0]]+2*teningar[haestaGildiEkkiPar])*prob;
            int[] merking = new int[]{pairIndices[0],pairIndices[1],haestaGildiEkkiPar};
            uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        }
        // Væntanleg stig fyrir leik ef ekkert par kom upp.
        double x = Math.pow(5.0/6.0,3);
        double prob = probabiltyOfAtLeastXManyTwoDifferentCertainValues(1,1,numerKasts,3);
        estimatedValue = (2*teningar[StaerstInd[0]]+2*teningar[StaerstInd[1]])*prob;
        int[] merking = new int[]{StaerstInd[0]};
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values;
    }
    /**
     * Reiknar líkurnar á X fjölda teninga með x sem gildi og Y fjölda
     * teninga með y sem gildi úr ákveðnum mörkum köstum.
     * @param numberValue1 fjöldi gilda með útkomuna x
     * @param numberValue2 fjöldi gilda með útkomuna y
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @param outOfDices fjöldi teninga sem eru ekki frystir.
     * @return líkurnar á numberValue1 fjölda teninga af gildi x og numberValue2 af gildi y þegar eftir er að kasta outOfDices
     * mörgum teningum í numerKast köstum.
     */
    public static double probabiltyOfGettingTwoDifferentCertainValues(int numberValue1, int numberValue2,int numerKasts, int outOfDices)
    {
        double p = 1.0/6.0;
        int N = numberValue1 + numberValue2;
        double prob = choose(N,numberValue1)*choose(outOfDices,N)*Math.pow(p,N)*Math.pow(4.0/6.0,(outOfDices-N)*(3-numerKasts));
        double count;
        if(numerKasts == 1)
        {

            for(int i = 1; i<=N;i++)
            {
                if(outOfDices-i == 0)
                    count = 1;
                else
                    count = choose(outOfDices,N-i);
                prob += choose(N,numberValue1)*count*choose(outOfDices-N+i,i)*Math.pow(p,N)*Math.pow(4.0/6.0,(outOfDices-N)*(3-numerKasts)+i);
            }
        }
        return prob;
    }
    /**
     * Reiknar líkurnar á x fjölda teninga með gildið y þegar eftir
     * er að kasta ákveðið mörgum teningum
     * @param x fjöldi útkoma
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @param outOfDices fjöldi teninga sem eru ekki frystir.
     * @return  líkunum á x fjölda teninga með samskonar gildi
     * þegar eftir er að kasta outOfDices mörgum teningum í numerKast köstum.
     */
    public static double probabiltyOfXIdenticalValues(int x,int numerKasts, int outOfDices)
    {
        double p = 1.0/6.0;
        double prob = choose(outOfDices,x)*Math.pow(p,x)*Math.pow((1-p),(outOfDices-x)*(3-numerKasts));
        double count;
        if(numerKasts == 1)
        {

            for(int i = 1; i<=x;i++)
            {
                if(outOfDices-i == 0)
                    count = 1;
                else
                    count = choose(outOfDices,x-i);
                prob += count*choose(outOfDices-x+i,i)*Math.pow(p,x)*Math.pow((1-p),(outOfDices-x)*(3-numerKasts)+i);
            }
        }
        return prob;
    }
    /**
     * Reiknar líkurnar á a.m.k. X fjölda teninga með x sem gildi og Y fjölda
     * teninga með y sem gildi úr ákveðnum mörkum köstum.
     * @param numberValue1 fjöldi gilda með útkomuna x
     * @param numberValue2 fjöldi gilda með útkomuna y
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @param outOfDices fjöldi teninga sem eru ekki frystir.
     * @return líkurnar á a.m.k. numberValue1 fjölda teninga af gildi x og numberValue2 af gildi y þegar eftir er að kasta outOfDices
     * mörgum teningum í numerKast köstum
     */
    public static double probabiltyOfAtLeastXManyTwoDifferentCertainValues(int numberValue1, int numberValue2,int numerKasts, int outOfDices)
    {
        int N = numberValue1 + numberValue2;
        double prob = 0;
        for(int i = numberValue1; i<=outOfDices-numberValue2;i++)
        {
            for(int j = numberValue2; j+i<=outOfDices; j++)
            {
                prob += probabiltyOfGettingTwoDifferentCertainValues(i, j,numerKasts, outOfDices);
            }
        }
        return prob;
    
    }
    /**
     * Fyrsta gildinu í vigri X sem er ekki í Y.
     * @param vigur vigur með heiltölum
     * @param staerstInd vigur með heiltölum
     * @return fyrsta gildinu í staerstInd sem er ekki í vigur
     */
    public static int highestValueIndexNotIn( int[] vigur, int[] staerstInd )
    {
        int haestaGildiEkkiPar = 0;
        for(int i = 0; i<staerstInd.length;i++)
        {
            if(!inArray(vigur,staerstInd[i]))
                return staerstInd[i];
        }
        return haestaGildiEkkiPar; 
    }
    /**
     * Skilar satt ef gildi er ekki í ákveðnum vigri.
     * @param array vigur
     * @param value gildi
     * @return skilar true ef value er ekki í array1.
     */
    public static boolean inArray(int[] array, int value)
    {
        for(int elem:array)
        {
            if(elem == value)
                return true;
        }
        return false;  
    }
    /**
     * Ert með rosaleg þolinmæði ef þú ert enn að lesa í gegnum kóðann.
     */
    
     /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er þremur eins.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */   
    public static double[] estimatedValueOfThreeOfAKind(int[] teningar, int numerKasts)
    {
        double[] values = new double[6];
        double estimatedValue;
        int[] StaerstInd = finnaVisaIStaerdarrod(teningar);
        values[5] = 0;
        int[] threeOfAKindInd = finnaVisiTypeOfAKind(teningar,3);
        int[] pairIndices = findPairs(teningar);
        //Ef upp koma þrír eins
        if(threeOfAKindInd[2] != 0)
        {
            estimatedValue = 3*teningar[threeOfAKindInd[0]];
            values[5] = estimatedValue;
            merkjaTeninga(values,threeOfAKindInd);
        }
        //Ef upp kemur eitt par
        if(pairIndices[1] != 0 && pairIndices[3] == 0)
        {
            double prob = probabiltyOfXIdenticalValues(1,numerKasts,3);
            estimatedValue = 3*teningar[pairIndices[0]]*prob;
            int[] merking = new int[]{pairIndices[0],pairIndices[1]};
            uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        }
        //Ef upp koma tvö pör
        if(pairIndices[3] != 0)
        {
            int[] merking;
            merking = finnaStaerraPar(pairIndices,teningar);
            double prob = probabilityOfMoreThenXIdenticalValues(1,numerKasts,3);
            estimatedValue = 3*teningar[merking[0]]*prob;
            uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        }
        // Ef engir teningar eru eins.
        double prob = probabilityOfMoreThenXIdenticalValues(2,numerKasts,4);
        estimatedValue = 3*teningar[StaerstInd[0]]*prob;
        int[] merking = new int[]{StaerstInd[0]};
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values;
    }
    /**
     * Fallið skilar hærra parinu af tveimur pörum.
     * @param pairInd vísar á pör í teningum, þar sem fyrstu tvö gildin
     * vísa á teninga með sama gildi og seinni tvö líka.
     * @param teningar gildi úr teningakasti
     * @return vigri með vísi á teningar með þeim pörum með hæsta gildinu.
     */
    public static int[] finnaStaerraPar(int[] pairInd, int[] teningar)
    {
        int[] merking;
        if(teningar[pairInd[2]]>teningar[pairInd[0]])
        {
            merking = new int[]{pairInd[2],pairInd[3]};
         
        }
        else
        {
            merking = new int[]{pairInd[0],pairInd[1]};              
        }
        return merking;
        
    }
     /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er fjórum eins.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */     
    public static double[] estimatedValueOfFourOfAKind(int[] teningar, int numerKasts)
    {
        double[] values = new double[6];
        int[] fourOfAKindInd = finnaVisiTypeOfAKind(teningar,4);
        int[] threeOfAKindInd = finnaVisiTypeOfAKind(teningar,3);
        int[] pairIndices = findPairs(teningar);
        int[] StaerstInd = finnaVisaIStaerdarrod(teningar);
        double estimatedValue;
        //Ef fjórir eins koma upp
        if(fourOfAKindInd[3] != 0)
        {
            estimatedValue = 4*teningar[fourOfAKindInd[0]];
            values[5] = estimatedValue;
            merkjaTeninga(values,fourOfAKindInd);
        }
        //Ef þrír eins koma upp
        if(threeOfAKindInd[2] != 0)
        {
            double prob = probabilityOfMoreThenXIdenticalValues(1,numerKasts,2);
            estimatedValue = 4*teningar[threeOfAKindInd[0]]*prob;
            uppfaeraVaentanlegtGildi(values, threeOfAKindInd, estimatedValue);
        }
        //Ef eitt par kemur upp
        if(pairIndices[1] != 0 && pairIndices[3] == 0)
        {
            double prob = probabilityOfMoreThenXIdenticalValues(2,numerKasts,3);
            estimatedValue = 4*teningar[pairIndices[0]]*prob;
            int[] merking = new int[]{pairIndices[0],pairIndices[1]};
            uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        }
        //Ef tvö pör koma upp
        if(pairIndices[3] != 0)
        {
            int[] merking = new int[2];
            merking = finnaStaerraPar(pairIndices,teningar);
            double prob = probabilityOfMoreThenXIdenticalValues(2,numerKasts,3);
            estimatedValue = 4*teningar[merking[0]]*prob;
            uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        }
        //Ef ekkert par kemur upp
        double prob = probabilityOfMoreThenXIdenticalValues(3,numerKasts,4);
        estimatedValue = 4*teningar[StaerstInd[0]]*prob;
        int[] merking = new int[]{StaerstInd[0]};
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values; 
    }
    /**
     * Skilar því pari sem er ekki hluti af þrír eins.
     * @param merking vigur sem heldur utan um parið sem er ekki hluti
     * af þrír eins.
     * @param teningar útkoma í teningakasti
     * @param threeOfAKindInd vísar á þrír eins í teningakasti
     * @param pairInd vísar á pör í teningakasti
     */
    public static void merkjaParNotInX(int[] merking, int[] teningar, int[] threeOfAKindInd, int[] pairInd)
    {
        merking[3] = pairInd[2];
        merking[4] = pairInd[3];
        for(int i = 0; i < threeOfAKindInd.length;i++)
        {
            if(threeOfAKindInd[i] == pairInd[2])
            {
                merking[3] = pairInd[0];
                merking[4] = pairInd[1];
                break;
            }
        }    
    }
     /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er fullu húsi.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */      
    public static double[] estimatedValueFulltHus(int[] teningar, int numerKasts)
    {
        double[] values = new double[6];
        int[] threeOfAKindInd = finnaVisiTypeOfAKind(teningar,3);
        int[] pairIndices = findPairs(teningar);
        int[] staerstInd = finnaVisaIStaerdarrod(teningar);
        double estimatedValue;
        // Ef upp koma tvö pör
        if(pairIndices[3] != 0)
        {
            // Ef fullt hús er í borði
            if(threeOfAKindInd[2] != 0 && teningar[pairIndices[0]]!=teningar[pairIndices[3]])
            {
                int[] merking = new int[5];
                System.arraycopy(threeOfAKindInd, 0, merking, 0, threeOfAKindInd.length);
                merkjaParNotInX(merking, teningar, threeOfAKindInd,pairIndices);
                estimatedValue = teningar[merking[0]]*3+teningar[merking[1]]*2;
                values[5] = estimatedValue;
                merkjaTeninga(values,merking);
            }
            // Aldrei betra að reyna að fá annað hærra hús með því að breyta einum
            // tening ef hús er nú þegar komið
            else
            {
                // Frysta bæði pörin
                double prob;
                int[] merking = new int[3];
                if(teningar[pairIndices[0]] != teningar[pairIndices[3]])
                    prob = (1-Math.pow(4.0/6.0,(3-numerKasts)));
                else
                    prob = probabiltyOfXIdenticalValues(1,numerKasts,1);
                estimatedValue = prob/2 * (teningar[pairIndices[1]]*3+teningar[pairIndices[3]]*2+
                        teningar[pairIndices[1]]*2+teningar[pairIndices[3]]*3);
                values[5] = estimatedValue;
                merkjaTeninga(values,pairIndices);
            }
        }
        //Ef eitt par kemur upp
        if(pairIndices[3] == 0 && pairIndices[1] != 0)
        {
            //Ef það er hluti af þremur eins.
            if(threeOfAKindInd[2] != 0)
            {
                int[] merking = new int[4];
                System.arraycopy(threeOfAKindInd, 0, merking, 0, threeOfAKindInd.length);
                merking[3] = highestValueIndexNotIn(threeOfAKindInd,staerstInd);
                double prob = probabiltyOfXIdenticalValues(1,numerKasts,1);
                estimatedValue = prob*(3*teningar[merking[0]]+2*teningar[merking[3]]);
                uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
            }
            else
            {
                int[] merking = new int[]{pairIndices[0],pairIndices[1],0};
                // Líkur á að 2 eins komi upp á tveimur teningum.
                double prob1 = probabiltyOfXIdenticalValues(2,numerKasts,2);
                // Líkur á að 2 ákveðin gildi (sitthvort gildið) komi uppá tveimur teningum
                double prob2 = probabiltyOfGettingTwoDifferentCertainValues(1,1,numerKasts,2);
                merking[2] = highestValueIndexNotIn(merking,staerstInd);
                estimatedValue = prob1*(3*teningar[merking[2]]+2*teningar[merking[0]])
                        +prob2*(3*teningar[merking[2]]+2*teningar[merking[0]]);
                uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
            }
        }
        //Enginn eins kemur upp
        double prob = 2*probabiltyOfGettingTwoDifferentCertainValues(1,2,numerKasts,3);
        estimatedValue = prob/2*(3*teningar[staerstInd[0]]+2*teningar[staerstInd[1]]+
                2*teningar[staerstInd[0]]+3*teningar[staerstInd[1]]);
        int[] merking = new int[]{staerstInd[0],staerstInd[1]};
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values;
    }
     /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er röð.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @param rod 1 ef um stóra röð er verið að ræða annars 0
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */     
    public static double[] estimatedValueRod(int[] teningar, int numerKasts, int rod)
    {
        double[] values = new double[6];
        int[] merking = new int[]{10,10,10,10,10};
        double estimatedValue;
        values[5] = 0;
        int eftirAdSafna = 5-fjoldiGildaIRod(teningar, rod, merking);
        double prob = probabilityOfxDifferentValues(eftirAdSafna, numerKasts);
        estimatedValue = (20*rod+15*(1-rod))*prob;
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values;  
    }
     /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað er eins hárri útkomu teninga og hægt er.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */     
    public static double[] estimatedValueTakaSensinn(int[] teningar, int numerKasts)
    {
        double[] values = new double[6];
        int[] merking = new int[]{10,10,10,10,10};
        double estimatedValue;
        int[] HaGildi = fjoldiHarraGilda( teningar, merking);
        double expXDices = ExpectedValueOfxDices(5-HaGildi[0],numerKasts);
        estimatedValue = HaGildi[1] + expXDices;
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values;  
    }
    /**
     * Reiknar væntigildið við að kasta x mörgum teningum y sinnum.
     * @param x fjöldi teninga.
     * @param numerKasts fjöldi kasta eftir í umferð
     * @return væntigildi teningakast þegar kastað er x teningum numerKasts
     * sinnum
     */
    public static double ExpectedValueOfxDices(int x, int numerKasts)
    {
        double p = 3.0/6.0;
        double cumBinVal = binomial(p,x,0)*x*3.5;
        if(numerKasts == 1)
        {
            for(int i = 1; i<=x; i++)
            {
                cumBinVal += binomial(p,x,i)*(5*i+3.5*(x-i));
            }
        }
       return cumBinVal;   
    }
     /**
     * Skilar ástandi teninga auk væntanlegrar útkomu miðað við þetta
     * ástand, þegar safnað Yatzee.
     * @param teningar er útkoman úr síðasta teningakasti.
     * @param numerKasts númer kasts hjá tölvu í þessari umferð.
     * @return skilar frystum teningum auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga. Fyrstu 5 sætin tákna teningana og 
     * 1.0 er í teningunum sem eru frystir og 0.0 hjá þeim sem eru ekki frystir.
     */ 
    public static double[] estimatedValueYatzee(int[] teningar, int numerKasts)
    {
        double[] values = new double[6];
        int[] merking = new int[]{10,10,10,10,10};
        double estimatedValue;
        int einsGildi = haestiFjoldiGildaEins(teningar, merking);
        double prob = probabiltyOfXIdenticalValues(5-einsGildi,numerKasts,5-einsGildi);
        estimatedValue = prob*50;
        uppfaeraVaentanlegtGildi(values, merking, estimatedValue);
        return values;
    }
    

    /**
     * Reiknar út mesta fjölda gilda á tening sem eru eins.
     * @param teningar útkoman úr teningaKasti
     * @param merking heldur utan um teninga sem eru eins
     * @return fjöldi gilda eins.
     */
    public static int haestiFjoldiGildaEins(int[] teningar, int[] merking)
    { 
        int haestiFjoldiEins = 1;
        int n = haestiFjoldiEins;
        for(int i = 0; i<teningar.length;i++)
        {
            int[] tmpMerking = new int[]{10,10,10,10,10};
            tmpMerking[i] = i;
            for(int j = i+1; j<teningar.length;j++)
            {
                if(teningar[i] == teningar[j])
                {
                    n++;
                    tmpMerking[j] = j;
                    if(n > haestiFjoldiEins)
                    {
                        haestiFjoldiEins = n;
                        System.arraycopy(tmpMerking, 0, merking, 0, merking.length);
                    }
                }     
            }
            n = 1;
        }
        if(haestiFjoldiEins == 1)
        {
            merking[0] = 0;
        }
        return haestiFjoldiEins;
    }

    /**
     * Reiknar út mesta fjölda gilda á tening sem eru hærri en 3.
     * @param teningar útkoman úr teningaKasti
     * @param merking heldur utan um teninga sem eru hærri en 3
     * @return fjöldi gilda hærri en 3.
     */
    public static int[] fjoldiHarraGilda(int[] teningar, int[] merking)
    {
        int sumHaGildi = 0;
        int n = 0;
        int i = 0;
        int[] result;
        for(int teningur: teningar)
        {
            if(teningur>3)
            {
                merking[i] = i;
                sumHaGildi+= teningur;
                n++;      
            }
            i++;
        }
        result = new int[]{n,sumHaGildi};
        return result;       
    }
    /**
     * Reiknar út mesta fjölda gilda í röð.
     * @param teningar útkoman úr teningaKasti
     * @param rod 1 ef um stóra röð er verið að ræða annars 0
     * @param merking heldur utan um teninga sem eru í röð
     * @return fjöldi gilda í röð.
     */
    public static int fjoldiGildaIRod(int[] teningar, int rod, int[] merking)
    {
        boolean[] gildiIRodKomid = new boolean[6];
        int n = 0;
        int i = 0;
        for(int teningur: teningar)
        {
            if(teningur>rod &&teningur<6+rod && gildiIRodKomid[teningur-1] == false)
            {
                gildiIRodKomid[teningur-1] = true;
                n++;
                merking[i] = i;
            }
            i++;
        }
        return n;
    }
    /**
     * Reiknar líkurnar á x ólíkum gildum í y köstum
     * @param x fjöldi ólíkra gilda
     * @param numerKasts fjöldi kasta sem á eftir að kasta í umferð leikmanns.
     * @return líkunum á x ólíkum gildum í numerKasts köstum
     */
    public static double probabilityOfxDifferentValues(int x,int numerKasts)
    {
        double prob;
        double probMultiplier;
        probMultiplier = factorial(x)/Math.pow(6.0,x);
        prob = probMultiplier;
        if(numerKasts == 1)
        {
            for(int i = 1; i <x ; i++)
            {
                prob += probMultiplier*choose(x,x-i)*Math.pow(6-i/6,i);
            }
        }
        return prob;
    }
    /**
     * Skilar vísi á pör úr teningakasti.
     * @param teningar gildi úr síðasta teningakasti.
     * @return vísi á pörin í teningakastinu. Ef það er 0 í sæti 1
     * er ekkert par annars a.m.k. 1 par. Ef það er 0 í sæti 3 eru tvö pör. 
     */
    public static int[] findPairs(int[] teningar)
    {
        int[] pairIndices = new int[4];
        int k = 0;
        for(int i = 0; i<teningar.length-1; i++)
        {
            for(int j = teningar.length-1; j>i; j--)
            {
                if(teningar[i] == teningar[j] && pairIndices[1] != j)
                {
                    pairIndices[k] = i;
                    k++;
                    pairIndices[k] = j;
                    k++;
                    break;
                } 
            }
            if(k == 4)
            {
                break;
            }
        }
        return pairIndices;
    }
    /**
     * Setur vænlegasta ástand teninga hingað til.
     * @param values heldur utan um frysta teninga auk væntanlegrar útkomu
     * miðað við að frysta þessa teninga, er af lengd 6. 
     * @param merking heldur utan um þá teninga sem á að frysta, 1 í þeim sætum
     * sem á að frysta tening annars 0
     */
    public static void merkjaTeninga(double[] values, int[] merking)
    {
        int i = 0;
        java.util.Arrays.sort(merking);
        int n = merking.length;
        for(int k = 0; k<values.length-1;k++)
        {
            if(i<n && k == merking[i])
            {
                values[k] = 1;
                i++;     
            }
            else
                values[k] = 0;
        } 
        
    }
    /**
     * Skilar vísi á teningana í stærðarröð.
     * @param teningar útkoma úr teningakasti.
     * @return vigur með vísum á teningar þannig að vísarnir eru
     * í stærðarröð miðað við teningana. Þannig á fyrsta gildið í vigrinum
     * við vísinn á stærsta gildið af teningar.
     */
    public static int[] finnaVisaIStaerdarrod(int[] teningar)
    {
        int[] visarStaerdarrod = new int[teningar.length];
        for(int i = 0; i<teningar.length;i++)
        {
            visarStaerdarrod[i] = i;
        }
        int i = 1;
        while(i<teningar.length)
        {
            int k = visarStaerdarrod[i];
            while(k!=0 && teningar[visarStaerdarrod[k]]>teningar[visarStaerdarrod[k-1]])
            {
                int tmp = visarStaerdarrod[k];
                visarStaerdarrod[k] = visarStaerdarrod[k-1];
                visarStaerdarrod[k-1] = tmp;
                k--;
            }
            i++;
        }
        return visarStaerdarrod;
    }
    /**
     * skilar vigri með vísi á teninga sem eru type margir eins.
     * @param teningar útkoma úr teningakasti.
     * @param type margir teningar eins.
     * @return vigri með vísi á teninga sem eru type margir eins.
     * Ef type-1. gildið í vigrinum er 0 er ekki til svona margir 
     * teningar eins.
     */
    public static int[] finnaVisiTypeOfAKind(int[] teningar, int type)
    {
        int[] typeOfAKindIndices = new int[type];
        int countEinsTeninga = 1;
        for(int i = 0; i < teningar.length-(type-1); i++)
        {
            typeOfAKindIndices[0] = i;
            for(int j = i+1; j<teningar.length;j++)
            {
                if(teningar[i] == teningar[j])
                {
                    typeOfAKindIndices[countEinsTeninga] = j;
                    countEinsTeninga++;     
                }  
                if(countEinsTeninga == type)
                    return typeOfAKindIndices;
            }
            countEinsTeninga = 1;
        }
        return typeOfAKindIndices;
    }
    
    /**
     * Reiknar n choose k
     * @param x er heiltala
     * @param y er heiltala
     * @return x choose y eða binomial fastanum.
     */
    public static double choose(int x, int y) {
        if (y < 0 || y > x) return 0;
        if (y > x/2) {
            // choose(n,k) == choose(n,n-k), 
            // so this could save a little effort
            y = x - y;
        }

        double denominator = 1.0, numerator = 1.0;
        for (int i = 1; i <= y; i++) {
            denominator *= i;
            numerator *= (x + 1 - i);
        }
        return numerator / denominator;
    }
   /**
    * Skilar hrópmerkt
    * @param number
    * @return number!
    */ 
   public static int factorial(int number) {
      if (number <= 1)
         return 1;
      else
         return number * factorial(number - 1);
   }
   /**
    * Tvíkostastærð
    * @param p líkur
    * @param n fjöldi tilrauna
    * @param k heppnaðar tilraunir.
    * @return líkum með tvíkostadreifingu.
    */
   public static double binomial(double p,int n,int k)
   {
       return choose(n,k)*Math.pow(p, k)*Math.pow(1-p, n-k);
   }
   


    
     
}
