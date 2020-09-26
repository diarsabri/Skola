
import java.util.ArrayList;
import java.util.LinkedList;

public class RoleSettingProblem {
    int roller;
    int scener;
    int actors;
    Kattio kattio;

    ArrayList<LinkedList<Integer>> scenerList;
    ArrayList<LinkedList<Integer>> skådisRoller;
    ArrayList<LinkedList<Integer>> rollerAktivaSkådisar;
    ArrayList<LinkedList<Integer>> output;
    ArrayList<LinkedList<Integer>> rollerList;
    int antaletSkådisar = 0;
    int antaletRoller = 0;
    int superActors;
    boolean[][] ÄrDessaISammaScen;
    boolean[] användSkådis;
    boolean[] användaRoller;

    public void readInput() {
        roller = kattio.getInt();
        scener = kattio.getInt();
        actors = kattio.getInt();
        //scener
        scenerList = new ArrayList<LinkedList<Integer>>(scener + 1); 
        //Vilka roller kan spela i vilken scen
        skådisRoller = new ArrayList<LinkedList<Integer>>(roller + 1); 
        //Vilka skådespelar kan spela vilken roll
        rollerAktivaSkådisar = new ArrayList<LinkedList<Integer>>(actors + 1); 
        //vår output
        output = new ArrayList<LinkedList<Integer>>(roller + actors + 1); 
        //roller
        rollerList = new ArrayList<LinkedList<Integer>>(roller + 1); 

        ÄrDessaISammaScen = new boolean[roller + 1][roller + 1];
        användaRoller = new boolean[roller + 1];
        användSkådis = new boolean[actors + 1];
        superActors = actors + 1;
        //läser in datan 
        for (int i = 0; i <= roller; i++) {
            rollerList.add(new LinkedList<Integer>());
            skådisRoller.add(new LinkedList<Integer>());

        }

        for (int i = 0; i <= actors + roller; i++) {
            output.add(new LinkedList<Integer>());
            if (i <= actors) {
                rollerAktivaSkådisar.add(new LinkedList<Integer>());
            }
        }

        for (int i = 0; i <= scener; i++) {
            scenerList.add(new LinkedList<Integer>());
        }

        // input roles
        for (int i = 1; i <= roller; i++) {
            int nIncomingValues = kattio.getInt();
            for (int j = 0; j < nIncomingValues; j++) {
                int to = kattio.getInt();
                rollerList.get(i).add(to);
                rollerAktivaSkådisar.get(to).add(i);
            }
        }

        // input scenes
        for (int i = 1; i <= scener; i++) {
            int nIncomingValues = kattio.getInt();
            int[] rollerIscen = new int[nIncomingValues];
            for (int j = 0; j < nIncomingValues; j++) {
                int to = kattio.getInt();
                scenerList.get(i).add(to);
                skådisRoller.get(to).add(i);
                rollerIscen[j] = to;
            }
            for (int x = 0; x < nIncomingValues; x++) {
                for (int y = 0; y < nIncomingValues; y++) {
                    int a = rollerIscen[x];
                    int b = rollerIscen[y];
                    ÄrDessaISammaScen[a][b] = true;
                }
            }

        }

        kattio.flush();



    }

    // Fixar divorna
    public void besättDivor() {
        LinkedList divas1 = rollerAktivaSkådisar.get(1);
        LinkedList divas2 = rollerAktivaSkådisar.get(2);

        boolean klar = false;

        for (int i = 0; i < divas1.size() && !klar; i++) {
            int diva1 = (int) divas1.get(i);
            for (int j = 0; j < divas2.size() && !klar; j++) {
                int diva2 = (int) divas2.get(j);
                if (!ÄrDessaISammaScen[diva1][diva2]) {
                    addActor(1, diva1);
                    addActor(2, diva2);
                    klar = true;
                }
            }
        }
    }

    //Boolesk funktion som kollar ifall en actor redan spelar i en scen. 
    public boolean SpelarIscenen(int actor, int roll) {
        LinkedList nuvarandeRoller = output.get(actor);
        int längd = nuvarandeRoller.size();
        if (längd == 0) {
            return false;
        }
        for (int i = 0; i < längd; i++) {
            int temp = (int) nuvarandeRoller.get(i);
            if (ÄrDessaISammaScen[temp][roll]) {
                return true;
            }
        }
        return false;
    }
    //besätter restarande 
    public void besättResterande() {
        for (int i = 3; i < rollerAktivaSkådisar.size(); i++) {
            for (int j = 0; j < rollerAktivaSkådisar.get(i).size(); j++) {
                int rollish = rollerAktivaSkådisar.get(i).get(j);
                if (!SpelarIscenen(i, rollish) && !användaRoller[rollish]) {
                    addActor(i, rollish);
                }

            }
        }

    }

    // Lägg till roller
    public void addActor(int actor, int roll) {
        if (användaRoller[roll] == false) {
            output.get(actor).add(roll);
            användaRoller[roll] = true;
            antaletRoller++;
            if (användSkådis[actor] == false) {
                antaletSkådisar++;
                användSkådis[actor] = true;
            }
        }
    }

    public void printOutData() {

        int res = antaletSkådisar + roller - antaletRoller;
        kattio.println(res);

        
        StringBuilder out = new StringBuilder();
        for (int i = 1; i < output.size(); i++) {
            if (!output.get(i).isEmpty()) {
                out.append(i + " " + output.get(i).size() + " ");
                for (int j = 0; j < output.get(i).size(); j++) {
                    out.append(output.get(i).get(j) + " ");
                }
                kattio.println(out);
                out.setLength(0);
            }
        }
        out.setLength(0);
        for (int i = 1; i < användaRoller.length; i++) {
            //om en roll inte är tillsat, dvs FALSE så lägger vi in en superactor. 
            if (användaRoller[i] == false) {
                out.append(superActors + " 1 " + i + "\n");
                superActors++;
            }
        }
     
        kattio.println(out);
        kattio.flush();
    }

    public RoleSettingProblem() {
        try {
            kattio = new Kattio(System.in, System.out);

        } catch (Exception e) {

        }
        readInput();
        besättDivor();
        besättResterande();
        printOutData();
        kattio.close();
    }

    public static void main(String[] args) {
        RoleSettingProblem rs = new RoleSettingProblem();
    }
}
