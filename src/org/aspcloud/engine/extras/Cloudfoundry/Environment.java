package org.aspcloud.engine.extras.Cloudfoundry;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.log4j.Category;
import org.aspcloud.engine.asp.AspException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

//import org.apache.log4j.Category;

public class Environment {

    // Debugging Context
    static Category DBG = Category.getInstance(Environment.class);

    public String EnvironmentVariable(String name) {
        String variable = System.getenv(name);
        if (variable == null)
            return  "";
        else
            return variable;
    }

    public DatabaseData StandardDatabaseData() throws AspException {
    	try {
    		DatabaseData dbdat = new DatabaseData();
	    	JSONObject databaseSettings = (JSONObject) JSONSerializer.toJSON(this.EnvironmentVariable("VCAP_SERVICES"));
	    	JSONArray mysql = databaseSettings.getJSONArray ("mysql-5.1");
	    	JSONObject creds = ((JSONObject)mysql.get(0)).getJSONObject("credentials");
	    	dbdat.ConnectionString = String.format("jdbc:mysql://%s:%s/%s", creds.getString("hostname"), creds.getString("port"), creds.getString("name"));
	    	dbdat.Username = creds.getString("user");
	    	dbdat.Password = creds.getString("password");
	    	return dbdat;
    	} catch (Exception e) {
    		throw new AspException("Could not parse JSON in VCAP_SERVICES environment. Make sure you hava added a MySQL database to your instance.");
    	}
    }
    
    public DatabaseData DatabaseDataForService(String serviceName) throws AspException {
    	
    	try {
    		DatabaseData dbdat = new DatabaseData();
	    	JSONObject databaseSettings = (JSONObject) JSONSerializer.toJSON(this.EnvironmentVariable("VCAP_SERVICES"));
	    	JSONArray mysql = databaseSettings.getJSONArray ("mysql-5.1");
	    	JSONObject creds = null;
	    	Iterator itr = mysql.iterator();
	    	while (itr.hasNext()) {
	    		JSONObject element = ((JSONObject)itr.next());
	    		if (element.getString("name").toLowerCase().equals(serviceName.toLowerCase()))
	    		{
	    			creds = element.getJSONObject("credentials");
	    			break;
	    		}
	    	}
	    	
	    	if (creds == null) {
	    		throw new NoSuchElementException(serviceName);
	    	}
	    	
	    	dbdat.ConnectionString = String.format("jdbc:mysql://%s:%s/%s", creds.getString("hostname"), creds.getString("port"), creds.getString("name"));
	    	dbdat.Username = creds.getString("user");
	    	dbdat.Password = creds.getString("password");
	    	return dbdat;
	    	
    	} catch (NoSuchElementException e) {
    		throw new AspException(String.format("The service \"%s\" could not be found.", serviceName));
    	} catch (Exception e) {
    		throw new AspException("Could not parse JSON in VCAP_SERVICES environment. Make sure you hava added a MySQL database to your instance.");
    	}
    }

}
