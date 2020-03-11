package P1;

import java.io.*;

/**
 * 需要判断
 * 1.数字之间是否用\t分割   isCutByTab()
 * 2.矩阵中的数字是否为正整数  isPnumber()
 * 3.符不符合矩阵的定义  isLegalMagicSquare()
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
	 * 判断字符串是否为正整数
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
	 * 判断数字之间是否用\t分割
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
	 * 判断是否为Magic Square
	 * @param fileName
	 * @throws IOException
	 */
	public static boolean isLegalMagicSquare(String fileName) throws IOException{
		File file = new File(fileName);
		BufferedReader bf = new BufferedReader(new FileReader(file));
		
		//读入第一行的数字，以第一行的列数为准，依次判断后面的行
		String lines = bf.readLine();
		String[] rows = lines.split("\t");
		//判断第一行分割符是否为\t
		if(!isCutByTab(rows)){
			System.out.println("分割符错误");
			return false;
		}
		//判断第一行是否为正整数
		for(String str : rows){
			if(!isPnumber(str)){
				System.out.println("有非正整数");
				return false;
			}
		}
		int col = rows.length;  //记录第一行列数
		int[][] magicSquare = new int[rows.length][rows.length];
		int i = 0;
		for(String str : rows){
			magicSquare[0][i] = Integer.valueOf(str);
			i++;
		}
		int list = 1; //第一行
	
		while((lines = bf.readLine())!=null){
			i = 0;
			rows = lines.split("\t");
			if(!isCutByTab(rows)){
				System.out.println("分割符错误");
				return false;
			}
			for(String str : rows){
				if(!isPnumber(str)){
					System.out.println("有非正整数");
					return false;
				}
			}
			for(String str : rows){
				magicSquare[list][i] = Integer.valueOf(str);
				i++;
			}
			if(col != rows.length){
				System.out.println("行缺少数字");
				return false;
			}
			
			list++;
			
		}
		if(list != col){
			System.out.println("不是方阵");
			return false;
			
		}
		bf.close();
		
		//判断行相加
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
		//判断列相加		
		for(int k=0 ; k<list ;k++){
			for(int j = 0 ; j<list ; j++){
				sum_j = sum_j + magicSquare[j][k];
			}
			if(sum_j != sum0)
				return false;
			sum_j = 0;
		}
		//判断对角线
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
		  //输入异常处理
		if(n<0){
			System.out.println("错误：n为负数");
			return false;
		}
		if(n % 2 == 0){
			System.out.println("错误：n为偶数");
			return false;
		}
		  
		 //生成幻方
		  int magic[][] = new int[n][n];   
		  int row = 0, col = n / 2, i, j, square = n * n; 
		 
		  for (i = 1; i <= square; i++) {    
			
			magic[row][col] = i;
			
			 //当右上角有元素直接下移一行
			  if (i % n == 0)     
				  row++;    
			  else {     
				  if (row == 0)      
					  row = n - 1;  //第一行右上角为空，移至最后一行
				  else      
					  row--;    //正常上移一行
				  if (col == (n - 1))      
					  col = 0;    //最后一列右上角为空，移到第一列
				  else      
					  col++;  //正常右移一列
				  }  
			  } 
		  
		  //输出生成的幻方
		  for(i = 0 ; i<n ; i++){
			  for(j = 0 ; j<n ; j++)
				  System.out.print(magic[i][j]+"\t");
			  System.out.println();
		  }
		  
		  //输出幻方到6.txt文件
		  String filename = "src/P1/txt/6.txt";
          FileWriter fw = new FileWriter(filename);
          BufferedWriter bw = new BufferedWriter(fw);
          for (i = 0; i < n; i++) {
              for (j = 0; j < n; j++) {
                  bw.write(magic[i][j] + "\t");//将产生的幻方数据写入到文件6.txt中，并注意格式＋\t
              }
              if(i < n-1) {//记得输出换行
              bw.write("\n");
              }
          }
          bw.close();
		    
		return true; 
		}
}
