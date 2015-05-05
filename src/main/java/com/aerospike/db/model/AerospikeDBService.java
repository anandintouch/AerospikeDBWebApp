package com.aerospike.db.model;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Language;
import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.cluster.Node;
import com.aerospike.client.lua.LuaConfig;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.IndexType;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.ResultSet;
import com.aerospike.client.query.Statement;
import com.aerospike.client.task.IndexTask;
import com.aerospike.client.task.RegisterTask;

/**
 * This service class creates connection to Aerospike DB and does all CRUD operations.
 * 
 * @author anand prakash
 *
 */
public class AerospikeDBService {
	private static Logger log = Logger.getLogger(AerospikeDBService.class);
	private static AerospikeClient client;
	private String seedHost;
	private int port = 3000;
	private Policy policy;
	private static WritePolicy writePolicy;
	private static String loggedinHost = null;


	public AerospikeDBService(String seedHost, int port) throws AerospikeException{
		this.policy = new Policy();
		this.writePolicy = new WritePolicy();
		this.seedHost = seedHost;
		this.port = port;
		this.client = new AerospikeClient(seedHost, port);

	}
	
	public AerospikeDBService(){
		
	}

	public Object authenticateUser(Credential credential) throws AerospikeException {
		Bin[] bins = null;
		Key key = null; 
		ScanPolicy scanPolicy = null;
		Record record = null;
		RecordSet recordSet = null;
		ResultSet resultSet = null;
		Statement stmt = null;	
		Object object =null;
		init();
					
		// print 'query on username'
		System.out.println("query on username");

		// select * from test.profile where username = 'Mary'
		stmt = new Statement();
		stmt.setNamespace("test");
		stmt.setSetName("users");
		stmt.setFilters(Filter.equal("username", Value.get(credential.username)));
		// Execute the query
		recordSet = client.query(null, stmt);

		// Process the record set
		try {
			while (recordSet != null && recordSet.next()) {
				key = recordSet.getKey();
				record = recordSet.getRecord();
				
				//System.out.println("Record: " + record);
				log.info("Record: " + record);
				
			}
		}
		finally {
			recordSet.close();
		}




		// print 'query for anand'
		System.out.println("query for user: "+ credential.username);

		// aggregate profile.check_password('ghjks') on test.profile where username = 'Anand'
		stmt = new Statement();
		stmt.setNamespace("test");
		stmt.setSetName("users");
		stmt.setFilters(Filter.equal("username", Value.get(credential.username)));
		// Below query was not working - Did a HACK , created directory UDF and added lua file
		///Users/anandprakash/Documents/Anand/eclipse/Eclipse.app/Contents/MacOS/udf
		resultSet = client.queryAggregate(null, stmt, 
			"profile", "check_password" , Value.get(credential.password));
				
		try {
			int count = 0;
			
			while (resultSet.next()) {
				 object = resultSet.getObject();
				//System.out.println("Result of authentication is: " + object);
				log.info("Result of authentication is: " + object);
				count++;
			}
			
			if (count == 0) {
				//System.out.println("No results returned.Object value is: "+ object);
				log.info("No results returned.Object value is: "+ object);
			}
		}
		finally {
			resultSet.close();
		}
		return object;

	}
	
	public void insertRecord(com.aerospike.db.model.Record recordData) throws AerospikeException{
		// insert into test.<set name> (PK, <binname>) values ('1', '<binvalue>')
		this.client.put(this.writePolicy, new Key("test", removeUnwantedChar(recordData.getSetName()), recordData.getPrimaryKey()), 
			new Bin(removeUnwantedChar(recordData.getBinName()), Value.get(removeUnwantedChar(recordData.getBinValue()))));
		//fetchRecord(recordData);
		getClusterDetails(recordData);
	}
	
	private String removeUnwantedChar(String obj){
		//return obj.replaceAll("[^a-zA-Z0-9]", "");
		return obj.replaceAll(",", "").trim();
	}
	
    public Object fetchRecord(com.aerospike.db.model.Record record) throws AerospikeException{
    	Object obj = null;
    	RecordSet rs = null;

		
		Statement stmt = new Statement();
    	stmt.setNamespace("test");
    	stmt.setSetName(removeUnwantedChar(record.getSetName())); //optional
    	//stmt.setFilters( Filter.range("age", 0,40) ); //optional
    	
    	//Execute query
    	rs = client.query(null, stmt);
    	int count = 0;
    	List<String> ls = new ArrayList<String>();
    	while (rs.next()) {
    		count++;
            Key key = rs.getKey();
            Record rc = rs.getRecord();
            ls.add(rc.bins.toString());
           /* System.out.println("----Fetch using 'Query' by providing Secondary Index----");
            System.out.println( "Record "+count+" is: "+rc.bins.toString() );*/
        }
    	System.out.println("List is: "+ls.toString());
		return ls.toString();

    }
    
	public String loadRecordForPerformanceTest(com.aerospike.db.model.Record perfTest) throws AerospikeException{
		String timeTaken = null;
    	try {
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Date date = new Date();
    		String set=  removeUnwantedChar(perfTest.getSetName());//setName.replaceAll("[^a-zA-Z0-9]", "");
    		String bin = removeUnwantedChar(perfTest.getBinName()); //binName.replaceAll("[^a-zA-Z0-9]", "");
    		Object binVal = removeUnwantedChar(String.valueOf(perfTest.getBinValue())).toString();
    		
    		System.out.println("Insert started " );
    		long startTime = System.currentTimeMillis();
			
			for(int j=0;j<Integer.valueOf(perfTest.getRecordCount());j++){

				Bin bin1 = new Bin(bin, binVal);
				Key key = new Key("test", set, Value.get(j));

				client.put(this.writePolicy, key, bin1);
			}
			System.out.println("Record inserted !! - "+dateFormat.format(new Date()));
			long endTime = System.currentTimeMillis();
			
			 timeTaken = calculateTimeTaken(new AtomicLong().addAndGet(endTime-startTime));
			// getClusterDetails(perfTest);
		} catch (AerospikeException e) {
			e.printStackTrace();
		}
		return timeTaken;
	}
	
	private String calculateTimeTaken(long elpsedTime){
		return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(elpsedTime));
	}
	
	private void getClusterDetails(com.aerospike.db.model.Record record){
		String nodeName = null;
		String hostName = null;
		Node[] nodeList = this.client.getNodes();
		List<String> nodeIps = new ArrayList<String>();
		for(Node node: nodeList){
			nodeName = node.getName();
			hostName = node.getHost().name;
			nodeIps.add(node.getHost().name);
			
		}
		//record.setHostName(hostName);
		record.setNodeName(nodeName);
		record.setHostNames(nodeIps);
		record.setSetName(removeUnwantedChar(record.getSetName()));
		record.setBinName(removeUnwantedChar(record.getBinName()));
		if(this.getLoggedinhost() != null){
			record.setLoggedInHostName(this.getLoggedinhost());
		}else{
			record.setLoggedInHostName("localhost");
		}
		
		
	}
	
    public void createAeroDBConnection(String seedHost,int port){
		
    	try {
    		/*ClientPolicy cp = new ClientPolicy();
    		cp.maxThreads =64;*/
			this.policy = new Policy();
			this.writePolicy = new WritePolicy();
			this.seedHost = seedHost;
			this.port = port;
			this.client = new AerospikeClient(seedHost, port);
			this.loggedinHost = seedHost;
			
		} catch (AerospikeException e) {
			e.printStackTrace();
		}
    }
    
	public static String getLoggedinhost() {
		return loggedinHost;
	}
	
	protected void finalize() throws Throwable {
	    if (this.client != null) {
	        this.client.close();
	        this.client = null;
	    }
	}
	
	private void init(){
		RegisterTask task =	null;
		File udfFile = null;
		IndexTask indexTask = null;
		
		LuaConfig.SourceDirectory = "udf"; 
		udfFile = new File("udf/profile.lua");
		System.out.println("file path is-"+udfFile.getPath());
		//Using servletContext
		/*URL u = context.getClass().getClassLoader().getResource("profile.lua");
		LuaConfig.SourceDirectory = u.getPath();  
		System.out.println("Context Path-"+ u.getPath());
		
		 udfFile = new File(u.getPath());*/
		//System.out.println("register udf/profile.lua");
		log.info("register udf/profile.lua");

		// REGISTER module 'udf/profile.lua'
		//udfFile = new File(u.getPath());
		

		task = this.client.register(null, 
				udfFile.getPath(), 
				udfFile.getName(), 
			Language.LUA); 
		task.waitTillComplete();

		System.out.println("udf/profile.lua  registered successfully");

		//System.out.println("create index profileindex");
		log.info("create index profileindex");

		// CREATE INDEX userindex1 ON test.users (username) STRING
		indexTask = this.client.createIndex(this.policy, "test", "users", "userindex1", "username", IndexType.STRING);
		indexTask.waitTillComplete();
	}
	
	protected void printInfo(String title, String infoString){
		String[] outerParts = infoString.split(";");
		System.out.println(title);
		for (String s : outerParts){

			String[] innerParts = s.split(":");
			for (String parts : innerParts){
				System.out.println("\t" + parts);
			}
			System.out.println();
		}
		
	}
}