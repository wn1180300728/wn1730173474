package P1;

import java.io.*;

/**
 * ��Ҫ�ж�
 * 1.����֮���Ƿ���\t�ָ�   isCutByTab()
 * 2.�����е������Ƿ�Ϊ������  isPnumber()
 * 3.�������Ͼ���Ķ���  isLegalMagicSquare()
 * @author Wang
 *
 */

public class MagicSquare {
	public static void main(String[] args) throws IOException{
		
		System.out.println(isLegalMagicSquare("src/P1/txt/1.txt"));
		System.out.println(isLegalMagicSquare("src/P1/txt/2.txt"));
		System.out.println(isLegalMagicSquare("src/P1/txt/3.txt"));
		System.out.println(isLegalMagicSquare("src/P1/txt/4.txt"));
		System.out.println(isLegalMagicSquare("src/P1/txt/5.txt"));
		generateMagicSquare(5);

		
		
		
		

	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ������
	 * @param str
	 * @return
	 */
	public static boolean isPnumber(String str){
		for(int i = str.length();--i>=0;){
			int chr = str.charAt(i);
			if(chr<48 || chr>57)
				return false;
		}
		return true;
	}
	
	/**
	 * �ж�����֮���Ƿ���\t�ָ�
	 * @param str
	 * @return
	 */
	public static boolean isCutByTab(String[] str){
		for(String st : str)
			if(!st.equals("")&&st.indexOf(" ")!=-1){
				return false;
			}
		return true;
	}
	
	/**
	 * �ж��Ƿ�ΪMagic Square
	 * @param fileName
	 * @throws IOException
	 */
	public static boolean isLegalMagicSquare(String fileName) throws IOException{
		File file = new File(fileName);
		BufferedReader bf = new BufferedReader(new FileReader(file));
		
		//�����һ�е����֣��Ե�һ�е�����Ϊ׼�������жϺ������
		String lines = bf.readLine();
		String[] rows = lines.split("\t");
		//�жϵ�һ�зָ���Ƿ�Ϊ\t
		if(!isCutByTab(rows)){
			System.out.println("�ָ������");
			return false;
		}
		//�жϵ�һ���Ƿ�Ϊ������
		for(String str : rows){
			if(!isPnumber(str)){
				System.out.println("�з�������");
				return false;
			}
		}
		int col = rows.length;  //��¼��һ������
		int[][] magicSquare = new int[rows.length][rows.length];
		int i = 0;
		for(String str : rows){
			magicSquare[0][i] = Integer.valueOf(str);
			i++;
		}
		int list = 1; //��һ��
	
		while((lines = bf.readLine())!=null){
			i = 0;
			rows = lines.split("\t");
			if(!isCutByTab(rows)){
				System.out.println("�ָ������");
				return false;
			}
			for(String str : rows){
				if(!isPnumber(str)){
					System.out.println("�з�������");
					return false;
				}
			}
			for(String str : rows){
				magicSquare[list][i] = Integer.valueOf(str);
				i++;
			}
			if(col != rows.length){
				System.out.println("��ȱ������");
				return false;
			}
			
			list++;
			
		}
		if(list != col){
			System.out.println("���Ƿ���");
			return false;
			
		}
		bf.close();
		
		//�ж������
		int sum0 = 0;
		int sum_j = 0;
		for(int j = 0 ; j<list ; j++){
			sum0 = sum0 + magicSquare[0][j];
		}
		for(int j = 0 ; j<list ; j++){
			for(int k = 0 ; k<list ; k++ ){
				sum_j = sum_j + magicSquare[j][k];
			}
			if(sum_j != sum0){
				return false;
			}
			sum_j = 0;
			
		}
		//�ж������		
		for(int k=0 ; k<list ;k++){
			for(int j = 0 ; j<list ; j++){
				sum_j = sum_j + magicSquare[j][k];
			}
			if(sum_j != sum0)
				return false;
			sum_j = 0;
		}
		//�ж϶Խ���
		for(int j=0 ; j<list ; j++){
			sum_j += magicSquare[j][j];
		}
		if(sum_j != sum0)
			return false;
		sum_j = 0;
		int m = list-1;
    	for(int j=0 ; j<list ; j++ ){
			sum_j +=magicSquare[j][m];
			m--;
	    }
		if(sum_j != sum0)
			return false;
		sum_j = 0;
		
		
		return true;

	
	}
	public static boolean generateMagicSquare(int n) throws IOException { 
		  //�����쳣����
		if(n<0){
			System.out.println("����nΪ����");
			return false;
		}
		if(n % 2 == 0){
			System.out.println("����nΪż��");
			return false;
		}
		  
		 //���ɻ÷�
		  int magic[][] = new int[n][n];   
		  int row = 0, col = n / 2, i, j, square = n * n; 
		 
		  for (i = 1; i <= square; i++) {    
			
			magic[row][col] = i;
			
			 //�����Ͻ���Ԫ��ֱ������һ��
			  if (i % n == 0)     
				  row++;    
			  else {     
				  if (row == 0)      
					  row = n - 1;  //��һ�����Ͻ�Ϊ�գ��������һ��
				  else      
					  row--;    //��������һ��
				  if (col == (n - 1))      
					  col = 0;    //���һ�����Ͻ�Ϊ�գ��Ƶ���һ��
				  else      
					  col++;  //��������һ��
				  }  
			  } 
		  
		  //������ɵĻ÷�
		  for(i = 0 ; i<n ; i++){
			  for(j = 0 ; j<n ; j++)
				  System.out.print(magic[i][j]+"\t");
			  System.out.println();
		  }
		  
		  //����÷���6.txt�ļ�
		  String filename = "src/P1/txt/6.txt";
          FileWriter fw = new FileWriter(filename);
          BufferedWriter bw = new BufferedWriter(fw);
          for (i = 0; i < n; i++) {
              for (j = 0; j < n; j++) {
                  bw.write(magic[i][j] + "\t");//�������Ļ÷�����д�뵽�ļ�6.txt�У���ע���ʽ��\t
              }
              if(i < n-1) {//�ǵ��������
              bw.write("\n");
              }
          }
          bw.close();
		    
		return true; 
		}
}
