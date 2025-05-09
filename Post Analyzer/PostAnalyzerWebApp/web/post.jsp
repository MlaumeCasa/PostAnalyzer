<%-- 
    Document   : post
    Created on : 09 May 2025, 12:03:47 PM
    Author     : Tk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Page</title>
    </head>
    <body>
        <h1>Post</h1>
        <p>
            Enter the following information for the post:           
        </p>
        <form action="AnalyzePostServlet.do" method="post">
            <table>
                <tr>
                    <td>ID: </td>
                    <td><input type="number" name="id" id="id"></td>
                </tr>
                <tr>
                    <td>User ID</td>
                    <td><input type="text" name="userid" id="userid"></td>
                </tr>
                <tr>
                    <td>Content:</td>
                    <td><input type="text" name="content" id="content"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Analyze Post"></td>
                </tr>
            </table>
        </form>
        
    </body>
</html>
