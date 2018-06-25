package com.bbs.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bbs.bean.BbsUser;
import com.bbs.service.UserService;

@WebServlet("/UploadPictrueServlet")
public class UploadPictrueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		String filePath = getServletContext().getRealPath("/upload/");
		//String filePath = request.getContextPath()+"/upload/";
		//String filePath = "D://upload/img";
		int id = Integer.parseInt(request.getParameter("id"));
		
		File path = new File(filePath);
		if (!path.exists() && !path.isDirectory()) {
			path.mkdirs();
		}
		
		boolean isIO = ServletFileUpload.isMultipartContent(request);
		if (isIO) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> it = items.iterator();
				List<String> fileTypes = new ArrayList<String>();  
				fileTypes.add(".jpg");  
				fileTypes.add(".jpeg");  
				fileTypes.add(".gif");  
				fileTypes.add(".png");  
				 
				while (it.hasNext()) {
					FileItem fileItem = it.next();
					String fileName = fileItem.getName();
					//System.out.println("fileName:"+fileName);
					String prefix = null;
					if (fileName.lastIndexOf(".") >= 0) 
						prefix = fileName.substring(fileName.lastIndexOf("."));
					if(!fileTypes.contains(prefix.toLowerCase())){  
						System.out.println("文件类型不正确"); 
						continue;
					} 
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = sdf.format(new Date()) + "_" + new Random().nextInt(10000) + prefix;
					File newFile = new File(filePath, newFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}
					fileItem.write(newFile);
					System.out.println("Path:"+newFile.getPath());
					//System.out.println("name:"+newFile.getName());
					String url = newFile.getName();
					us.saveUserHeaderPicPath(id, url);
					//System.out.println("上传成功");
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		BbsUser user = us.selectUser(id);
		HttpSession session =request.getSession();
		session.setAttribute("loginuser", user);
		response.sendRedirect(request.getContextPath()+"/user/set.jsp");
	}

}
