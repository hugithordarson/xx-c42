package family;

import static java.lang.System.out;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import family.data.Person;

public class MainWithAddedBackReference {

	public static void main( String[] args ) {
		ServerRuntime serverRuntime = ServerRuntime.builder()
				.addConfig( "cayenne/cayenne-project.xml" )
				.jdbcDriver( "org.h2.Driver" )
				.url( "jdbc:h2:mem:smu" )
				.build();

		ObjectContext oc = serverRuntime.newContext();

		Person parent = oc.newObject( Person.class );
		Person child = oc.newObject( Person.class );

		out.println( "Commit 1 -- creates the objects" );
		oc.commitChanges();

		parent.addToChildren( child );
		parent.setLastAddedChild( child );

		out.println( "Commit 2 -- add a linked child and reference it from the parent. Here's where we fail in v4.2" );
		oc.commitChanges();
	}
}