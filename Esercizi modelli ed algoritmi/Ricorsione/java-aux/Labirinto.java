public class Labirinto {

	private static enum Cella { VUOTA, PIENA };

	private int n;
    private Cella m[][];
    private boolean marcata[][];

  public Labirinto(int n) {
	this.n = n;
	m = new Cella[n][n];
	marcata = new boolean[n][n];
	for(int i = 0; i < n; i++){
		for (int j = 0; j < n; j++){
			m[i][j] = Cella.VUOTA;
			marcata[i][j] = false;
		}
	}
  }

	public void setPiena(int r, int c){
		if(percorribile(r, c))
			m[r][c] = Cella.PIENA;
  }

	private boolean uscita(int r, int c){
  		if(r == n-1 && r == c) return true;
		return false;
  }

	public boolean percorribile(int r, int c){
  		if(r >= 0 && r < n && c >= 0 && c < n){
			if(!marcata[r][c] && m[r][c].equals(Cella.VUOTA))
				return true;
		}
		return false;
  }

	private boolean uscitaRaggiungibileDa(int r, int c){
		if(uscita(r,c)){
			marcata[r][c] = true;
			return true;
		} 
		else if (percorribile(r,c)){
			marcata[r][c] = true;
			if( uscitaRaggiungibileDa(r, c+1) || uscitaRaggiungibileDa(r, c-1)
			|| uscitaRaggiungibileDa(r+1, c) || uscitaRaggiungibileDa(r-1, c))
				return true; 
			else{
				marcata[r][c] = false;
				return false;
			}
		}
		else{
			return false;
		}
	}

	public boolean risolvibile(){
		System.out.println(this);
		return uscitaRaggiungibileDa(0, 0);
	}

	public String toString() {
		String res = "";
		for(int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if(marcata[i][j]) res+=  "+ ";
				else res+=(m[i][j].equals(Cella.VUOTA))?". ":"# ";
			}
			res+="\n";
		}
		return res;
	}	
}
