package family;

import java.util.List;
import java.util.UUID;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.flush.operation.DbRowOp;
import org.apache.cayenne.access.flush.operation.DbRowOpSorter;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.SQLExec;

import family.data.PersonWithAppGeneratedUUID;

public class MainUUIDAppGenerated {

	public static void main( String[] args ) {
		ServerRuntime serverRuntime = ServerRuntime.builder()
				.addConfig( "cayenne/cayenne-project.xml" )
				.jdbcDriver( "org.h2.Driver" )
				.url( "jdbc:h2:mem:tempdb-" + UUID.randomUUID() )
				// .jdbcDriver( "org.postgresql.Driver" )
				// .url( "jdbc:postgresql://localhost/uuidtest" )
				.addModule( b -> b.bind( DbRowOpSorter.class ).to( NoOpDbRowOpSorter.class ) )
				.build();

		SQLExec
				.query( """
						drop table if exists person_with_app_generated_uuid;

						create table person_with_app_generated_uuid(
							id uuid primary key,
							parent_id uuid,
							last_added_child_id uuid,
							name varchar(1024)
						);

						-- Not generating the constraints by default since h2 doesn't support deferred constraints. You can create those if using postgres
						-- since due to being defined as 'deferrable initially deferred' they'll only get checked at the end of the transaction.

						-- alter table person_with_app_generated_uuid
						    add foreign key (parent_id)
						    references person_with_app_generated_uuid(id)
						    deferrable initially deferred;

						-- alter table person_with_app_generated_uuid
						    add foreign key (last_added_child_id)
						    references person_with_app_generated_uuid(id)
						    deferrable initially deferred;
						""" )
				.execute( serverRuntime.newContext() );

		ObjectContext oc = serverRuntime.newContext();

		PersonWithAppGeneratedUUID parent = oc.newObject( PersonWithAppGeneratedUUID.class );
		PersonWithAppGeneratedUUID child = oc.newObject( PersonWithAppGeneratedUUID.class );

		parent.addToChildren( child );
		parent.setLastAddedChild( child );

		oc.commitChanges();

		System.out.println( "parent: " + parent.getObjectId() );
		System.out.println( "child: " + child.getObjectId() );
		System.out.println( "child's parent: " + child.getParent().getObjectId() );
		System.out.println( "parent's last added child: " + parent.getLastAddedChild().getObjectId() );
	}

	public static class NoOpDbRowOpSorter implements DbRowOpSorter {

		@Override
		public List<DbRowOp> sort( List<DbRowOp> dbRows ) {
			return dbRows;
		}
	}
}