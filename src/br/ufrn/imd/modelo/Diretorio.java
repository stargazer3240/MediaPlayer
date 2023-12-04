package br.ufrn.imd.modelo;

import java.io.File;

public class Diretorio {
	private Integer userId;
	private File dir;

	public Diretorio(Integer id, File dir) {
		this.userId = id;
		this.dir = dir;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public File getDir() {
		return dir;
	}

	public void setDir(File dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return userId + " " + dir.getAbsolutePath();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Diretorio)) {
			return false;
		}
		Diretorio c = (Diretorio) o;
		return userId.equals(c.userId) && dir.toURI().equals(c.getDir().toURI());
	}
}
