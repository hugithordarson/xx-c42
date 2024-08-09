package family;

import static java.lang.System.out;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

import family.data.Person;

public class Main {

	public static void main( String[] args ) {
		ServerRuntime serverRuntime = ServerRuntime.builder()
				.addConfig( "cayenne/cayenne-project.xml" )
				.jdbcDriver( "org.h2.Driver" )
				.url( "jdbc:h2:mem:smu" )
				.build();

		ObjectContext oc = serverRuntime.newContext();

		Person me = oc.newObject( Person.class );
		Person dad = oc.newObject( Person.class );

		out.println( "Commit 1 -- creating objects" );
		oc.commitChanges();

		me.setParent( dad );

		out.println( "Commit 2 -- added first reference" );
		oc.commitChanges();

		dad.setParent( me );

		out.println( "Commit 3 -- added second reference, creating a circle. If you're on Cayenne v4.2, here's where we fail" );
		oc.commitChanges();
	}
}