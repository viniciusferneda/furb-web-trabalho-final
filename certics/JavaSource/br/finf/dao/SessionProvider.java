package br.finf.dao;

public class SessionProvider {

	private static final SessionProvider INSTANCE = new SessionProvider();
	private final DBSession dbSession = new DBSession();

	private SessionProvider() {
	}

	public static SessionProvider get() {
		return INSTANCE;
	}

	public DBSession provide() {
		if (dbSession == null) {
			throw new IllegalStateException("A sessão não foi iniciada!");
		}
		return dbSession;
	}

}
