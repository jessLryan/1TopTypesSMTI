package com.mycompany.toptypessmti;

import java.util.Comparator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jessica
 */
public class Agent implements Comparable<Agent> {

    //agent number is arbitrary (assigned according to row (for women) or 
    //column (for men) in the score table
    private int number;
    private List<List<Integer>> prefList;
    private int top = -1;
    boolean hasTop = false;

    public Agent(int no) {
        number = no;
    }

    public int getNumber() {
        return number;
    }

    //each list in the preference list contains agents in a tie
    public void setPrefList(List<List<Integer>> list) {
        prefList = list;
    }

    public List<List<Integer>> getPrefList() {
        return prefList;
    }

    public void setTop(int t) {
        top = t;
    }

    public int getTop() {
        return top;
    }
    
    public void setHasTop(boolean b) {
        hasTop = b;
    }

    public boolean hasTop() {
        return hasTop;
    }

    public boolean hasSamePrefs(Agent b) {
        System.out.println("compare pref lists of agents "+this.getNumber()+b.getNumber());
        List<List<Integer>> prefListCopy = prefList;
        List<List<Integer>> bWithoutATop = b.getPrefList();
        if (hasTop()) {
            for (List<Integer> tie : prefListCopy) {
                if (tie.contains(Integer.valueOf(top))) {
                    tie.remove(Integer.valueOf(top));
                }
            }
            for (List<Integer> tie : b.getPrefList()) {
                if (tie.contains(Integer.valueOf(top))) {
                    tie.remove(Integer.valueOf(top));
                }
            }
            if (b.hasTop()) {

                if (compareLists(prefListCopy, bWithoutATop)) {
                    b.setTop(-1);
                    return true;
                }

                for (List<Integer> tie : prefListCopy) {
                    if (tie.contains(Integer.valueOf(b.getTop()))) {
                        tie.remove(Integer.valueOf(b.getTop()));
                    }
                }
                for (List<Integer> tie : b.getPrefList()) {
                    if (tie.contains(Integer.valueOf(b.getTop()))) {
                        tie.remove(Integer.valueOf(b.getTop()));
                    }
                }

                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }

            } else {
                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }
            }
        } else {
            if (b.hasTop()) {

                if (compareLists(prefListCopy, bWithoutATop)) {
                    b.setTop(-1);
                    return true;
                }
                for (List<Integer> tie : prefListCopy) {
                    if (tie.contains(Integer.valueOf(b.getTop()))) {
                        tie.remove(Integer.valueOf(b.getTop()));
                    }
                }
                for (List<Integer> tie : b.getPrefList()) {
                    if (tie.contains(Integer.valueOf(b.getTop()))) {
                        tie.remove(Integer.valueOf(b.getTop()));
                    }
                }

                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }

            } else {
                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }
            }
        }

        return false;

    }
    
    
    
    
    
    
    
    public boolean hasSamePrefs(Agent a2, boolean agentOneHasTop,boolean agentTwoHasTop) {
        List<List<Integer>> prefListCopy = prefList;
        List<List<Integer>> bWithoutATop = a2.getPrefList();
        if (agentOneHasTop) {
            for (List<Integer> tie : prefListCopy) {
                if (tie.contains(Integer.valueOf(top))) {
                    tie.remove(Integer.valueOf(top));
                }
            }
            for (List<Integer> tie : a2.getPrefList()) {
                if (tie.contains(Integer.valueOf(top))) {
                    tie.remove(Integer.valueOf(top));
                }
            }
            if (agentTwoHasTop) {

                if (compareLists(prefListCopy, bWithoutATop)) {
                    a2.setTop(-1);
                    return true;
                }

                for (List<Integer> tie : prefListCopy) {
                    if (tie.contains(Integer.valueOf(a2.getTop()))) {
                        tie.remove(Integer.valueOf(a2.getTop()));
                    }
                }
                for (List<Integer> tie : a2.getPrefList()) {
                    if (tie.contains(Integer.valueOf(a2.getTop()))) {
                        tie.remove(Integer.valueOf(a2.getTop()));
                    }
                }

                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }

            } else {
                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }
            }
        } else {
            if (a2.hasTop()) {

                if (compareLists(prefListCopy, bWithoutATop)) {
                    a2.setTop(-1);
                    return true;
                }
                for (List<Integer> tie : prefListCopy) {
                    if (tie.contains(Integer.valueOf(a2.getTop()))) {
                        tie.remove(Integer.valueOf(a2.getTop()));
                    }
                }
                for (List<Integer> tie : a2.getPrefList()) {
                    if (tie.contains(Integer.valueOf(a2.getTop()))) {
                        tie.remove(Integer.valueOf(a2.getTop()));
                    }
                }

                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }

            } else {
                if (compareLists(prefListCopy, bWithoutATop)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    
    
    
    
    
    
    
    
    

    public boolean compareLists(List<List<Integer>> prefList1, List<List<Integer>> prefList2) {
        
        int prefListLength = prefList.size();
        int h2prefListLength = prefList2.size();
        iLoop:
        for (int i = 0;; i++) {
            if (i == prefListLength && h2prefListLength > i) {
                return false;
            }
            if (i == h2prefListLength && prefListLength > i) {
                return false;
            }
            if (i == h2prefListLength && i == prefListLength) {
                return true;
            }
            for (int p = 0;; p++) {
                prefList.get(i).sort(Comparator.comparing(Integer::valueOf));
                prefList2.get(i).sort(Comparator.comparing(Integer::valueOf));
                if (p == prefList.get(i).size() && prefList2.get(i).size() > p) {
                    return false;
                }
                if (p == prefList2.get(i).size() && prefList.get(i).size() > p) {
                    return false;
                }
                if (p == prefList2.get(i).size() && p == prefList.get(i).size()) {
                    continue iLoop;
                }
                if (prefList.get(i).get(p) > prefList2.get(i).get(p)) {
                    return false;
                }
                if (prefList2.get(i).get(p) > prefList.get(i).get(p)) {
                    return false;
                }
            }

        }
    }

    //agents are compared by preference list - agents who prefer lower-numbered
    //partners are placed higher in the list (we want to be able to find agents
    //with identical preference lists)
    @Override
    public int compareTo(Agent a2) {
        List<List<Integer>> prefList2 = a2.getPrefList();
        int prefListLength = prefList.size();
        int h2prefListLength = prefList2.size();
        iLoop:
        for (int i = 0;; i++) {
            if (i == prefListLength && h2prefListLength > i) {
                return -1;
            }
            if (i == h2prefListLength && prefListLength > i) {
                return 1;
            }
            if (i == h2prefListLength && i == prefListLength) {
                return 0;
            }
            for (int p = 0;; p++) {
                prefList.get(i).sort(Comparator.comparing(Integer::valueOf));
                prefList2.get(i).sort(Comparator.comparing(Integer::valueOf));
                if (p == prefList.get(i).size() && prefList2.get(i).size() > p) {
                    return 1;
                }
                if (p == prefList2.get(i).size() && prefList.get(i).size() > p) {
                    return -1;
                }
                if (p == prefList2.get(i).size() && p == prefList.get(i).size()) {
                    continue iLoop;
                }
                if (prefList.get(i).get(p) > prefList2.get(i).get(p)) {
                    return 1;
                }
                if (prefList2.get(i).get(p) > prefList.get(i).get(p)) {
                    return -1;
                }
            }

        }

    }

    //checks whether two agents of the opposite gender are considered equal
    //partners to this agent (i.e. in a tie)
    public boolean areAgentsRankedIdentically(Agent r1, Agent r2) {
        for (List<Integer> tie : prefList) {
            int posAgent1 = tie.indexOf(r1.getNumber());
            int posAgent2 = tie.indexOf(r2.getNumber());
            if (posAgent1 >= 0 && posAgent2 >= 0) {
                return true;
            }
        }
        return false;
    }
}
