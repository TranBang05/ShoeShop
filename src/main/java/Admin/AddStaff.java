package Admin;



import Model.Admin;
import Model.Staff;
import Model.User;
import OTPFunction.MailSending;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AdminAddStaffController", value = "/addStaff")
public class AddStaff extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





        String fullname = request.getParameter("username");
        String user = request.getParameter("email");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repass");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");



        Staff staff = new Staff();

        boolean password = staff.isSecurePassword(pass);
        if(!password){
            request.setAttribute("mess", "Mật khẩu phải gồm 8 kí tự (Gồm 1 chữ hoa,chữ thường và kí tự đặc biệt)!");
        }
        else {
            if (!pass.equals(repass)) {
                request.setAttribute("mess", "Mật khẩu không khớp!");
            } else {
                boolean check = true;
                User DaoC  = new User ();
                if (DaoC.searchByEmail(user) != null) {
                    check = false;
                }
                Staff DaoS = new Staff();
                if (DaoS.searchByEmail(user) != null) {
                    check = false;
                }
                Admin DaoA = new Admin();
                if (DaoA.searchAdminByEmail (user) != null) {
                    check = false;
                }
                if (check) {
                    System.out.println ("hh");
                    HttpSession session = request.getSession();
                    Admin admin = (Admin) session.getAttribute("admin");
                    int adminId = admin.admin_id;
                    System.out.println (adminId);
                    Staff newStaff = new Staff(fullname,pass,user,address,phone,0);
                    //DaoS.insertStaff(newStaff,adminId);
                    DaoS.addStaffMember (newStaff);



                    // Send email to the staff
                    MailSending mail = new MailSending();


                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            mail.sendEmailPassStaff ("tranxuanbang.yt@gmail.com", "zptgaabhvfqmsvnw", newStaff.getEmail(),pass);
                        }
                    };


                    thread.start();

                    request.setAttribute("email", newStaff.getEmail());
                    request.setAttribute("mess", "Thêm tài khoản thành nhân viên công!");

                }

                else {
                    request.setAttribute("mess", "Email đã tồn tại!");
                }
            }
        }
        request.getRequestDispatcher("AddStaff.jsp").forward(request, response);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("admin") != null){
            request.getRequestDispatcher("AddStaff.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("login");
        }
    }
}
