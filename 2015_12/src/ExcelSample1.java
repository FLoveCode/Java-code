import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelSample1 {
	public static void main(String[] args) throws IOException {
		//����һ��excel�ļ�
		HSSFWorkbook wb= new HSSFWorkbook();
		FileOutputStream fileOut= new FileOutputStream("d:\\workbook.xls");
		// FileOutputStream fileOut= new FileOutputStream("c:/workbook.xls");
		wb.write(fileOut);
		fileOut.close();
		}
		
}
