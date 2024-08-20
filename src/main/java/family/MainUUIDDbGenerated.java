package family;

import java.util.UUID;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.SQLExec;

import family.data.PersonWithDbGeneratedUUID;

public class MainUUIDDbGenerated {

	public static void main( String[] args ) {
		ServerRuntime serverRuntime = ServerRuntime.builder()
				.addConfig( "cayenne/cayenne-project.xml" )
				.jdbcDriver( "org.h2.Driver" )
				.url( "jdbc:h2:mem:tempdb-" + UUID.randomUUID() )
				.build();

		SQLExec
				.query( """
						create table person_with_db_generated_uuid(
							id uuid default random_uuid() primary key,
							parent_id uuid,
							last_added_child_id uuid,
							name varchar(1024)
						);

						alter table person_with_db_generated_uuid
						    add foreign key (parent_id)
						    references person_with_db_generated_uuid(id);

						alter table person_with_db_generated_uuid
						    add foreign key (last_added_child_id)
						    references person_with_db_generated_uuid(id);
						""" )
				.execute( serverRuntime.newContext() );

		ObjectContext oc = serverRuntime.newContext();

		PersonWithDbGeneratedUUID parent = oc.newObject( PersonWithDbGeneratedUUID.class );
		PersonWithDbGeneratedUUID child = oc.newObject( PersonWithDbGeneratedUUID.class );

		parent.addToChildren( child );

		oc.commitChanges();

		parent.setLastAddedChild( child );

		oc.commitChanges();

		System.out.println( "parent: " + parent.getObjectId() );
		System.out.println( "child: " + child.getObjectId() );
		System.out.println( "child's parent: " + child.getParent().getObjectId() );
		System.out.println( "parent's last added child: " + parent.getLastAddedChild().getObjectId() );
	}
}