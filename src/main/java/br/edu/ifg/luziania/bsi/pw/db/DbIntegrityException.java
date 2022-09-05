package br.edu.ifg.luziania.bsi.pw.db;

public class DbIntegrityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DbIntegrityException(String msg) {
        super(msg);
    }
}
