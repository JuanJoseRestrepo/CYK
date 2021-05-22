package model;

public class CYK {

	private int np = 0;
	private String[][] grammer;
	private String str;
	
	
	
    public CYK(String[][] grammer, String str) {
		this.grammer = grammer;
		this.str = str;
	}

	//Checks if the passed string can be achieved for the grammer
    public String check(String a){
        String to_ret = "";
        int count = 0;
        for(int i = 0; i < np; i++) {
            for(count = 0; count < grammer[i].length; count++){
                if(grammer[i][count].equals(a)){
                    to_ret += grammer[i][0];
                }
            }
        }
        return to_ret;
    }
    
    //Makes all possible combinations out of the two string passed
    public String combinat(String a, String b){
        String to_ret = "", temp = "";
            for(int i = 0; i < a.length(); i++){
                for(int j = 0; j < b.length(); j++){
                    temp = "";
                    temp += a.charAt(i) + "" +  b.charAt(j);
                    to_ret += check(temp);
                }
            }
        return to_ret;
    }
	
    public String fillMatrixAndAnswer() {
    	String r;
    	String st;
    	String  start = "S";
    	int count = 0;
    	np = grammer.length;
    	String response = "";
    	String ans_mat[][] = new String[10][10];
        for(int i = 0; i < str.length(); i++){
            r = "";
            st = "" + str.charAt(i);
            for(int j = 0; j < np; j++){
                for(count = 1; count < grammer[j].length; count++){
                    if(grammer[j][count].equals(st)){
                        r += grammer[j][0];
                    }
                }      
            }
            ans_mat[i][i] = r;
        }
        
        //Fill the rest of the matrix
        for(int i = 1; i < str.length(); i++){
            for(int j = i; j < str.length(); j++){
                r = "";
                for(int k = j - i; k < j; k++){
                    r += combinat(ans_mat[j - i][k], ans_mat[k + 1][j]);
                }
                ans_mat[j - i][j] = r;
            }
        }
        
        //The last column of first row should have the start symbol
        if(ans_mat[0][str.length() - 1].indexOf(start) >= 0){
           
        	response = "acepta";
        }
        else{
        	response = "no acepta";
        }
        
        return response;
    }
	
}
