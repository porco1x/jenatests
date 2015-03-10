// package yourPack;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author Saidi Ali-(+216 22 790 538) - (+216 50 590 538) - fb.com/idiasila - Tunisia (2012-2013)
 *
 *
 *
 */

class OpenOWL {

     static  String  s;

      // Open a connection to MonFichierOwl.OWL

     static  OntModel OpenConnectOWL(){
        
    OntModel mode = null;
    mode = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_RULE_INF );
    java.io.InputStream in = FileManager.get().open( "C:/MonFichierOWL.owl" );  //MyFile

    //test
    if (in == null) {
        throw new IllegalArgumentException("Pas de base de connaissance");  // there i no file to connect
    }
        return  (OntModel) mode.read(in, "");
    }


    // jena.query.ResultSet  return

     static  com.hp.hpl.jena.query.ResultSet ExecSparQl(String Query){
         
          com.hp.hpl.jena.query.Query query = QueryFactory.create(Query);

                QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());
                com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

                return results;  // Retrun jena.query.ResultSet 
         
     }

     // String return (convert jena.query.ResultSet  to String)

      static  String ExecSparQlString(String Query){
        try {
            com.hp.hpl.jena.query.Query query = QueryFactory.create(Query);

                  QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());

                  com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

                  // Test
                  if(results.hasNext()){

                  	// if iS good 

                     ByteArrayOutputStream go = new ByteArrayOutputStream ();
                    ResultSetFormatter.out((OutputStream)go ,results, query);
                  //  String s = go.toString();
                       s = new String(go.toByteArray(), "UTF-8");
                   }
                   // not okay
                  else{

                      s = "rien";
                  }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OpenOWL.class.getName()).log(Level.SEVERE, null, ex);
        }
         return s;   // return  jena.query.ResultSet  as string 
      }
    
}
