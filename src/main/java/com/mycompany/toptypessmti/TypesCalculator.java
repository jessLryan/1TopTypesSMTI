
package com.mycompany.toptypessmti;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author jessica
 */
public class TypesCalculator {

    public static void main(String[] args) {
        String input = args[0];
        BufferedReader agentsBufferedReader = getReader(input);
        //the score table is stored as as a list (rows) of lists (columns)
        //of triples (row,column,value) where value is the score of this partnership
        List<ArrayList<Triple>> matrix = readToMatrix(agentsBufferedReader);
        List<Agent> men = extractMen(matrix);
        List<Agent> women = extractWomen(matrix);
        
        List<ArrayList<Agent>> partitionedMen = partitionAgents(men);
        List<ArrayList<Agent>> partitionedWomen = partitionAgents(women);
        List<Agent> menWithTop = partitionedMen.get(0);
        System.out.println("men with top ");
        List<Agent> menWithoutTop = partitionedMen.get(1);
        List<Agent> womenWithTop = partitionedWomen.get(0);
        List<Agent> womenWithoutTop = partitionedWomen.get(1);
        System.out.println("men without top "+menWithoutTop.size());
        System.out.println("men with top "+menWithTop.size());
        System.out.println("women without top "+womenWithoutTop.size());
        System.out.println("women with top "+womenWithTop.size());
        
        System.out.println("number of men " + men.size());
        System.out.println("number of women " + women.size());
        //sort each gender by pref list (so that agents with identical pref lists 
        //are grouped in the list)
        womenWithoutTop.sort((o1, o2) -> o1.compareTo(o2));
        menWithoutTop.sort((o1, o2) -> o1.compareTo(o2));
        //group each gender into lists of agents with identical preference lists
        List<ArrayList<Agent>> groupedMenWithoutTop = groupAgentsIdenticalPrefs(menWithoutTop);
        
        
//        System.out.println("number of groups of men without tops "+groupedMenWithoutTop.size());
//        for (ArrayList<Agent> type:groupedMenWithoutTop) {
//            System.out.println("group");
//            for (Agent a:type) {
//                System.out.print(a.getNumber());
//            }
//            System.out.println();
//        }
//        
        
        List<ArrayList<Agent>> groupedWomenWithoutTop = groupAgentsIdenticalPrefs(womenWithoutTop);
//        
//        
//        System.out.println("number of groups of women without tops "+groupedWomenWithoutTop.size());
//        for (ArrayList<Agent> type:groupedWomenWithoutTop) {
//            System.out.println("group");
//            for (Agent a:type) {
//                System.out.print(a.getNumber());
//            }
//            System.out.println();
//        }
//        System.out.println("finding types of men");
        //from grouped lists, find minimum number of types needed to describe the
        //instance by checking which agents in a group are considered equal
        //(are in a tie) in all members of the opposite gender's pref lists
        List<ArrayList<Agent>> typedMenWithoutTop = findTypes(groupedMenWithoutTop, women);
//        System.out.println("number of types of men without tops "+typedMenWithoutTop.size());
//        for (ArrayList<Agent> type:typedMenWithoutTop) {
//            System.out.println("type");
//            for (Agent a:type) {
//                System.out.print(a.getNumber());
//            }
//            System.out.println();
//        }
//        
        
        
//        System.out.println("finding types of women");
        List<ArrayList<Agent>> typedWomenWithoutTop = findTypes(groupedWomenWithoutTop, men);
//        
//        
//        System.out.println("number of types of women without tops "+typedWomenWithoutTop.size());
//        for (ArrayList<Agent> type:typedWomenWithoutTop) {
//            System.out.println("type");
//            for (Agent a:type) {
//                System.out.print(a.getNumber());
//            }
//            System.out.println();
//        }
        
        List<ArrayList<Agent>> menTypes = getTypes(typedMenWithoutTop, menWithTop, women);
        List<ArrayList<Agent>> womenTypes = getTypes(typedWomenWithoutTop, womenWithTop, men);
//        System.out.println("number of types of men " +menTypes.size());
//        System.out.println("number of types of women " + womenTypes.size());
//        System.out.println("types of men");
//        for (ArrayList<Agent> type : menTypes) {
//            System.out.println("type");
//            for (Agent a : type) {
//                System.out.println(a.getNumber());
//            }
//        }
//
//        System.out.println("types of women");
//        for (ArrayList<Agent> type : womenTypes) {
//            System.out.println("type");
//            for (Agent a : type) {
//                System.out.println(a.getNumber());
//            }
//        }

    }
    
    
    public static List<ArrayList<Pair<Agent,Integer>>> partitionsOfRemainingAgents(List<Agent> agents) {
        List<ArrayList<Pair<Agent,Integer>>> groups = new ArrayList<>();
        for (int i=0;i<agents.size()-1;i++) {
            Agent a = agents.get(i);
            Pair<Agent, Integer> currentPair = new Pair(a, a.getTop());

            boolean isContainedInGroup = false;
            for (ArrayList<Pair<Agent, Integer>> group : groups) {
                if (group.contains(currentPair)) {
                    isContainedInGroup = true;
                }
            }
            if (!isContainedInGroup) {
                ArrayList<Pair<Agent, Integer>> currentGroup = new ArrayList<>();
                currentGroup.add(currentPair);
                for (int j = 1; j < agents.size(); j++) {
                    Agent a2 = agents.get(j);
                    if (a.hasSamePrefs(a2, true, true)  {
                        currentGroup.add(a2, a2.getTop());
                    }
                    else if (a.hasSamePrefs(a2, true, false) {
                        currentGroup.add(new Pair(a2, -1));
                    }
                }
                groups.add(currentGroup);
            }
                
            currentPair = new Pair(a, -1);
            
            isContainedInGroup = false;
            for (ArrayList<Pair<Agent, Integer>> group : groups) {
                if (group.contains(currentPair)) {
                    isContainedInGroup = true;
                }
            }
            if (!isContainedInGroup) {
                ArrayList<Pair<Agent, Integer>> currentGroup = new ArrayList<>();
                currentGroup.add(currentPair);
                for (int j = 1; j < agents.size(); j++) {
                    Agent a2 = agents.get(j);
                    if (a.hasSamePrefs(a2, false, true)  {
                        currentGroup.add(a2, a2.getTop());
                    }
                    else if (a.hasSamePrefs(a2, false, false) {
                        currentGroup.add(new Pair(a2, -1));
                    }
                }
                groups.add(currentGroup);
            }

        }
        return groups;
    }
    
    
    public static List<ArrayList<Agent>> getTypes(List<ArrayList<Agent>> typedAgentsWithoutTop, List<Agent> agentsWithTop, List<Agent> otherGender) {
        agents: for (Agent a:agentsWithTop) {
            for (ArrayList<Agent> typeGroup:typedAgentsWithoutTop) {
                Agent b = typeGroup.get(0);
                
                if (areAgentsConsideredIdentical(a,b, otherGender)) {
                    System.out.println("?");
                    if (b.hasSamePrefs(a)) {
                        typeGroup.add(a);
                        continue agents;
                    }
                }
            }
            ArrayList<Agent> newType = new ArrayList<>();
            newType.add(a);
            typedAgentsWithoutTop.add(newType);
        }
        return typedAgentsWithoutTop;
    }
    
    public static List<ArrayList<Agent>> partitionAgents(List<Agent> agents) {
        List<ArrayList<Agent>> twoParts = new ArrayList<>();
        ArrayList<Agent> hasTop = new ArrayList<>();
        ArrayList<Agent> noTop = new ArrayList<>();
        for (Agent a:agents) {
            if (a.hasTop()) {
                hasTop.add(a);
            }
            else {
                noTop.add(a);
            }
        }
        twoParts.add(hasTop);
        twoParts.add(noTop);
        return twoParts;
        
    }
    

    
    //find minimum number of types needed to descibr the instance
    public static List<ArrayList<Agent>> findTypes(List<ArrayList<Agent>> groupedAgents, List<Agent> oppositeGenderAgents) {
        List<ArrayList<Agent>> groupedAgentsReturned = new ArrayList<>();
        //for each group, split the agents in the group into types so that agents
        //in a type are considered equal by all members of the opposite gender
        for (ArrayList<Agent> group : groupedAgents) {
//            System.out.println("agents in group");
//            for (Agent a:group) {
//                System.out.print(a.getNumber()+" ");
//            }
//            System.out.println();
            ArrayList<Agent> groupCopy = (ArrayList<Agent>) group.clone();
            ArrayList<Agent> type = new ArrayList<>();
            Agent currentAgent = groupCopy.get(0);
            type.add(currentAgent);
            groupCopy.remove(currentAgent);
            //while there are still agents in the group, find all agents in the
            //group which are considered equal to the first agent left in the
            //group, and put these agents in a type
            while (!groupCopy.isEmpty()) {
                Agent agentI = groupCopy.get(0);
                if (areAgentsConsideredIdentical(currentAgent, agentI, oppositeGenderAgents)) {
                    type.add(agentI);
                    groupCopy.remove(agentI);
                } else {
                    groupedAgentsReturned.add(type);
                    type = new ArrayList<>();
                    type.add(agentI);
                    groupCopy.remove(agentI);
                }
            }
            groupedAgentsReturned.add(type);
        }
        return groupedAgentsReturned;
    }

    //check if two agents are viewed equally (in a tie) by all agents of the 
    //opposite gender
    public static boolean areAgentsConsideredIdentical(Agent r1, Agent r2, List<Agent> agents) {
        for (Agent a : agents) {
            if (!a.areAgentsRankedIdentically(r1, r2)) {
                return false;
            }
        }
        return true;
    }

    //put agents with identical preference lists into groups. The list of agents
    //given as input is sorted by preference list
    public static List<ArrayList<Agent>> groupAgentsIdenticalPrefs(List<Agent> agents) {
        List<ArrayList<Agent>> groupedAgents = new ArrayList<>();
        ArrayList<Agent> currentList = new ArrayList<>();
        currentList.add(agents.get(0));
        Agent currentAgent = agents.get(0);
        if (agents.size()==1) {
            groupedAgents.add(currentList);
            return groupedAgents;
        }
        int pos = 1;
        while (pos < agents.size()) {
            Agent newAgent = agents.get(pos);
            if (currentAgent.compareTo(newAgent) == 0) {
                currentList.add(newAgent);
                currentAgent = newAgent;
            } else {
                groupedAgents.add(currentList);
                currentList = new ArrayList();
                currentList.add(agents.get(pos));
                currentAgent = newAgent;
            }
            pos++;
            if (pos == agents.size()) {
                if (!currentList.isEmpty()) {
                    groupedAgents.add(currentList);
                }
            }

        }
        return groupedAgents;
    }

    //each row in the score table corresponds to a woman's preference list 
    //over the men -> higher score = more preferred partner
    public static List<Agent> extractWomen(List<ArrayList<Triple>> matrix) {
        List<Agent> women = new ArrayList<>();
        for (int row = 0; row < matrix.size(); row++) {
            Agent woman = new Agent(row);
            //get row of matrix corresponding to this woman's preference list
            List<Triple> rowList = matrix.get(row);
            List<List<Integer>> prefList = new ArrayList<>();
            //sort the row of the table corresponding to this woman's preference
            //list so that most preferred men are at the front of the list.
            
            Collections.sort(rowList, new Comparator<Triple>() {
                public int compare(Triple o1,
                        Triple o2) {
                    return Double.valueOf(o2.getValue()).compareTo(o1.getValue());
                }
            });
            //Now insert the men into the woman's preference list. Men with equal
            //scores are placed in a tie in the preference list
            List<Integer> tie = new ArrayList<>();
            tie.add(rowList.get(0).getColumn());
            double currentVal = rowList.get(0).getValue();
            for (int i = 1; i < rowList.size(); i++) {
                Triple t = rowList.get(i);
                if (t.getValue() == currentVal) {
                    tie.add(t.getColumn());

                } else {
                    prefList.add(tie);
                    tie = new ArrayList<>();
                    tie.add(t.getColumn());
                }
                if (i == rowList.size() - 1) {
                    prefList.add(tie);
                }
                currentVal = rowList.get(i).getValue();

            }
            if (prefList.get(0).size()==1) {
                woman.setTop(prefList.get(0).get(0));
            }
            woman.setPrefList(prefList);
            women.add(woman);
        }
        return women;
    }
    
    //each column in the score table corresponds to a man's preference list 
    //over the women -> higher score = more preferred partner
    public static List<Agent> extractMen(List<ArrayList<Triple>> matrix) {
        List<ArrayList<Triple>> revMatrix = getColumns(matrix);
        List<Agent> men = new ArrayList<>();
        for (int row = 0; row < revMatrix.size(); row++) {
            Agent man = new Agent(row);
            List<Triple> rowList = revMatrix.get(row);
            List<List<Integer>> prefList = new ArrayList<>();
            Collections.sort(rowList, new Comparator<Triple>() {
                public int compare(Triple o1,
                        Triple o2) {
                    return Double.valueOf(o2.getValue()).compareTo(o1.getValue());
                }
            });
            List<Integer> tie = new ArrayList<>();
            tie.add(rowList.get(0).getRow());
            double currentVal = rowList.get(0).getValue();
            for (int i = 1; i < rowList.size(); i++) {
                Triple t = rowList.get(i);
                if (t.getValue() == currentVal) {
                    tie.add(t.getRow());

                } else {
                    prefList.add(tie);
                    tie = new ArrayList<>();
                    tie.add(t.getRow());
                }
                if (i == rowList.size() - 1) {
                    prefList.add(tie);
                }
                currentVal = rowList.get(i).getValue();
            }
            if (prefList.get(0).size()==1) {
                
                man.setTop(prefList.get(0).get(0));
            }
            man.setPrefList(prefList);
            men.add(man);
        }
        return men;
    }

    public static List<ArrayList<Triple>> getColumns(List<ArrayList<Triple>> matrix) {
        int rows = matrix.size();
        int columns = matrix.get(0).size();
        List<ArrayList<Triple>> newMatrix = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            ArrayList column = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                column.add(matrix.get(j).get(i));
            }
            newMatrix.add(column);
        }
        return newMatrix;
    }

    //convert text file containing table of scores into matrix
    public static List<ArrayList<Triple>> readToMatrix(BufferedReader reader) {
        List<ArrayList<Triple>> listOfLists = new ArrayList<>();
        try {
            int rows = Integer.parseInt(reader.readLine());
            int columns = Integer.parseInt(reader.readLine());
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                ArrayList<Triple> list = new ArrayList<>();
                String[] splitArray = line.trim().split("\\s+");

                for (int col = 0; col < splitArray.length; col++) {
                    double value = Double.parseDouble(splitArray[col]);
                    list.add(new Triple(row, col, value));
                }
                listOfLists.add(list);
                row++;
            }
            return listOfLists;

        } catch (IOException e) {
            System.out.print("I/O Error");
            System.exit(0);
        }
        return null;

    }

    public static BufferedReader getReader(String name) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File(name)));
        } catch (FileNotFoundException e) {
            System.out.print("File " + name + " cannot be found");
        }
        return in;
    }

}
