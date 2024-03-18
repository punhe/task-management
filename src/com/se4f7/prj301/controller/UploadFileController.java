package com.se4f7.prj301.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFileController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;

	public void init() {
		toDoService = new ToDoServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Part filePart = request.getPart("file");

			String uploadDir = getServletContext().getRealPath("") + File.separator;

			File uploadDirFile = new File(uploadDir);
			if (!uploadDirFile.exists()) {
				uploadDirFile.mkdir();
			}

			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

			String filePath = uploadDir + File.separator + fileName;

			try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}
			}
			boolean importResult = toDoService.importFromExcel(request, response, filePath);

			if (importResult) {
				response.sendRedirect("import-success.jsp");
			} else {
				response.getWriter().write("Import failed - Status [0-3] - Priority [0-1]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error during import. Please check the logs for details.");
		}
	}
}
