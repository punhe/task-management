<%@page import="com.se4f7.prj301.entities.ToDoEntity"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" %>
<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbook" %>
<%@page import="org.apache.poi.ss.usermodel.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset="utf-8">
        <title>Report Page</title>
    </head>
    <body>
        <%
            response.setHeader("Content-Disposition", "inline; filename=todo.xlsx");
            List<ToDoEntity> list = (List<ToDoEntity>) request.getAttribute("lists");

            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("ToDo List");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] columns = {"ID", "Name", "Description", "Status", "Created By", "Updated By",
                    "Created Date", "Updated Date", "Priority", "Due"};
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                }

                // Create data rows
                int rowNum = 1;
                for (ToDoEntity toDo : list) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(toDo.getId());
                    row.createCell(1).setCellValue(toDo.getName());
                    row.createCell(2).setCellValue(toDo.getDescription());
                    row.createCell(3).setCellValue(toDo.getStatus());
                    row.createCell(4).setCellValue(toDo.getCreatedBy());
                    row.createCell(5).setCellValue(toDo.getUpdatedBy());
                    row.createCell(6).setCellValue(toDo.getCreatedDate());
                    row.createCell(7).setCellValue(toDo.getUpdatedDate());
                    row.createCell(8).setCellValue(toDo.getPriority());
                    row.createCell(9).setCellValue(toDo.getDue());
                }

                // Auto-size columns (optional)
                for (int i = 0; i < columns.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                // Write the workbook to the response output stream
                try (OutputStream outputStream = response.getOutputStream()) {
                    workbook.write(outputStream);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        %>
    </body>
</html>
