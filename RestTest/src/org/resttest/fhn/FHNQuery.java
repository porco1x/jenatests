package org.resttest.fhn;

public class FHNQuery {
	
	private String nameOfIndividual = null;
	private String roleOfIndividual = null;
	private String genderOfIndividual = null;
	private String relationshipStatusOfIndividual = null;
	private String emailOfIndividual = null;
	private String birthdayOfIndividual = null;
	public String query;
	public String insertQuery;
	

	public FHNQuery(String name){
		
		this.nameOfIndividual = name;
	}
	
	
	public FHNQuery(String nameOfIndividual, String roleOfIndividual,
			String genderOfIndividual, String relationshipStatusOfIndividual,
			String emailOfIndividual, String birthdayOfIndividual) {
	
		super();
		this.nameOfIndividual = nameOfIndividual;
		this.roleOfIndividual = roleOfIndividual;
		this.genderOfIndividual = genderOfIndividual;
		this.relationshipStatusOfIndividual = relationshipStatusOfIndividual;
		this.emailOfIndividual = emailOfIndividual;
		this.birthdayOfIndividual = birthdayOfIndividual;
	}
	
	
	public FHNQuery(User user) {
	
		super();
		this.nameOfIndividual = user.getName();
		this.roleOfIndividual = user.getJobTitle();
		this.genderOfIndividual = "Male";
		this.relationshipStatusOfIndividual = user.getRelationshipStatus();
		this.emailOfIndividual = user.getEmail();
		this.birthdayOfIndividual = String.valueOf(user.getAge());
	}
	
	

	public String createQuery(){
		
		String agent = this.nameOfIndividual;
		
		this.query = "PREFIX FHNRDF: <http://www.semanticweb.org/porco/ontologies/2015/2/FHNRDF#>\r\n" + 
				"PREFIX FHN: <htttp://www.shawndexter.com/semantics/FHNOntology#>\r\n" + 
				"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX or: <http://www.ontologydesignpatterns.org/cp/owl/objectrole.owl#>\r\n" + 
				"PREFIX ar: <http://www.ontologydesignpatterns.org/cp/owl/agentrole.owl#>\r\n" + 
				"\r\n" + 
				"CONSTRUCT { FHN:" + agent +" FHN:agentIsSatisfiedBy ?y }\r\n" + 
				"WHERE {\r\n" + 
				"  FHN:" + agent +" or:hasRole ?b.\r\n" + 
				"  ?b FHN:roleHasNeed ?a.\r\n" + 
				"  ?a FHN:isSatisfiedBy ?y.      \r\n" + 
				"}";
		
		
		return this.query;
	}
	
	
	public String insertNewIndividual(){
		
		String agent = this.nameOfIndividual;
		
this.query= "PREFIX FHNRDF: <http://www.semanticweb.org/porco/ontologies/2015/2/FHNRDF#>\r\n" + 
		"PREFIX FHN: <htttp://www.shawndexter.com/semantics/FHNOntology#>\r\n" + 
		"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
		"PREFIX or: <http://www.ontologydesignpatterns.org/cp/owl/objectrole.owl#>\r\n" + 
		"PREFIX ar: <http://www.ontologydesignpatterns.org/cp/owl/agentrole.owl#>\r\n" + 
		"PREFIX vcard: <http://www.w3.org/2006/vcard/ns#>\r\n" + 
		"INSERT DATA\r\n" + 
		"{ FHN:" + agent + " or:hasRole FHN:" + roleOfIndividual + ".\r\n" + 
		"  FHN:" + agent +" vcard:bday \""+ birthdayOfIndividual +"\".\r\n" + 
		"  FHN:"+ agent + " vcard:email \"" + emailOfIndividual +  "\".\r\n" + 
		"  FHN:"+ agent + " vcard:hasGender  \"" + genderOfIndividual + "\".\r\n" + 
		"  FHN:" + agent +" or:hasRole FHN:" + relationshipStatusOfIndividual + "\r\n" + 
		"}";
		
		this.insertQuery = query;
		return this.insertQuery;
	}
	
	
	
	
	
}
