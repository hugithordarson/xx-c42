package family.data;

import java.util.UUID;

import family.data.auto._PersonWithAppGeneratedUUID;

public class PersonWithAppGeneratedUUID extends _PersonWithAppGeneratedUUID {

	@Override
	protected void onPostAdd() {
		setId( UUID.randomUUID() );
	}
}