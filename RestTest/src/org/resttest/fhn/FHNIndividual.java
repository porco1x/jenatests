package org.resttest.fhn;
import java.util.ArrayList;
import org.openrdf.model.Model;
import org.openrdf.model.Statement;



public interface FHNIndividual {

 public void createFHNIndividual(Model model);
 
 public void getIndividual(Model model);
 
 public void getStatements(ArrayList<Statement> statements);
 
 
 
}