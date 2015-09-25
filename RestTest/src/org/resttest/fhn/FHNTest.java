package org.resttest.fhn;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.UnsupportedQueryLanguageException;
import org.openrdf.query.Update;
import org.openrdf.query.UpdateExecutionException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;
import org.openrdf.repository.manager.RemoteRepositoryManager;
import org.openrdf.repository.manager.RepositoryInfo;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.repository.sparql.SPARQLRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.SailException;
import org.openrdf.sail.inferencer.fc.CustomGraphQueryInferencer;
import org.openrdf.sail.memory.MemoryStore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/fhn")
public class FHNTest {
	
	
		private static String FHNRDF = "PREFIX FHNRDF: <http://www.semanticweb.org/porco/ontologies/2015/2/FHNRDF#> ";
		private static String FHN = "	PREFIX FHN: <htttp://www.shawndexter.com/semantics/FHNOntology#>";
			private static String owl = "	PREFIX owl: <http://www.w3.org/2002/07/owl#>";
			private static String or	= "PREFIX or: <http://www.ontologydesignpatterns.org/cp/owl/objectrole.owl#>";

			private String queryConstruct = "PREFIX FHNRDF: <http://www.semanticweb.org/porco/ontologies/2015/2/FHNRDF#>\r\n" + 
					"PREFIX FHN: <htttp://www.shawndexter.com/semantics/FHNOntology#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX or: <http://www.ontologydesignpatterns.org/cp/owl/objectrole.owl#>\r\n" + 
					"PREFIX ar: <http://www.ontologydesignpatterns.org/cp/owl/agentrole.owl#>\r\n" + 
					"\r\n" + 
					"CONSTRUCT { ?x FHN:agentIsSatisfiedBy ?y }\r\n" + 
					"WHERE {\r\n" + 
					"  ?x or:hasRole ?b.\r\n" + 
					"  ?d FHN:isANeedFor ?b.\r\n" + 
					"  ?a FHN:isASatisfierFor ?y.      \r\n" + 
					"}";
			
			
			private String queryConstruct2 = "PREFIX FHNRDF: <http://www.semanticweb.org/porco/ontologies/2015/2/FHNRDF#>\r\n" + 
					"PREFIX FHN: <htttp://www.shawndexter.com/semantics/FHNOntology#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX or: <http://www.ontologydesignpatterns.org/cp/owl/objectrole.owl#>\r\n" + 
					"PREFIX ar: <http://www.ontologydesignpatterns.org/cp/owl/agentrole.owl#>\r\n" + 
					"\r\n" + 
					"CONSTRUCT { ?x FHN:agentIsSatisfiedBy ?y }\r\n" + 
					"WHERE {\r\n" + 
					"  ?x or:hasRole ?b.\r\n" + 
					"  ?b FHN:roleHasNeed ?a.\r\n" + 
					"  ?a FHN:isSatisfiedBy ?y.      \r\n" + 
					"}";
			
			private String dbPediaQuery2 = "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
					"PREFIX android: <http://dbpedia.org/resources/Category:Android_(operating_system)_software> \r\n" + 
					"SELECT ?f \r\n" + 
					"WHERE { \r\n" + 
					"?f  dbpedia2:operatingSystem <http://dbpedia.org/resource/Android_(operating_system)> . \r\n" + 
					"?f  dbpedia-owl:genre <http://dbpedia.org/resource/Physical_fitness>\r\n" + 
					"}";
			
			private String dbPediaQueryPrefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
					"PREFIX dc: <http://purl.org/dc/elements/1.1/>\r\n" + 
					"PREFIX : <http://dbpedia.org/resource/>\r\n" + 
					"PREFIX dbpedia2: <http://dbpedia.org/property/>\r\n" + 
					"PREFIX dbpedia: <http://dbpedia.org/>\r\n" + 
					"PREFIX skos: <http://www.w3.org/2004/02/skos/core#> ";
			
			private String dbPediaConferenceQuery = "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
										"SELECT ?f \r\n" + 
					"WHERE {\r\n" + 
					"?f rdf:type yago:TheoreticalComputerScienceConferences\r\n" + 
					"}";
			
			private ArrayList<String> dbPdiaList = new ArrayList<String>();
		


  // This method is called if TEXT_PLAIN is request
 
			/*
	
			
			
  
			*/
			
/*
  // This method is called if XML is request
  @GET
  @Produces(MediaType.TEXT_XML)
  public String sayXMLHello() throws MalformedQueryException, UnsupportedQueryLanguageException, SailException, RepositoryException, RDFParseException, QueryEvaluationException, IOException {
   
	  String output = queryOntology(output);
	  return output;
	   //return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() throws MalformedQueryException, UnsupportedQueryLanguageException, SailException, RepositoryException, RDFParseException, QueryEvaluationException, IOException {
    
	  String output = queryOntology(output);
	  return output;
	  //return "<html> " + "<title>" + "Hello Jersey" + "</title>"
      //  + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }
	*/
  
	public String queryOntology(String nameOfIndividual) throws MalformedQueryException, UnsupportedQueryLanguageException, SailException, RepositoryException, RDFParseException, IOException, QueryEvaluationException
	{
		
			 Repository repo = null;
			 repo = new SailRepository(new CustomGraphQueryInferencer(new MemoryStore(), QueryLanguage.SPARQL, queryConstruct, ""));
			 repo.initialize();
			 RepositoryConnection con = repo.getConnection();
			 URL url = new URL("http://www.shawndexter.com/TheFHNOntology.owl");
			 con.add(url, url.toString(), RDFFormat.RDFXML);
			 URL url2 = new URL("http://www.shawndexter.com/FHNRDF.owl");
			 con.add(url2, url2.toString(), RDFFormat.RDFXML);
			 con.close();
			 String output = queryRepo(repo, nameOfIndividual);
		
			
		return output;
	
	}



	
	private String queryRepo(Repository repo, String nameOfIndividual) throws QueryEvaluationException, RepositoryException, MalformedQueryException {
		 String queryResult = null;
		 String need = "";
		 String satisfier = "";
		 RepositoryConnection con = repo.getConnection(); 
		
		 queryResult = evaluateGraphQuery(repo, con, nameOfIndividual);
		 //queryResult = evaluateTupleQuery(repo, con);
			   
			   if (queryResult!= null) {
				   return queryResult;
			   }
			   
			return "Failed Query or Connection";
		
	}
	
	private String updateRepo(Repository repo, String insertQuery) throws QueryEvaluationException, RepositoryException, MalformedQueryException {
		
		 RepositoryConnection con = repo.getConnection(); 
		 Update insert = con.prepareUpdate(QueryLanguage.SPARQL, insertQuery);
		 try {
			insert.execute();
			return "/n Insert successful";
		} catch (UpdateExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			   
			return "Failed Query or Connection";
		
	}
	
	
	private String evaluateTupleQuery(Repository repo, RepositoryConnection con, String queryString) throws QueryEvaluationException, RepositoryException, MalformedQueryException {
		 String queryResult = null;
		 String need = "";
		 String satisfier = "";
		

			
			  TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

			  TupleQueryResult result = tupleQuery.evaluate();
			
			  String testString = result.toString();
			   System.out.println(result.toString()); 
			   try {
		              while (result.hasNext()) {  // iterate over the result
					BindingSet bindingSet = result.next();
					Value valueOfF = bindingSet.getValue("f");
					
					this.dbPdiaList.add(valueOfF.toString());
					queryResult = queryResult + valueOfF;
		              }
			  }
			   finally {
				      result.close();
				  }
	
	if(queryResult != null)
	{
	return queryResult;
	}
	else
	{
		return "Error evaluating graph query";
	}
	
	}

	private String evaluateGraphQuery(Repository repo, RepositoryConnection con, String nameOfIndividual) throws QueryEvaluationException, RepositoryException, MalformedQueryException {
		 String queryResult = "";
		FHNQuery query = new FHNQuery(nameOfIndividual);
		String graphQuery = query.createQuery();

		 GraphQueryResult graphResult = con.prepareGraphQuery(QueryLanguage.SPARQL, graphQuery).evaluate();
       while (graphResult.hasNext()) {  // iterate over the result
	     Statement st = graphResult.next();		
		queryResult = queryResult + st.getSubject().toString().split("#")[1] + " is satisfied by:" 
				 + st.getObject().toString().split("#")[1] + "<br>";
		              }
	
	if(queryResult != null)
	{
	return queryResult;
	}
	else
	{
		return "Error evaluating graph query";
	}

}
	
	
	
	
//Method to query Sesame Repo with only Name	
public String querySesameRepository(String nameOfIndividual) throws RepositoryException, QueryEvaluationException, MalformedQueryException{
		
	String serverUrl = "http://localhost:8080/openrdf-sesame";
	RemoteRepositoryManager manager = new RemoteRepositoryManager(serverUrl);
	manager.initialize();
	RepositoryInfo repInfo = manager.getRepositoryInfo("testRepID");
	Repository repo = new HTTPRepository(serverUrl, "testRepID");
	repo.initialize();
	String output = queryRepo(repo, nameOfIndividual);	
	return output;
		
	}

//Method to update Sesame repo with User Sign Info
public String querySesameRepository(String nameOfIndividual, String insertQuery) throws RepositoryException, QueryEvaluationException, MalformedQueryException{
	String serverUrl = "http://localhost:8080/openrdf-sesame";
	RemoteRepositoryManager manager = new RemoteRepositoryManager(serverUrl);
	manager.initialize();
	RepositoryInfo repInfo = manager.getRepositoryInfo("testRepID");
	Repository repo = new HTTPRepository(serverUrl, "testRepID");
	repo.initialize();
	String output = updateRepo(repo,  insertQuery);	
	return output;
		
	}
	
	
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "Hello Jersey";
	  }
	  
	  	  
	  @Path("/convert/{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("f") float f) throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius =  (f - 32)*5/9; 
		jsonObject.put("id", f); 
		jsonObject.put("content", celsius);
	//	String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		String result =  jsonObject.toString();
		return Response.status(200).entity(result).build();
	  }
  
@Path("{nameOfIndividual}")
@GET
@Produces(MediaType.TEXT_PLAIN)
public String sayPlainTextHello( @PathParam("nameOfIndividual") String nameOfIndividual) throws MalformedQueryException, UnsupportedQueryLanguageException, SailException, RepositoryException, RDFParseException, QueryEvaluationException, IOException  {
  
	 
	 // return nameOfIndividual;
	//String output = queryOntology(nameOfIndividual);
	String output = querySesameRepository(nameOfIndividual);
	return output + "asdasdasdtest";
}


@Path("/create/{nameOfIndividual}/{role}/{birthday}/{relationship}/{gender}/{email}")
@GET
@Produces(MediaType.TEXT_PLAIN)
public String createFHNQuery( @PathParam("nameOfIndividual") String nameOfIndividual, @PathParam("role") String role, @PathParam("birthday") String birthday, @PathParam("relationship") String relationship, @PathParam("gender") String gender, @PathParam("email") String email) throws MalformedQueryException, UnsupportedQueryLanguageException, SailException, RepositoryException, RDFParseException, QueryEvaluationException, IOException  {
  
	 String test = "shawn";
	 // return nameOfIndividual;
	//String output = queryOntology(nameOfIndividual);
	//String output = querySesameRepository(nameOfIndividual);
	String userProfile = nameOfIndividual + " " +  role + " " + birthday + " " + gender + " " + relationship; 
	FHNQuery createQuery = new FHNQuery(nameOfIndividual, role, gender, relationship, email, birthday) ;
	String insertQuery = createQuery.insertNewIndividual();
	String getSesame = querySesameRepository(nameOfIndividual, insertQuery);
return insertQuery + "/n" + getSesame;
}


/*
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response createUser(User user) throws RepositoryException, QueryEvaluationException, MalformedQueryException {
    System.out.println("HELLO WORLD");
    FHNQuery createQuery = new FHNQuery(user);
    String insertQuery = createQuery.insertNewIndividual();
	String getSesame = querySesameRepository(user.getName(), insertQuery);
    return Response.status(666).entity("Hello world").build();
}
*/


@POST 
@Path("/createUser/{nameOfIndividual}/{role}/{birthday}/{relationship}/{gender}/{email}")
@Consumes(MediaType.APPLICATION_JSON)
public Response createUser(@PathParam("nameOfIndividual") String nameOfIndividual, @PathParam("role") String role, @PathParam("birthday") String birthday, @PathParam("relationship") String relationship, @PathParam("gender") String gender, @PathParam("email") String email) throws RepositoryException, QueryEvaluationException, MalformedQueryException{

 
    String userProfile = nameOfIndividual + " " +  role + " " + birthday + " " + gender + " " + relationship; 
	FHNQuery createQuery = new FHNQuery(nameOfIndividual, role, gender, relationship, email, birthday) ;
	String insertQuery = createQuery.insertNewIndividual();
	String getSesame = querySesameRepository(nameOfIndividual, insertQuery);
	this.queryDbPedia();

	
	
	return Response.status(666).entity(getSesame).build();
	
}



public void queryDbPedia() throws RepositoryException, QueryEvaluationException, MalformedQueryException{
	
	SPARQLRepository repo = new SPARQLRepository("http://dbpedia.org/sparql");
	repo.initialize();
	final RepositoryConnection conn = repo.getConnection();
	String query= this.dbPediaQueryPrefix+dbPediaConferenceQuery;
	String testOutput = evaluateTupleQuery(repo, conn, query);
	System.out.println(testOutput);
	System.out.println(dbPdiaList.toString());
	
}

@GET
@Path("/getList")
@Produces(MediaType.APPLICATION_JSON)
public SatisfierList getSatListInJSON() throws RepositoryException, QueryEvaluationException, MalformedQueryException {

	queryDbPedia();
	SatisfierList satList = new SatisfierList();
	satList.setSatList(this.dbPdiaList);
	satList.setAgentName("ShawnDexter");
	return satList;

}



}
	