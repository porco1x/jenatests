package jenatest;

import java.util.UUID;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateProcessor;
 
/**
 * Example connection to Fuseki. For this to work, you need to start a local
 * Fuseki server like this: ./fuseki-server --update --mem /ds
 */
public class FusekiTest {
    /** A template for creating a nice SPARUL query */
    private static final String UPDATE_TEMPLATE = 
            "PREFIX dc: <http://purl.org/dc/elements/1.1/>"
            + "INSERT DATA"
            + "{ <http://example/%s>    dc:title    \"A new book\" ;"
            + "                         dc:creator  \"A.N.Other\" ." + "}   ";
 
    public static void main(String[] args) {
        //Add a new book to the collection
        String id = UUID.randomUUID().toString();
        System.out.println(String.format("Adding %s", id));
        UpdateProcessor upp = UpdateExecutionFactory.createRemote(
                UpdateFactory.create(String.format(UPDATE_TEMPLATE, id)), 
                "http://localhost:3030/ds/update");
        upp.execute();
        //Query the collection, dump output
        QueryExecution qe = QueryExecutionFactory.sparqlService(
                "http://localhost:3030/ds/query", "PREFIX FHNRDF: <http://www.semanticweb.org/porco/ontologies/2015/2/FHNRDF#>PREFIX FHN: <htttp://www.shawndexter.com/semantics/FHNOntology#>PREFIX owl: <http://www.w3.org/2002/07/owl#>PREFIX or: <http://www.ontologydesignpatterns.org/cp/owl/objectrole.owl#>SELECT *WHERE{?x or:hasRole ?z}");
        ResultSet results = qe.execSelect();
      ResultSetFormatter.out(System.out, results);
     
        qe.close();
    }
 
}