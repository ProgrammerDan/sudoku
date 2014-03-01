class SS_min {
	public static void main(String[] s) {
		int[][] puzzle = new int[9][9];
		for (int i=0; i<9; i++) {
			char[] row = s[i].toCharArray();
			for (int j=0; j<9; j++) {
				puzzle[i][j] = row[j]-48;
			}
		}
   		int[][] valid_sample = new int[][] {
	        {1,2,3,4,5,6,7,8,9},
        	{4,5,6,7,8,9,1,2,3},
   	    	{7,8,9,1,2,3,4,5,6},
   	    	{2,3,1,5,6,4,8,9,7},
        	{5,6,4,8,9,7,2,3,1},
        	{8,9,7,2,3,1,5,6,4},
        	{3,1,2,6,4,5,9,7,8},
        	{6,4,5,9,7,8,3,1,2},
        	{9,7,8,3,1,2,6,4,5}};
   		int[][] invalid_sample = new int[][] {
	        {1,2,3,4,5,6,7,8,9},
        	{4,5,6,7,8,9,1,2,3},
   	    	{7,8,9,1,2,3,4,5,6},
   	    	{2,3,1,5,6,4,8,9,7},
        	{5,6,4,8,9,7,2,3,1},
        	{8,9,7,2,3,1,6,6,4},
        	{3,1,2,6,4,5,9,7,8},
        	{6,4,5,9,7,8,3,1,2},
        	{9,7,8,3,1,2,5,4,5}};
   		int[][] edge_sample = new int[][] {
	        {5,5,5,5,5,5,5,5,5},
        	{5,5,5,5,5,5,5,5,5},
   	    	{5,5,5,5,5,5,5,5,5},
   	    	{5,5,5,5,5,5,5,5,5},
        	{5,5,5,5,5,5,5,5,5},
        	{5,5,5,5,5,5,5,5,5},
        	{5,5,5,5,5,5,5,5,5},
        	{5,5,5,5,5,5,5,5,5},
        	{5,5,5,5,5,5,5,5,5}};

		SS_min solver = new SS_min();

		int[] S = new int[] {
				solver.s(puzzle),
				solver.s(valid_sample),
				solver.s(invalid_sample),
				solver.s(edge_sample)};
		int[] K = new int[] {
				solver.k(puzzle),
				solver.k(valid_sample),
				solver.k(invalid_sample),
				solver.k(edge_sample)};

		for (int i = 0; i<4; i++) {
			System.out.println(S[i] + "," + K[i]);
		}

		System.exit(K[0]);
	}

	int s(int[][] s) {
		int i=0,j,k=1;
		long[] f=new long[9]; 
		long r=0L,c,g,q,z=45L;
		for(c=g=q=r,f[0]=1L;k<9;){f[k]=f[k-1]*49;z+=f[k++]*45;}
		for(;i<9;i++) {
			for (j=0;j<9;) {
				k=s[i][j];
				r+=k*f[i];
				c+=k*f[j];
				g+=k*f[j++/3+3*(i/3)];
				q+=5*f[k-1];
			}
		}
		return (r==z&&c==z&&g==z&&q==z)?0:1;
	}

	int k(int[][] s) {
		int i=0,j;
		int[][] z = new int[4][9];
		for (;i<9;i++)
			for (j=0;j<4;j++)
				z[j][i]=45;
		for (i=0;i<9;i++) {
			for (j=0;j<9;j++) {
				z[0][i]-=s[i][j];
				z[1][j]-=s[i][j];
				z[2][i/3+3*(j/3)]-=s[i][j];
				z[3][s[i][j]-1]-=5;
			}
		}
		for (i=0;i<9;i++)
			for (j=0;j<4;j++)
				if (z[j][i] > 0) return 1;

		return 0;
	}

	int k2(int[][] s) {
		int i=0,j;
		int[][] z = new int[4][9];
		for (;i<9;i++)
			for (j=0;j<4;j++)
				z[j][i]=45;
		for (i=0;i<9;i++) {
			for (j=0;j<9;j++) {
				z[0][i]-=s[i][j];
				z[1][j]-=s[i][j];
				z[2][i/3+3*(j/3)]-=s[i][j];
				z[3][s[i][j]-1]-=5;
			}
		}
		for (i=0;i<9;i++)
			for (j=0;j<4;j++)
				if (z[j][i] > 0) return 1;

		return 0;
	}

int x(int[][] s){int i=0,j,k=1;long[] f=new long[9];long r=0L,c,g,z=45L,q;for(c=q=g=r,f[0]=1L;k<9;){f[k]=f[k-1]*49;z+=f[k++]*45;}for(;i<9;i++){for(j=0;j<9;){k=s[i][j];r+=k*f[i];c+=k*f[j];g+=k*f[j++/3+3*(i/3)];q+=5*f[k-1];}}return (r==z&&c==z&&g==z&&q==z)?0:1;}

int z(int[][] s){int i=0,j;int[][] z=new int[4][9];for(;i<9;i++)for(j=0;j<4;)z[j++][i]=45;for(i=0;i<9;i++){for(j=0;j<9;){z[0][i]-=s[i][j];z[1][j]-=s[i][j];z[2][i/3+3*(j/3)]-=s[i][j];z[3][s[i][j++]-1]-=5;}}for(i=0;i<9;i++)for(j=0;j<4;j++)if(z[j][i]>0)return 1;return 0;} 
}

