/*
 *  CloudASP
 *  Adapted for the cloud by Yvan JANSSENS
 *  Based on Arrowhead ASP 0.2.3
 *
 *  Licensed under the GNU GPL
 */
package org.aspcloud.engine.asp.ADODB;

import org.aspcloud.engine.asp.AspException;
import org.aspcloud.engine.asp.ByRefValue;
import java.sql.SQLException;

/**
 * This class represents the ADODB.Command object used to prepare statements
 * for execution.
 * @author Terence Haddock
 */
public class Command
{
    /** Text of the command to execute */
    public String CommandText = null;

    /** Type of the command */
    public int CommandType = 0;

    /** Internal Active connection */
    public Connection _ActiveConnection = null;

    /**
     * Execute a statements based off this command object.
     * @throws SQLException on SQL error
     */
    public RecordSet Execute() throws SQLException, AspException
    {
        return Execute(null);
    }

    /**
     * Execute a statement, returning the number of elements affected.
     * @param recordsAffected returns the number of rows affected.
     * @throws SQLException on SQL error
     */
    public RecordSet Execute(ByRefValue recordsAffected) throws SQLException,
        AspException
    {
        if (_ActiveConnection.State() == 0)
        {
            _ActiveConnection.Open();
        }
        RecordSet RS = new RecordSet();
        int ra = RS.Open(this);
        if ((recordsAffected != null) && (ra != -1))
        {
            Integer res = new Integer(ra);
            recordsAffected.setValue(res);
        }
        return RS;
    }

    /**
     * Obtain the active connection.
     * @return Active connection
     */
    public Connection ActiveConnection()
    {
        return _ActiveConnection;
    }

    /**
     * Set the active connection to a connection object.
     * @param ActiveConnection Active connection object.
     */
    public void ActiveConnection(Connection con)
    {
        _ActiveConnection = con;
    }

    /**
     * Sets the active connection to a string object.
     * @param DSN Active Connection DSN
     */
    public void ActiveConnection(String DSN)
    {
        _ActiveConnection = new Connection();
        _ActiveConnection.ConnectionString = DSN;
    }
}

