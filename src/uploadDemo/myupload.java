package uploadDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class myupload
 */
public class myupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myupload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//��ȡ�����ļ���·��
		
		String savePath=this.getServletContext().getRealPath("/WEB-INF/upload");
		
		System.out.println("����·���ǣ�"+savePath);
		
		File file=new File(savePath);
		
		if(!file.exists()){
			System.out.println("Ŀ¼�����ڣ�����");
			file.mkdirs();
		}
		DiskFileItemFactory factory=new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setHeaderEncoding("utf-8");
		String msg="";
		 if(!upload.isMultipartContent(request)){
			 
			 return;
		 }
		 
		 try {
			List<FileItem> list=upload.parseRequest(request);
			
		    for(FileItem item : list){
		     if(item.isFormField()){

		    	 System.out.println(item.getFieldName()+":"+item.getString("utf-8"));
		     }else{
		    	 
		    	   String fileName=item.getName();
		    	   
		    	  if(fileName==null||fileName.equals("")){
		    		  
		    		  System.out.println("�ļ���Ϊ�գ�������");
		    		  return;
		    	  }
		    	  fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		    	  System.out.println("��д����ļ���:"+fileName);
		         //��ȡ������
		    	  InputStream  in=item.getInputStream();
		    	  
		    	  FileOutputStream out=new FileOutputStream(savePath + "\\" + fileName);
		    	 
		    	  byte[] b=new byte[1024];
		    	  int len=0;
		    	  while((len=in.read(b))>0){
		    		  
		    		  out.write(b,0,len);
		    	  }
		    	  
		    	  in.close();
		    	  out.close();
		    	  item.delete();
		    	  msg="�ϴ��ɹ���";
		     }
		    	
		    	
		    }
			

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			 msg="�ϴ�ʧ�ܣ�"+e.getMessage();
			e.printStackTrace();
		}
		 
		request.setAttribute("msg",msg);
		
	request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

}
