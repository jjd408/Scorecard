/*
 * The MIT License
 *
 * Copyright 2024 joe.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.joe.scorecard.view.models;

import com.joe.scorecard.Scorecard;
import com.joe.scorecard.data.Team;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author joe
 */
public class TeamsTableModel implements TableModel {
    private ArrayList<Team> teams = new ArrayList<>();

    public TeamsTableModel() {
        try {
            teams = (ArrayList<Team>) Scorecard.getData().getGlobalTeamDao().queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(TeamsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int getRowCount() {
        return teams.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int i) {
        if(i == 0)return "Id";
        if(i == 1)return "Name";
        if(i == 2)return "City";
        return null;
    }

    @Override
    public Class<?> getColumnClass(int i) {
        if(i == 0)return Long.class;
        if(i == 1)return String.class;
        if(i == 2)return String.class;
        return null;        
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        if(i >= 0 && i < teams.size()) {
            Team t = teams.get(i);
            if(i1 == 0)return t.getId();
            if(i1 == 1)return t.getName();
            if(i1 == 2)return t.getCity();            
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        if(i >= 0 && i < teams.size()) {
            Team t = teams.get(i);
            if(i1 == 0)t.setId((Long) o);
            if(i1 == 1)t.setName((String) o);
            if(i1 == 2)t.setCity((String) o);            
        }        
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
    }    
    
    public Team getTeamAt(Integer index) {
        if(index >= 0 && index < teams.size()) {
            return teams.get(index);
        }
        return null;
    }
}
