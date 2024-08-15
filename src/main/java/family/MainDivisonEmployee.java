package family;

import static java.lang.System.out;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import family.data.Division;
import family.data.Person;

public class MainDivisonEmployee {

	public static void main( String[] args ) {
		ServerRuntime serverRuntime = ServerRuntime.builder()
				.addConfig( "cayenne/cayenne-project.xml" )
				.jdbcDriver( "org.h2.Driver" )
				.url( "jdbc:h2:mem:smu" )
				.build();

		ObjectContext oc = serverRuntime.newContext();

		Division existingDivision = oc.newObject( Division.class );

		out.println( "Commit 1 -- creates the division" );
		oc.commitChanges();

		Person newEmployee = oc.newObject( Person.class );
		existingDivision.addToEmployees( newEmployee );
		existingDivision.setManager( newEmployee );

		out.println( "Commit 2 -- Create an employee and link it up" );
		oc.commitChanges();
		out.println( "Commit 2 -- Create an employee and link it up" );
	}
}