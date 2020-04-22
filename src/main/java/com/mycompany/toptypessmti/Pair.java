/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.toptypessmti;

/**
 *
 * @author jessica
 */

public class Pair<Agent, Integer> {
    private final Agent agent;
    private int top = -1;

    public Pair(Agent a, int t) {
        this.top = t;
        this.agent = a;
    }


    public Agent getAgent() {
        return agent;
    }

    public int getTop() {
        return top;
    }
    
    public void setTop(int t) {
        top = t;
    }
    
    public boolean hasTop() {
        if (top>=0) {
            return true;
        }
        return false;
    }
 
    
    public boolean equals(Pair<Agent,Integer> p2) {
        if (agent.equals(p2.getAgent())&&top==p2.getTop()) {
            return true;
        }
        return false;
    }

}

