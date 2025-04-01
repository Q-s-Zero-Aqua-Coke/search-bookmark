<%@ page import="org.example.searchbookmark.model.vo.KeywordSearch" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>검색을 할거에요</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 20px;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        input[name="keyword"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            flex-grow: 1;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        section {
            width: 80%; /* 검색 결과 영역 너비 조정 */
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        section h3 {
            color: #333;
            margin-top: 0;
            margin-bottom: 5px;
        }

        section ul {
            list-style: none;
            padding: 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
        }

        section ul li {
            color: #555;
            margin-bottom: 5px;
        }

        section ul li:last-child {
            margin-bottom: 0;
        }

        .result-item {
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .result-item h3 {
            margin-top: 0;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>검색하자!</h1>
<form>
    <label>키워드 <input name="keyword"></label>
    <button>검색</button>
</form>
<% if (request.getAttribute("result") != null) { %>
<section>
    <%
        List<KeywordSearch> results = (List<KeywordSearch>) request.getAttribute("result");
        if (results != null && !results.isEmpty()) {
            for (KeywordSearch v : results) {
    %>
    <div class="result-item">
        <h3><%= v.title() %></h3>
        <ul>
            <li>링크: <a href="<%= v.link() %>" target="_blank"><%= v.link() %></a></li>
            <li>설명: <%= v.description() %></li>
            <li>등록일: <%= v.postdate() %></li>
        </ul>
    </div>
    <%
        }
    } else {
    %>
    <p>검색 결과가 없습니다.</p>
    <%
        }
    %>
</section>
<% } %>
</body>
</html>