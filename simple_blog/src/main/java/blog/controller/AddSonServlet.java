package blog.controller;

import blog.dao.BlogSonDao;
import blog.dao.BlogSonDaoImpl;
import blog.model.BlogSon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddSonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String son_name = request.getParameter("user_name");
        String blog_content = request.getParameter("blog");
        String idStr = request.getParameter("father_id");
        if (idStr!=null&&!idStr.equals("")) {
            int father_id = Integer.parseInt(idStr);
            BlogSon blogSon = new BlogSon();
            blogSon.setFather_id(father_id);
            blogSon.setSon_name(new String(son_name.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            blogSon.setBlog_content(new String(blog_content.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            BlogSonDao dao = new BlogSonDaoImpl();
            dao.insert(blogSon);
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
