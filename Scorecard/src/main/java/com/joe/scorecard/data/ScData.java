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
package com.joe.scorecard.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScData {
    JdbcConnectionSource globalCon;
    JdbcConnectionSource gameCon;
    Dao<Team,Long> globalTeamDao;    
    Dao<Team,Long> gameTeamDao;    
    Dao<Player,Long> globalPlayerDao;    
    Dao<Player,Long> gamePlayerDao;    
        
    public void openGlobalData(String path) {
        try {
            globalCon = new JdbcConnectionSource("jdbc:sqlite:" + path);
            globalTeamDao = DaoManager.createDao(globalCon,Team.class);
            TableUtils.createTableIfNotExists(globalCon,Team.class);            
            globalPlayerDao = DaoManager.createDao(globalCon,Player.class);
            TableUtils.createTableIfNotExists(globalCon,Player.class);            
            
        } catch (Exception ex) {
            Logger.getLogger(ScData.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void closeGlobalData() {
        try {
            globalCon.close();
        } catch (Exception ex) {
            Logger.getLogger(ScData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openGameData(String path) {
        try {
            gameCon = new JdbcConnectionSource("jdbc:sqlite:" + path);
            gameTeamDao = DaoManager.createDao(gameCon,Team.class);
            TableUtils.createTableIfNotExists(gameCon,Team.class);            
            gamePlayerDao = DaoManager.createDao(gameCon,Player.class);
            TableUtils.createTableIfNotExists(gameCon,Player.class);                        
        } catch (Exception ex) {
            Logger.getLogger(ScData.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public Dao<Team, Long> getGlobalTeamDao() {
        return globalTeamDao;
    }        

    public Dao<Team, Long> getGameTeamDao() {
        return gameTeamDao;
    }        
}
